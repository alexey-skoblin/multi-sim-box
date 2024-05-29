package com.graduate.work.automated_workstation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@EnableFeignClients
@EntityScan(basePackages = "com.graduate.work.model.entity")
public class AutomatedWorkstationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutomatedWorkstationApplication.class, args);
    }

}
