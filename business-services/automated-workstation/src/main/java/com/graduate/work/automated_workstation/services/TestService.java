package com.graduate.work.automated_workstation.services;

import com.graduate.work.automated_workstation.AutomatedWorkstationApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestClient;

@Configuration
@Slf4j
public class TestService {


    private final RestClient webClient;

    public TestService() {
        this.webClient = RestClient.create();
    }

    @Scheduled(fixedRate = 1000)
    public void run1() {
        //get("internal-company-database-client-service");
    }

    @Scheduled(fixedRate = 1000)
    public void run2() {
        //get("mobile-operator-personal-account-client-service");
    }

    public void get(String url) {
        String request = AutomatedWorkstationApplication.class.getSimpleName();
        webClient.get()
                .uri("http://" + url + "/test?text=" + request)
                .retrieve();
    }
}