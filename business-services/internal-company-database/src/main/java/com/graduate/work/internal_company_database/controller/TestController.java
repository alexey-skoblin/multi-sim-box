package com.graduate.work.internal_company_database.controller;

import com.graduate.work.internal_company_database.InternalCompanyDatabaseApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@RestController
@Slf4j
public class TestController {
//    static Flux<Long> flux = Flux.interval(Duration.of(1, ChronoUnit.SECONDS));
//    static ConnectableFlux<Long> connectableFlux;
//
//    private void initFlux() {
//        connectableFlux = flux.publish();
//        connectableFlux.connect();
//    }

    @GetMapping( value = "/test", produces = TEXT_EVENT_STREAM_VALUE)
    public Mono<String> test(@RequestParam String text) {
//        if (connectableFlux == null) {
//            initFlux();
//        }
//        return connectableFlux;
        log.info("Input: " + text);
        return Mono.just(InternalCompanyDatabaseApplication.class.getSimpleName());
    }

}
