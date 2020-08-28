package com.dcy.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dcy
 * -Dcsp.sentinel.app.type=1
 * -Dcsp.sentinel.dashboard.server=192.168.109.132:8858
 * -Dproject.name=gateway-center
 */
@SpringBootApplication(scanBasePackages = "com.dcy")
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
