package com.graduate.work.entity_and_event_generator.random.updater.external;

import com.graduate.work.entity_and_event_generator.random.Randomizer;
import com.graduate.work.entity_and_event_generator.random.updater.Updater;
import com.graduate.work.entity_and_event_generator.repository.*;
import com.graduate.work.entity_and_event_generator.service.*;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Setter(onMethod_ = {@Autowired})
public abstract class ExternalUpdater<T>  extends Updater<T> {

    Randomizer randomizer;

    ClientRepository clientRepository;
    EquipmentRepository equipmentRepository;
    ModemRepository modemRepository;
    FacilityRepository facilityRepository;
    SimCardRepository simCardRepository;

    ClientService clientService;
    EquipmentService equipmentService;
    ModemService modemService;
    FacilityService facilityService;
    SimCardService simCardService;

}
