package com.graduate.work.entity_and_event_generator.service;

import com.graduate.work.entity_and_event_generator.random.Randomizer;
import com.graduate.work.entity_and_event_generator.random.executor.Executable;
import com.graduate.work.entity_and_event_generator.random.generator.FacilityInitialStateGenerator;
import com.graduate.work.entity_and_event_generator.random.updater.external.FacilityExternalUpdater;
import com.graduate.work.entity_and_event_generator.random.updater.internal.FacilityInternalUpdater;
import com.graduate.work.entity_and_event_generator.repository.jpa.FacilityRepository;
import com.graduate.work.model.entity.Facility;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Setter(onMethod_ = {@Autowired})
public class FacilityService implements Executable<Facility> {

    private Randomizer randomizer;
    private FacilityInitialStateGenerator objectRandomGenerator;
    private FacilityInternalUpdater facilityInternalUpdater;
    @Setter(onMethod_ = {@Autowired, @Lazy})
    private FacilityExternalUpdater facilityExternalUpdater;
    private FacilityRepository facilityRepository;

    @Override
    public Facility add() {
        Facility facility = objectRandomGenerator.create();
        facilityRepository.saveAndFlush(facility);
        return facility;
    }

    public List<Facility> getAll() {
        return facilityRepository.findAll();
    }

    @Override
    public Facility getRandom() {
        List<Long> list = facilityRepository.getAllIds();
        if (list.isEmpty()) {
            return add();
        }
        Long id = list.get(randomizer.getRandomId(list.size()));
        Facility facility = facilityRepository.findById(id).orElse(null);
        if (facility == null) {
            return add();
        }
        return facility;
    }

    @Override
    public Facility update() {
        Facility facility = getRandom();
        facility = facilityInternalUpdater.update(facility);
        facility = facilityExternalUpdater.update(facility);
        facilityRepository.save(facility);
        return facility;
    }

    public Facility getRandomByClientNull() {
        List<Facility> facilities = facilityRepository.findByClientNull();
        if (facilities.isEmpty()) {
            return add();
        }
        return facilities.get(randomizer.getRandomId(facilities.size()));
    }
}
