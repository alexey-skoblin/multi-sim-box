package com.graduate.work.entity_and_event_generator.random;

import com.graduate.work.entity_and_event_generator.service.*;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Setter(onMethod_ = @Autowired)
public class Test {

    private SimCardService simCardService;
    private ClientService clientService;
    private ModemService modemService;
    private EquipmentService equipmentService;
    private FacilityService facilityService;

    //    @Transactional
//    @Scheduled(timeUnit = TimeUnit.SECONDS, fixedDelay = 10)
    public void run() {
        var clients = clientService.getAll();
        var modems = modemService.getAll();
        var equipment = equipmentService.getAll();
        var facilities = facilityService.getAll();
        log.info("test");
    }

}
