package com.dcy.service.apiadmin.api;

import com.dcy.db.base.service.BaseService;
import com.dcy.service.apiadmin.dto.UserInfoRoleDto;
import com.dcy.service.apiadmin.model.Role;
import com.dcy.service.apiadmin.model.UserInfo;

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
     * 登录
     *
     * @param userInfo
     * @return
     */
    String login(UserInfo userInfo);

    /**
     * 根据加密信息得到用户信息
     *
     * @param token
     * @return
     */
    UserInfo getUserInfo(String token);

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
