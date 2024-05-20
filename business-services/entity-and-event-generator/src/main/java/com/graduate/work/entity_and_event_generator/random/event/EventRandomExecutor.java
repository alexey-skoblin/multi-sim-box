package com.graduate.work.entity_and_event_generator.random.event;

import com.graduate.work.entity_and_event_generator.service.*;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EventRandomExecutor {

    @Setter(onMethod_ = {@Autowired})
    ClientService clientService;
    @Setter(onMethod_ = {@Autowired})
    EquipmentService equipmentService;
    @Setter(onMethod_ = {@Autowired})
    ModemService modemService;
    @Setter(onMethod_ = {@Autowired})
    ObjectService objectService;
    @Setter(onMethod_ = {@Autowired})
    SimCardService simCardService;

    Integer COUNT_CREATING_ENTITIES = 10;
    Integer COUNT_UPDATING_ENTITIES = 10;

//    @Scheduled(fixedDelay = 100000)
    public void execute() {
        log.info("Start Event Random Executor");
        List<Executable<?>> list = List.of(
                clientService,
                equipmentService,
                modemService,
                objectService,
                simCardService
        );
        for (Executable<?> executable : list) {
            log.info("Start add: {}", executable.getClass().getSimpleName());
            executable.run(executable::add, COUNT_CREATING_ENTITIES);
            log.info("Start update: {}", executable.getClass().getSimpleName());
            executable.run(executable::update, COUNT_UPDATING_ENTITIES);
//            log.info("Start getAll: {}", executable.getClass().getSimpleName());
//            executable.run(executable::getAll, 1);
        }
        log.info("End Event Random Executor");
    }

}
