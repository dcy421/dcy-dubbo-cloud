package com.dcy.service.bizoauth;

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
public class AuthCenterProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthCenterProviderApplication.class, args);
    }
}
