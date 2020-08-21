package com.dcy.service.bizadmin.controller;

import cn.hutool.core.util.StrUtil;
import com.dcy.common.model.ResponseData;
import com.dcy.common.utils.JwtUtil;
import com.dcy.service.apiadmin.api.UserInfoService;
import com.dcy.service.apiadmin.model.UserInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2020/8/19 10:41
 */
@RestController
public class LoginController {

    @DubboReference(version = "1.0.0")
    private UserInfoService userInfoService;

    /**
     * 登录
     *
     * @param userInfo
     * @return
     */
    @PostMapping(value = "/login")
    public ResponseData<String> login(UserInfo userInfo) {
        String token = userInfoService.login(userInfo);
        if (StrUtil.isNotBlank(token)) {
            return ResponseData.success(token);
        }
        return ResponseData.error("登录失败");
    }

    /**
     * 获取用户信息
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/getUserInfo")
    public ResponseData<UserInfo> getUserInfo(HttpServletRequest request) {
        // 取header的
        String authorization = request.getHeader("authorization");
        if (StrUtil.isNotBlank(authorization)) {
            UserInfo userInfo = userInfoService.getUserInfo(authorization);
            if (userInfo != null) {
                return ResponseData.success(userInfo);
            }
        }
        // 取cookie
        String token = JwtUtil.getAuthToken(request);
        if (StrUtil.isNotBlank(token)) {
            UserInfo userInfo = userInfoService.getUserInfo(token);
            if (userInfo != null) {
                return ResponseData.success(userInfo);
            }
        }
        return ResponseData.error("获取用户信息失败");
    }
}
