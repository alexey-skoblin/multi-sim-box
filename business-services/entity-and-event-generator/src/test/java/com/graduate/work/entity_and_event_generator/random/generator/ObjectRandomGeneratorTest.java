package com.graduate.work.entity_and_event_generator.random.generator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
class ObjectRandomGeneratorTest {

    @Autowired
    private ObjectRandomGenerator objectRandomGenerator;

    @Test
    void generate() {
        Object object = objectRandomGenerator.create();
        log.info(object.toString());
        assertNotNull(object);
    }

}