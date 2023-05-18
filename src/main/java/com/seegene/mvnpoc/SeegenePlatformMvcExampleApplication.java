package com.seegene.mvnpoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EntityScan(basePackages = {"com.seegene.mvnpoc.web.api.v1.demo.domain.entity"})
@SpringBootApplication
public class SeegenePlatformMvcExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeegenePlatformMvcExampleApplication.class, args);
    }

}
