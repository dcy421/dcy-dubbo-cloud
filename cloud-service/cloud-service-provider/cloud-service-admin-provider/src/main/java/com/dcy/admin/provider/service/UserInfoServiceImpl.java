package com.dcy.admin.provider.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dcy.admin.provider.mapper.UserInfoMapper;
import com.dcy.common.constant.Constant;
import com.dcy.db.base.service.impl.BaseServiceImpl;
import com.dcy.admin.api.api.UserInfoService;
import com.dcy.admin.api.dto.UserInfoRoleDto;
import com.dcy.admin.api.model.Role;
import com.dcy.admin.api.model.UserInfo;
import com.dcy.admin.api.model.UserRole;
import com.dcy.admin.provider.mapper.UserRoleMapper;
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
