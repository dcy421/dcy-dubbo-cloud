package com.dcy.service.provideradmin.service;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dcy.common.constant.Constant;
import com.dcy.common.utils.BPwdEncoderUtil;
import com.dcy.common.utils.JwtUtil;
import com.dcy.db.base.service.impl.BaseServiceImpl;
import com.dcy.service.apiadmin.api.UserInfoService;
import com.dcy.service.apiadmin.dto.UserInfoRoleDto;
import com.dcy.service.apiadmin.model.Role;
import com.dcy.service.apiadmin.model.UserInfo;
import com.dcy.service.apiadmin.model.UserRole;
import com.dcy.service.provideradmin.mapper.UserInfoMapper;
import com.dcy.service.provideradmin.mapper.UserRoleMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author dcy
 * @since 2020-08-19
 */
@DubboService(version = "1.0.0",timeout = 3000)
@Service
@Transactional
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public String login(UserInfo userInfo) {
        UserInfo userInfo1 = super.getOne(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getUsername, userInfo.getUsername()));
        if (BPwdEncoderUtil.matches(userInfo.getPassword(), userInfo1.getPassword().replace("{bcrypt}", ""))) {
            return JwtUtil.generateToken(JSON.toJSONString(userInfo1));
        }
        return null;
    }

    @Override
    public UserInfo getUserInfo(String token) {
        String validateToken = JwtUtil.validateToken(token);
        if (StrUtil.isNotBlank(validateToken)) {
            UserInfo userInfo = JSON.parseObject(validateToken, UserInfo.class);
            userInfo.setPassword(null);
            return userInfo;
        }
        return null;
    }

    @Override
    public UserInfo getUserInfoByUsername(String username) {
        return super.getOne(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getUsername, username));
    }

    @Override
    public List<Map<String, Object>> getResourcesByUserId(String userId) {
        return baseMapper.getResourcesByUserId(userId);
    }

    @Override
    public List<Role> getAuthRoleListByUserId(String userId) {
        return baseMapper.getAuthRoleListByUserId(userId);
    }

    @Override
    public Boolean saveAuthRole(UserInfoRoleDto userInfoRoleDto) {
        boolean success = false;
        if (StrUtil.isNotBlank(userInfoRoleDto.getUserId()) && userInfoRoleDto.getRoleIds() != null) {
            // 删除关联表
            userRoleMapper.delete(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, userInfoRoleDto.getUserId()));
            // 添加关联表
            userInfoRoleDto.getRoleIds().forEach(roleId -> userRoleMapper.insert(new UserRole().setUserId(userInfoRoleDto.getUserId()).setRoleId(roleId)));
            success = true;
        }
        if (success) {
            // 删除缓存
            redisTemplate.delete(Constant.REDIS_USER_MODULE_LIST_KEY + userInfoRoleDto.getUserId());
            // 在查询权限
            List<Map<String, Object>> resourcesList = baseMapper.getResourcesByUserId(userInfoRoleDto.getUserId());
            redisTemplate.opsForValue().set(Constant.REDIS_USER_MODULE_LIST_KEY + userInfoRoleDto.getUserId(), resourcesList);
        }
        return success;
    }
}
