package com.graduate.work.mobile_operator_personal_account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
public class MobileOperatorPersonalAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobileOperatorPersonalAccountApplication.class, args);
    }

}
