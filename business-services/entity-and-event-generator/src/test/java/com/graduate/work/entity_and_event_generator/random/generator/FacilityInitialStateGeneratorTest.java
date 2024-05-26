package com.graduate.work.entity_and_event_generator.random.generator;

import com.graduate.work.entity_and_event_generator.service.random.generator.FacilityInitialStateGenerator;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(properties =
        """
                spring.datasource.url=jdbc:h2:mem:db;DB_CLOSE_DELAY=-1;NON_KEYWORDS=KEY,VALUE
                spring.datasource.driverClassName=org.h2.Driver
                spring.datasource.driver-class-name=org.h2.Driver"""
)
@Slf4j
@Setter(onMethod_ = {@Autowired})
class FacilityInitialStateGeneratorTest {

    private FacilityInitialStateGenerator objectRandomGenerator;

    @Test
    void generate() {
        Object object = objectRandomGenerator.create();
        log.info(object.toString());
        assertNotNull(object);
    }

}