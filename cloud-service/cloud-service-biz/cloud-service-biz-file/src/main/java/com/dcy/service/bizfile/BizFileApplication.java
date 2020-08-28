package com.dcy.service.bizfile;

import com.dcy.service.bizfile.properties.FileServerProperties;
import org.minbox.framework.api.boot.autoconfigure.swagger.annotation.EnableApiBootSwagger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author dcy
 * -javaagent:E:\Installation\skywalking\apache-skywalking-apm-6.6.0\agent\skywalking-agent.jar
 * -Dskywalking.agent.service_name=file-center
 */
@SpringBootApplication(scanBasePackages = "com.dcy")
@EnableConfigurationProperties(FileServerProperties.class)
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.dcy.service.bizfile.mapper"})
@EnableApiBootSwagger
public class BizFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(BizFileApplication.class, args);
    }

}
