package com.graduate.work.automated_workstation.controllers;

import com.graduate.work.domain.SimCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.time.LocalDateTime;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@RestController
@Slf4j
public class SimCardController {

    @GetMapping(value = "/simCard")
    public Mono<SimCard> getSimCard() {
        SimCard simCard = SimCard.builder()
                .id(1L)
                .iccid("12345678901234567890")
                .lastActivation(LocalDateTime.now())
                .lastLocation(new Point(140,43))
                .defNumber("123456789")
                .status(SimCard.Status.ACTIVE)
                .mobileOperator("MobOperator")
                .tariff("Tariff")
                .trafficForYesterday(0D)
                .build();
      log.info(simCard.toString());
        return Mono.just(simCard);
    }

}
