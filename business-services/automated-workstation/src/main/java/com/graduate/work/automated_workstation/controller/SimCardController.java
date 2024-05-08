package com.graduate.work.automated_workstation.controller;

import com.graduate.work.model.entity.SimCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin
public class SimCardController {

    @GetMapping(value = "/simCard")
    public SimCard getSimCard() {
        SimCard simCard = SimCard.builder()
                .id(1L)
                .iccid("12345678901234567890")
//                .lastActivation(LocalDateTime.now().getNano())
//                .lastLocation(new Point(140,43))
                .defNumber("123456789")
                .status(SimCard.Status.ACTIVE)
                .mobileOperator("MobOperator")
                .tariff("Tariff")
                .trafficForYesterday(0D)
                .build();
        log.info(simCard.toString());
        return simCard;
    }

}