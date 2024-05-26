package com.graduate.work.entity_and_event_generator.service.random.generator;

import com.graduate.work.model.entity.Modem;
import com.graduate.work.model.entity.SimCard;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ModemInitialStateGenerator extends InitialStateGenerator<Modem> {

    @Override
    public Modem create() {
        Modem.Status status = Modem.Status.INACTIVE;
        String imei = randomizer.number().numberBetween(100000000000000L, 9999999999999999L) + "";
        Map<Long, SimCard> simCardMap = new HashMap<>();
        return Modem.builder()
                .status(status)
                .imei(imei)
                .simCards(simCardMap)
                .build();
    }
}
