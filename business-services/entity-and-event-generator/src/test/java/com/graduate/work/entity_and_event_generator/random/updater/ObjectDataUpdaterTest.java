package com.graduate.work.entity_and_event_generator.random.updater;

import com.graduate.work.entity_and_event_generator.random.generator.ObjectInitialStateGenerator;
import com.graduate.work.model.entity.Object;
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
class ObjectDataUpdaterTest {

    ObjectInitialStateGenerator objectRandomGenerator;
    ObjectDataUpdater objectRandomUpdater;

    @Autowired
    public void setObjectRandomGenerator(ObjectInitialStateGenerator objectRandomGenerator) {
        this.objectRandomGenerator = objectRandomGenerator;
    }

    @Autowired
    public void setObjectRandomUpdater(ObjectDataUpdater objectRandomUpdater) {
        this.objectRandomUpdater = objectRandomUpdater;
    }

    @Test
    void updateStatus(){
        Object object = objectRandomGenerator.create();
        log.info(object.toString());
        objectRandomUpdater.getUpdateStatus().activate(object);
        log.info(object.toString());
        assertNotNull(object.getStatus());
    }
}