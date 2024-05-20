package com.graduate.work.entity_and_event_generator.random.relationship;

import com.github.javafaker.Faker;
import com.graduate.work.entity_and_event_generator.random.event.EventRandomExecutor;
import com.graduate.work.entity_and_event_generator.repository.ModemRepository;
import com.graduate.work.model.entity.Modem;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.graduate.work.entity_and_event_generator.random.data.Modem.COUNT_SIM_CARDS;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(properties =
        """
                spring.datasource.url=jdbc:h2:mem:db;DB_CLOSE_DELAY=-1;NON_KEYWORDS=KEY,VALUE
                spring.datasource.driverClassName=org.h2.Driver
                spring.datasource.driver-class-name=org.h2.Driver"""
)
class SimCardRelationHandlerTest {

    @Setter(onMethod_ = {@Autowired})
    Faker faker;

    @Setter(onMethod_ = {@Autowired})
    ModemRelationHandler modemRelationHandler;

    @Setter(onMethod_ = {@Autowired})
    ModemRepository modemRepository;

    @Setter(onMethod_ = {@Autowired})
    EventRandomExecutor eventRandomExecutor;

    @Test
    @Transactional
    void fullModem(){
        eventRandomExecutor.execute();
        var list = modemRepository.findAll();
        Modem modem = list.get(faker.number().numberBetween(0, list.size() - 1));
        modemRelationHandler.getFullModem().activate(modem);
        assertEquals(modem.getSizeSimCards(), COUNT_SIM_CARDS);
    }


}