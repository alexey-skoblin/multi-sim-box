package com.graduate.work.automated_workstation.output;

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
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.time.LocalDateTime;

@Configuration
@Slf4j
public class TestService {

    private final WebClient webClient;

    public TestService(WebClient.Builder builder) {
        this.webClient = builder.build();
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
                .retrieve()
                .bodyToMono(String.class)
                .retryWhen(Retry.max(3))
                .subscribe(x->log.info("Output: " + x));
    }
}