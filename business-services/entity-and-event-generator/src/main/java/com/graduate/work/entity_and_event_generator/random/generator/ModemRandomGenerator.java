package com.graduate.work.entity_and_event_generator.random.generator;

import com.graduate.work.entity_and_event_generator.random.RandomGenerator;
import com.graduate.work.model.entity.Modem;
import org.springframework.stereotype.Component;

@Component
public class ModemRandomGenerator extends RandomGenerator {

    @Override
    public Modem create() {
        String imei = faker.number().numberBetween(100000000000000L, 9999999999999999L) + "";
        return Modem.builder()
                .imei(imei)
                .build();
    }
}
