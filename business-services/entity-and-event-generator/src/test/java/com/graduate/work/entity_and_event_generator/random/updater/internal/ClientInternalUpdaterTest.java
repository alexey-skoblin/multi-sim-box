package com.graduate.work.entity_and_event_generator.random.updater.internal;

import com.graduate.work.entity_and_event_generator.random.generator.ClientInitialStateGenerator;
import com.graduate.work.model.entity.Client;
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
class ClientInternalUpdaterTest {

    ClientInitialStateGenerator clientRandomGenerator;
    ClientInternalUpdater clientRandomUpdater;

    @Test
    void updateName() {
        Client client = clientRandomGenerator.create();
        log.info(client.toString());
        clientRandomUpdater.getUpdateName().activate(client);
        log.info(client.toString());
        assertNotNull(client.getName());
    }

    @Test
    void updateSurname() {
        Client client = clientRandomGenerator.create();
        log.info(client.toString());
        clientRandomUpdater.getUpdateLastName().activate(client);
        log.info(client.toString());
        assertNotNull(client.getLastName());
    }

    @Test
    void updateEmail() {
        Client client = clientRandomGenerator.create();
        log.info(client.toString());
        clientRandomUpdater.getUpdateEmail().activate(client);
        log.info(client.toString());
        assertNotNull(client.getEmail());
    }

    @Test
    void updateIp() {
        Client client = clientRandomGenerator.create();
        log.info(client.toString());
        clientRandomUpdater.getUpdateIp().activate(client);
        log.info(client.toString());
        assertNotNull(client.getIp());
    }

}