package com.graduate.work.entity_and_event_generator.random.generator;

import com.graduate.work.model.entity.SimCard;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
class SimCardRandomGeneratorTest {

    @Autowired
    private SimCardRandomGenerator simCardRandomGenerator;

    @Test
    void generate() {
        SimCard simCard = simCardRandomGenerator.create();
        log.info(simCard.toString());
        assertNotNull(simCard);
    }

}