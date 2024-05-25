package com.graduate.work.entity_and_event_generator.random.updater.internal;

import com.graduate.work.entity_and_event_generator.random.generator.SimCardInitialStateGenerator;
import com.graduate.work.model.entity.SimCard;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest(properties =
        """
                spring.datasource.url=jdbc:h2:mem:db;DB_CLOSE_DELAY=-1;NON_KEYWORDS=KEY,VALUE
                spring.datasource.driverClassName=org.h2.Driver
                spring.datasource.driver-class-name=org.h2.Driver"""
)
@Setter(onMethod_ = {@Autowired})
class SimCardInternalUpdaterTest {

    SimCardInitialStateGenerator simCardRandomGenerator;
    SimCardInternalUpdater simCardRandomUpdater;

    @Test
    void updateStatusAndLastActionDate() {
        SimCard simCard = simCardRandomGenerator.create();
        log.info(simCard.toString());
        simCard.setStatus(SimCard.Status.ACTIVE);
        log.info(simCard.toString());
        assertNotNull(simCard.getStatus());
    }

    @Test
    void updateDefNumber() {
        SimCard simCard = simCardRandomGenerator.create();
        log.info(simCard.toString());
        simCardRandomUpdater.getUpdateDefNumber().activate(simCard);
        log.info(simCard.toString());
        assertNotNull(simCard.getDefNumber());
    }

    @Test
    void updateMobileOperator(){
        SimCard simCard = simCardRandomGenerator.create();
        log.info(simCard.toString());
        simCardRandomUpdater.getUpdateMobileOperator().activate(simCard);
        log.info(simCard.toString());
        assertNotNull(simCard.getMobileOperator());
    }

    @Test
    void updateTariff(){
        SimCard simCard = simCardRandomGenerator.create();
        log.info(simCard.toString());
        simCardRandomUpdater.getUpdateTariff().activate(simCard);
        log.info(simCard.toString());
        assertNotNull(simCard.getTariff());
    }

    @Test
    void updateLastLocation(){
        SimCard simCard = simCardRandomGenerator.create();
        log.info(simCard.toString());
        simCardRandomUpdater.getUpdateLastLocation().activate(simCard);
        log.info(simCard.toString());
        assertNotNull(simCard.getLastLocation());
    }

    @Test
    void updateTrafficForYesterday(){
        SimCard simCard = simCardRandomGenerator.create();
        log.info(simCard.toString());
        simCardRandomUpdater.getUpdateTrafficForYesterday().activate(simCard);
        log.info(simCard.toString());
        assertNotNull(simCard.getTrafficForYesterday());
    }

}