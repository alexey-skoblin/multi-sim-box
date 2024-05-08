package com.graduate.work.entity_and_event_generator.random.generator;

import com.graduate.work.model.entity.Equipment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
class EquipmentRandomGeneratorTest {

    @Autowired
    private EquipmentRandomGenerator equipmentRandomGenerator;

    @Test
    void generate() {
        Equipment equipment = equipmentRandomGenerator.create();
        log.info(equipment.toString());
        assertNotNull(equipment);
    }
}