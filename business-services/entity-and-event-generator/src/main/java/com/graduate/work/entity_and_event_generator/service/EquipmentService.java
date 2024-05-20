package com.graduate.work.entity_and_event_generator.service;

import com.github.javafaker.Faker;
import com.graduate.work.entity_and_event_generator.random.event.Executable;
import com.graduate.work.entity_and_event_generator.random.generator.EquipmentInitialStateGenerator;
import com.graduate.work.entity_and_event_generator.random.updater.EquipmentDataUpdater;
import com.graduate.work.entity_and_event_generator.repository.EquipmentRepository;
import com.graduate.work.model.entity.Equipment;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EquipmentService implements Executable<Equipment> {

    @Setter(onMethod_ = {@Autowired})
    private Faker faker;
    @Setter(onMethod_ = {@Autowired})
    private EquipmentInitialStateGenerator equipmentRandomGenerator;
    @Setter(onMethod_ = {@Autowired})
    private EquipmentDataUpdater equipmentRandomUpdater;
    @Setter(onMethod_ = {@Autowired})
    private EquipmentRepository equipmentRepository;

    @Override
    public List<Equipment> getAll() {
        return equipmentRepository.findAll();
    }

    @Override
    public Equipment getRandom() {
        List<Long> list = equipmentRepository.getAllIds();
        Long id = list.get(faker.number().numberBetween(0, list.size() - 1));
        return equipmentRepository.findById(id).orElse(null);
    }

    @Override
    public Equipment add() {
        Equipment equipment = equipmentRandomGenerator.create();
        equipmentRepository.saveAndFlush(equipment);
        return equipment;
    }

    @Override
    public Equipment update() {
        Equipment equipment = getRandom();
        if (equipment == null) {
            equipment = add();
        }
        equipment = equipmentRandomUpdater.update(equipment);
        equipmentRepository.save(equipment);
        return equipment;
    }
}
