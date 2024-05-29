package com.graduate.work.mobile_operator_personal_account;

import com.graduate.work.mobile_operator_personal_account.output.TestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
public class MobileOperatorPersonalAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobileOperatorPersonalAccountApplication.class, args);
    }

}
