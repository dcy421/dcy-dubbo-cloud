package com.dcy.service.apiadmin.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dcy.service.apiadmin.model.UserInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author dcy
 * @since 2020-08-19
 */
public interface UserInfoService extends IService<UserInfo> {

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
}
