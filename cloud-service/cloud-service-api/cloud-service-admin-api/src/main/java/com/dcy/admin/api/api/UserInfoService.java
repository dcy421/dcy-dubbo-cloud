package com.dcy.admin.api.api;

import com.dcy.admin.api.dto.UserInfoRoleDto;
import com.dcy.admin.api.model.Role;
import com.dcy.db.base.service.BaseService;
import com.dcy.admin.api.model.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author dcy
 * @since 2020-08-19
 */
public interface UserInfoService extends BaseService<UserInfo> {

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    UserInfo getUserInfoByUsername(String username);

    /**
     * 根据用户id获取权限信息
     *
     * @param userId
     * @return
     */
    List<Map<String, Object>> getResourcesByUserId(String userId);

    /**
     * 根据用户id 查询已授权角色列表
     *
     * @param userId
     * @return
     */
    List<Role> getAuthRoleListByUserId(String userId);

    /**
     * 保存授权角色
     * @param userInfoRoleDto
     * @return
     */
    Boolean saveAuthRole(UserInfoRoleDto userInfoRoleDto);
}
