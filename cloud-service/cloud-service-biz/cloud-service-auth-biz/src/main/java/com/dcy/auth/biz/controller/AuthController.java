package com.dcy.auth.biz.controller;

import com.alibaba.fastjson.JSON;
import com.dcy.admin.api.model.UserInfo;
import com.dcy.common.constant.Constant;
import com.dcy.common.model.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2020-02-14 13:32
 */
@RestController
@Api(value = "AuthController", tags = {"鉴权操作接口"})
public class AuthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;

    @Autowired
    private TokenStore tokenStore;

    /**
     * 重写 /auth/token 方法
     *
     * @param principal
     * @param parameters
     * @return
     */
    @ApiOperation(value = "重写 /auth/token 方法", notes = "重写 /auth/token 方法")
    @GetMapping("/oauth/token")
    public ResponseData<OAuth2AccessToken> getAccessToken(Principal principal, @RequestParam Map<String, String> parameters) {
        ResponseEntity<OAuth2AccessToken> accessToken = null;
        try {
            accessToken = tokenEndpoint.getAccessToken(principal, parameters);
        } catch (Exception ex) {
            return ResponseData.error("登录失败");
        }
        return ResponseData.success(accessToken.getBody());
    }

    /**
     * 重写 /auth/token 方法
     *
     * @param principal
     * @param parameters
     * @return
     */
    @ApiOperation(value = "重写 /auth/token 方法", notes = "重写 /auth/token 方法")
    @PostMapping("/oauth/token")
    public ResponseData<OAuth2AccessToken> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) {
        ResponseEntity<OAuth2AccessToken> accessToken = null;
        try {
            accessToken = tokenEndpoint.postAccessToken(principal, parameters);
        } catch (Exception ex) {
            return ResponseData.error("登录失败");
        }
        return ResponseData.success(accessToken.getBody());
    }

    /**
     * 暴露Remote Token Services接口
     * 获取登录信息
     * 如果其它服务需要验证Token，则需要远程调用授权服务暴露的验证Token的API接口
     *
     * @return
     */
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    @GetMapping(value = "/getUserInfo")
    public ResponseData<UserInfo> getUser() {
        String tokenValue = ((OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getTokenValue();
        Object userInfoMap = tokenStore.readAccessToken(tokenValue).getAdditionalInformation().get(Constant.USER_INFO);
        return ResponseData.success(JSON.parseObject(JSON.toJSONString(userInfoMap), UserInfo.class));
    }

    /**
     * 获取OAuth用户信息
     *
     * @param principal
     * @return
     */
    @ApiOperation(value = "获取OAuth用户信息", notes = "获取OAuth用户信息")
    @GetMapping(value = "/getOAuthDetails")
    public Principal getOAuthDetails(Principal principal) {
        return principal;
    }
}
