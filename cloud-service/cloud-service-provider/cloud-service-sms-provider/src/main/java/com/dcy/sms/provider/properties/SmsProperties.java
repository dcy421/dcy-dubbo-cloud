package com.dcy.sms.provider.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static com.dcy.sms.provider.properties.SmsProperties.DCY_BOOT_SMS_PREFIX;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2020/9/16 9:17
 */
@Data
@Configuration
@ConfigurationProperties(prefix = DCY_BOOT_SMS_PREFIX)
public class SmsProperties {

    /**
     * sms 配置前缀
     */
    public static final String DCY_BOOT_SMS_PREFIX = "dcy.boot.sms";
    /**
     * RAM账号的AccessKey ID
     */
    private String accessKeyId;
    /**
     * RAM账号Access Key Secret
     */
    private String accessKeySecret;
    /**
     *  版本
     */
    private String version;
    /**
     * 区域
     */
    private String regionId;

}
