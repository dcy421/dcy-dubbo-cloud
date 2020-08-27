package com.dcy.service.provideradmin.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dcy.common.constant.Constant;
import com.dcy.db.base.service.impl.BaseServiceImpl;
import com.dcy.service.apiadmin.api.RoleService;
import com.dcy.service.apiadmin.dto.RoleResourceDto;
import com.dcy.service.apiadmin.model.Resources;
import com.dcy.service.apiadmin.model.Role;
import com.dcy.service.apiadmin.model.RoleRes;
import com.dcy.service.provideradmin.mapper.RoleMapper;
import com.dcy.service.provideradmin.mapper.RoleResMapper;
import com.dcy.service.provideradmin.mapper.UserInfoMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author dcy
 * @since 2020-08-19
 */
@DubboService(version = "1.0.0",timeout = 3000)
@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RoleResMapper roleResMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public List<Resources> getAuthResourceListByRoleId(String roleId) {
        return baseMapper.getAuthResourceListByRoleId(roleId);
    }

    @Override
    public Boolean saveAuthResource(RoleResourceDto roleResourceDto) {
        boolean success = false;
        if (StrUtil.isNotBlank(roleResourceDto.getRoleId()) && roleResourceDto.getResIds() != null) {
            // 删除关联表
            roleResMapper.delete(new LambdaQueryWrapper<RoleRes>().eq(RoleRes::getRoleId, roleResourceDto.getRoleId()));
            // 添加关联表
            roleResourceDto.getResIds().forEach(resId -> roleResMapper.insert(new RoleRes().setRoleId(roleResourceDto.getRoleId()).setResId(resId)));
            success = true;
        }
        if (success) {
            // 删除缓存
            redisTemplate.delete(Constant.REDIS_USER_MODULE_LIST_KEY + roleResourceDto.getUserId());
            // 在查询权限
            List<Map<String, Object>> resourcesList = userInfoMapper.getResourcesByUserId(roleResourceDto.getUserId());
            redisTemplate.opsForValue().set(Constant.REDIS_USER_MODULE_LIST_KEY + roleResourceDto.getUserId(), resourcesList);
        }
        return success;
    }
}
