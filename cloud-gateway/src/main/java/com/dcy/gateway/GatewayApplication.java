package com.dcy.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dcy
 */
@SpringBootApplication(scanBasePackages = {"com.dcy.gateway", "com.dcy.redis"})
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
