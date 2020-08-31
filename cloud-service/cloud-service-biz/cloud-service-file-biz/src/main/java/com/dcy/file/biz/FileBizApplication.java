package com.dcy.file.biz;

import com.dcy.file.biz.properties.FileServerProperties;
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
@MapperScan(basePackages = {"com.dcy.file.biz.mapper"})
@EnableApiBootSwagger
public class FileBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileBizApplication.class, args);
    }

}
