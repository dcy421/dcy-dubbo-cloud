package com.dcy.sms.provider.config;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.dcy.sms.provider.properties.SmsProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author：dcy
 * @Description: 短信配置
 * @Date: 2020/9/16 9:21
 */
@Configuration
@ConditionalOnClass(CommonRequest.class)
@ConditionalOnProperty(prefix = SmsProperties.DCY_BOOT_SMS_PREFIX, name = {"access-key-id", "access-key-secret", "sign-name"})
@EnableConfigurationProperties(SmsProperties.class)
public class SmsAutoConfiguration {

    /**
     * Sms 属性配置类
     */
    private SmsProperties smsProperties;

    public SmsAutoConfiguration(SmsProperties smsProperties) {
        this.smsProperties = smsProperties;
    }

    @Bean
    public IAcsClient iAcsClient(){
        DefaultProfile profile = DefaultProfile.getProfile(smsProperties.getRegionId(), smsProperties.getAccessKeyId(), smsProperties.getAccessKeySecret());
        return new DefaultAcsClient(profile);
    }
}
