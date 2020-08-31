package com.dcy.auth.biz;

import org.minbox.framework.api.boot.autoconfigure.swagger.annotation.EnableApiBootSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author dcy
 * -javaagent:E:\Installation\skywalking\apache-skywalking-apm-6.6.0\agent\skywalking-agent.jar
 * -Dskywalking.agent.service_name=auth-center
 */
@SpringBootApplication(scanBasePackages = "com.dcy")
@EnableResourceServer
@EnableApiBootSwagger
public class AuthBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthBizApplication.class, args);
    }
}
