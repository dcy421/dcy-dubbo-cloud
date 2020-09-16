package com.dcy.sms.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2020/7/29 8:43
 */
@EnableAutoConfiguration
@ComponentScan("com.dcy")
public class SmsProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmsProviderApplication.class, args);
    }
}
