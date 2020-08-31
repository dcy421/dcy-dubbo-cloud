package com.dcy.auth.biz.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.dcy.auth.biz.model.AuthUser;
import com.dcy.common.constant.Constant;
import com.dcy.admin.api.api.UserInfoService;
import com.dcy.admin.api.model.UserInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author dcy
 * @Date: 2019/3/19 16:31
 * @Description:
 */
@Service
public class UserServiceDetailImpl implements UserDetailsService {

    @DubboReference(version = "1.0.0")
    private UserInfoService userInfoService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Basic ZGN5X2FkbWluX2NsaWVudDoxMjM0NTY=
        //查询用户信息
        UserInfo userInfo = userInfoService.getUserInfoByUsername(username);
        if (userInfo != null) {
            // 查询权限
            List<Map<String, Object>> resources = userInfoService.getResourcesByUserId(userInfo.getId());
            if (CollUtil.isNotEmpty(resources)) {
                Set<String> userIdSet = resources.stream().map(res -> StrUtil.toString(res.get("resCode"))).collect(Collectors.toSet());
                redisTemplate.opsForValue().set(Constant.REDIS_USER_MODULE_LIST_KEY + userInfo.getId(), resources);
                AuthUser authUser = new AuthUser();
                authUser.setUserInfo(userInfo);
                userInfo.setResources(userIdSet);
                return authUser;
            }
            return null;
        }
        return null;
    }
}
