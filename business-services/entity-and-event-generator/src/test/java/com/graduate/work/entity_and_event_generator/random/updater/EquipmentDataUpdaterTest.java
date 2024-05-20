package com.graduate.work.entity_and_event_generator.random.updater;

import com.graduate.work.entity_and_event_generator.random.generator.EquipmentInitialStateGenerator;
import com.graduate.work.model.entity.Equipment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(properties =
        """
                spring.datasource.url=jdbc:h2:mem:db;DB_CLOSE_DELAY=-1;NON_KEYWORDS=KEY,VALUE
                spring.datasource.driverClassName=org.h2.Driver
                spring.datasource.driver-class-name=org.h2.Driver"""
)
class EquipmentDataUpdaterTest {

    EquipmentInitialStateGenerator equipmentRandomGenerator;
    EquipmentDataUpdater equipmentRandomUpdater;

    @Autowired
    public void setEquipmentRandomGenerator(EquipmentInitialStateGenerator equipmentRandomGenerator) {
        this.equipmentRandomGenerator = equipmentRandomGenerator;
    }

    @Autowired
    public void setEquipmentRandomUpdater(EquipmentDataUpdater equipmentRandomUpdater) {
        this.equipmentRandomUpdater = equipmentRandomUpdater;
    }

    @Test
    void updateHostName() {
        Equipment equipment = equipmentRandomGenerator.create();
        log.info(equipment.toString());
        equipmentRandomUpdater.getUpdateHostName().activate(equipment);
        log.info(equipment.toString());
        assertNotNull(equipment.getHostname());
    }
}