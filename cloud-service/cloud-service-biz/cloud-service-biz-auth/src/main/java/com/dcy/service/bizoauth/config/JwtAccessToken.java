package com.dcy.service.bizoauth.config;

import com.dcy.common.constant.Constant;
import com.dcy.service.bizoauth.model.AuthUser;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @author dcy
 * @Date: 2019/3/20 15:25
 * @Description:
 */
@Configuration
public class JwtAccessToken extends JwtAccessTokenConverter {

    /**
     * 生成token
     *
     * @param accessToken
     * @param authentication
     * @return
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
        // 设置额外用户信息
        AuthUser principal = (AuthUser) authentication.getPrincipal();
        principal.getUserInfo().setPassword(null);
        // 将用户信息添加到token额外信息中
        defaultOAuth2AccessToken.getAdditionalInformation().put(Constant.USER_INFO, principal.getUserInfo());
        return super.enhance(defaultOAuth2AccessToken, authentication);
    }

}
