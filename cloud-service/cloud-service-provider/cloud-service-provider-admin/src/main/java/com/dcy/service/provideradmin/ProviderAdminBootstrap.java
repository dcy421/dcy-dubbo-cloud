package com.dcy.service.provideradmin;

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
@MapperScan(basePackages = {"com.dcy.service.provideradmin.mapper"})
public class ProviderAdminBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(ProviderAdminBootstrap.class, args);
    }
}
