package com.graduate.work.entity_and_event_generator.random.generator;

import com.graduate.work.model.entity.Client;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
class ClientRandomGeneratorTest {

    @Autowired
    private ClientRandomGenerator clientRandomGenerator;

    @Test
    void generate() {
        Client client = clientRandomGenerator.create();
        log.info(client.toString());
        assertNotNull(client);
    }
}