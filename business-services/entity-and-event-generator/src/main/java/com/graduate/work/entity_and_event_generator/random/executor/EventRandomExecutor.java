package com.graduate.work.entity_and_event_generator.random.executor;

import com.graduate.work.entity_and_event_generator.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class EventRandomExecutor {

    private static final Integer COUNT_CREATING_ENTITIES = 1;
    private static final Integer COUNT_UPDATING_ENTITIES = 25;
    ClientService clientService;
    EquipmentService equipmentService;
    ModemService modemService;
    FacilityService facilityService;
    SimCardService simCardService;
    TaskService taskService;

    @Scheduled(fixedDelay = 1)
    public void execute() {
//        log.info("Start Event Random Executor");
        List<ExecutableService<?>> list = List.of(
                clientService,
                equipmentService,
                modemService,
                facilityService,
                simCardService,
                taskService
        );
        for (ExecutableService<?> executableService : list) {
//            log.info("Start add: {}", executable.getClass().getSimpleName());
            executableService.run(executableService::add, COUNT_CREATING_ENTITIES);
//            log.info("Start update: {}", executable.getClass().getSimpleName());
            executableService.run(executableService::update, COUNT_UPDATING_ENTITIES);
//            log.info("Start getAll: {}", executable.getClass().getSimpleName());
//            executable.run(executable::getAll, 1);
        }
//        log.info("End Event Random Executor");

    }


}
