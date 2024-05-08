package com.graduate.work.entity_and_event_generator.random.generator;

import com.graduate.work.entity_and_event_generator.random.RandomGenerator;
import com.graduate.work.model.entity.Equipment;
import org.springframework.stereotype.Component;

@Component
public class EquipmentRandomGenerator extends RandomGenerator {

    @Override
    public Equipment create() {
        String hostname = faker.internet().domainName();
        String model = "MCR-202";
        String serialNumber = faker.number().numberBetween(100000, 999999) + "-" + faker.number().numberBetween(100000, 999999) + "-" + faker.number().numberBetween(100000, 999999);
        return Equipment.builder()
                .hostname(hostname)
                .model(model)
                .serialNumber(serialNumber)
                .build();
    }
}
