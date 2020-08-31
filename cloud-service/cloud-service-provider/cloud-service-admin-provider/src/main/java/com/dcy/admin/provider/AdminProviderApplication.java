package com.dcy.admin.provider;

import org.mybatis.spring.annotation.MapperScan;
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
@MapperScan(basePackages = {"com.dcy.admin.provider.mapper"})
public class AdminProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminProviderApplication.class, args);
    }
}
