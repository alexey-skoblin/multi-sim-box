package com.graduate.work.entity_and_event_generator.random.updater.internal;

import com.graduate.work.entity_and_event_generator.service.random.generator.FacilityInitialStateGenerator;
import com.graduate.work.entity_and_event_generator.service.random.updater.internal.FacilityInternalUpdater;
import com.graduate.work.model.entity.Facility;
import lombok.Setter;
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
@Setter(onMethod_ = {@Autowired})
class FacilityInternalUpdaterTest {

    FacilityInitialStateGenerator objectRandomGenerator;
    FacilityInternalUpdater objectRandomUpdater;

    @Test
    void updateStatus(){
        Facility facility = objectRandomGenerator.create();
        log.info(facility.toString());
        facility.setStatus(Facility.Status.ACTIVE);
        log.info(facility.toString());
        assertNotNull(facility.getStatus());
    }
}