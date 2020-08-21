package com.dcy.service.bizadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2020/7/29 8:44
 */
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan("com.dcy")
public class BizAdminBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(BizAdminBootstrap.class);
    }
}
