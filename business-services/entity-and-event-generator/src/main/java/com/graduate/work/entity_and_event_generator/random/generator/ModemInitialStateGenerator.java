package com.graduate.work.entity_and_event_generator.random.generator;

import com.graduate.work.model.entity.Modem;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ModemInitialStateGenerator extends InitialStateGenerator {

    @Override
    public Modem create() {
        Modem.Status status = Modem.Status.INACTIVE;
        String imei = faker.number().numberBetween(100000000000000L, 9999999999999999L) + "";
        return Modem.builder()
                .status(status)
                .imei(imei)
                .build();
    }
}
