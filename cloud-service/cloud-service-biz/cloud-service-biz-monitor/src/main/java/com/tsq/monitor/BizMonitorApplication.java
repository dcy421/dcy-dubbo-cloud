package com.tsq.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dcy
 * -javaagent:E:\Installation\skywalking\apache-skywalking-apm-6.6.0\agent\skywalking-agent.jar
 * -Dskywalking.agent.service_name=monitor-center
 */
@SpringBootApplication
@EnableAdminServer
public class BizMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(BizMonitorApplication.class, args);
    }

}
