package com.graduate.work.internal_company_database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
public class InternalCompanyDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(InternalCompanyDatabaseApplication.class, args);
    }

}
