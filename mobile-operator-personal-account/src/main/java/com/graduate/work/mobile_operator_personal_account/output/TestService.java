package com.graduate.work.mobile_operator_personal_account.output;

import com.graduate.work.mobile_operator_personal_account.MobileOperatorPersonalAccountApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.LocalDateTime;

@Configuration
@Slf4j
public class TestService {

    private final WebClient webClient;

    public TestService(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    @Scheduled(fixedRate = 1000)
    public void run(){
        get("automated-workstation-client-service");
    }

    public void get(String url){
        String request = MobileOperatorPersonalAccountApplication.class.getSimpleName();
        webClient.get()
                .uri("http://" + url + "/test?text=" + request)
                .retrieve()
                .bodyToMono(String.class)
                .retryWhen(Retry.max(3))
                .subscribe(x->log.info("Output: " + x));
    }

    }