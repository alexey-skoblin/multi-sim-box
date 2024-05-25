package com.graduate.work.entity_and_event_generator.random.generator;

import com.graduate.work.model.entity.Client;
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
class ClientInitialStateGeneratorTest {

    ClientInitialStateGenerator clientRandomGenerator;

    @Test
    void generate() {
        Client client = clientRandomGenerator.create();
        log.info(client.toString());
        assertNotNull(client);
    }
}