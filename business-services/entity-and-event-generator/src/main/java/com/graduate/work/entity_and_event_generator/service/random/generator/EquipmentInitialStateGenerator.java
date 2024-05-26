package com.graduate.work.entity_and_event_generator.service.random.generator;

import com.graduate.work.model.entity.Equipment;
import com.graduate.work.model.entity.Modem;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EquipmentInitialStateGenerator extends InitialStateGenerator<Equipment> {

    @Override
    public Equipment create() {
        String hostname = randomizer.internet().domainName();
        String model = "MCR-202";
        String serialNumber = randomizer.number().numberBetween(100000, 999999) + "-" + randomizer.number().numberBetween(100000, 999999) + "-" + randomizer.number().numberBetween(100000, 999999);
        Map<Long, Modem> modemMap = new HashMap<>();
        return Equipment.builder()
                .hostname(hostname)
                .model(model)
                .serialNumber(serialNumber)
                .modems(modemMap)
                .build();
    }
}
