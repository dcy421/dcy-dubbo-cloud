package com.dcy.service.bizadmin;

import org.minbox.framework.api.boot.autoconfigure.swagger.annotation.EnableApiBootSwagger;
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
@EnableApiBootSwagger
public class BizAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BizAdminApplication.class);
    }
}
