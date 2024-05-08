package com.graduate.work.entity_and_event_generator.random.generator.entity;

import com.graduate.work.model.entity.Modem;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ModemRandomGeneratorTest {

    @Autowired
    private ModemRandomGenerator modemRandomGenerator;

    @Test
    void generate() {
        Modem modem = modemRandomGenerator.create();
        log.info(modem.toString());
        assertNotNull(modem);
    }
}