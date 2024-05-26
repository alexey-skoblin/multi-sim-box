package com.graduate.work.entity_and_event_generator.random.updater.internal;

import com.graduate.work.entity_and_event_generator.service.random.generator.ModemInitialStateGenerator;
import com.graduate.work.entity_and_event_generator.service.random.updater.internal.ModemInternalUpdater;
import com.graduate.work.model.entity.Modem;
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
class ModemInternalUpdaterTest {

    ModemInitialStateGenerator modemRandomGenerator;
    ModemInternalUpdater modemRandomUpdater;

    @Test
    void updateStatus() {
        Modem modem = modemRandomGenerator.create();
        log.info(modem.toString());
        modem.setStatus(Modem.Status.ACTIVE);
        log.info(modem.toString());
        assertNotNull(modem.getStatus());
    }


}