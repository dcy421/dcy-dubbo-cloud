package com.dcy.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dcy
 * -Dcsp.sentinel.app.type=1
 * -Dcsp.sentinel.dashboard.server=192.168.109.132:8858
 * -Dproject.name=gateway-center
 * -javaagent:D:\Develop\apache-skywalking-8.1.0\agent\skywalking-agent.jar
 * -Dskywalking.agent.service_name=gateway-center
 */
@SpringBootApplication(scanBasePackages = "com.dcy")
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
