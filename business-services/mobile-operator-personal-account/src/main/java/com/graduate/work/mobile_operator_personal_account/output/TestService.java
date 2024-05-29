package com.graduate.work.mobile_operator_personal_account.output;

import com.graduate.work.mobile_operator_personal_account.MobileOperatorPersonalAccountApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestClient;

@Configuration
@Slf4j
public class TestService {

    private final RestClient webClient;

    public TestService(RestClient.Builder builder) {
        this.webClient = builder.build();
    }

    public void run() {
        get("mobile-operator-personal-account");
    }

    public void get(String url) {
        String request = MobileOperatorPersonalAccountApplication.class.getSimpleName();
        webClient.get()
                .uri("http://" + url + "/test?text=" + request)
                .retrieve();
    }

    public void get() {
        String request = MobileOperatorPersonalAccountApplication.class.getSimpleName();
        webClient.get()
                .uri("http://localhost:8761/eureka/entity-and-event-generator/allClients")
                .retrieve();

    }


}