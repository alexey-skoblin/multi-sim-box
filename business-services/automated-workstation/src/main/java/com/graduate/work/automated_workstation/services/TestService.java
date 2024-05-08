package com.graduate.work.automated_workstation.services;

import com.graduate.work.automated_workstation.AutomatedWorkstationApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.time.LocalDateTime;

@Configuration
@Slf4j
public class TestService {


    private final RestClient webClient;

    public TestService() {
        this.webClient = RestClient.create();
    }

    @Scheduled(fixedRate = 1000)
    public void run1(){
        //get("internal-company-database-client-service");
    }

    @Scheduled(fixedRate = 1000)
    public void run2(){
        //get("mobile-operator-personal-account-client-service");
    }

    public void get(String url){
        String request = AutomatedWorkstationApplication.class.getSimpleName();
        webClient.get()
                .uri("http://" + url + "/test?text=" + request)
                .retrieve();
    }
}