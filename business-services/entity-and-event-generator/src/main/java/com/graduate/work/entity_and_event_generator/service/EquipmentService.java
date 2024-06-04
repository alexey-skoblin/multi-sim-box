package com.graduate.work.entity_and_event_generator.service;

import com.graduate.work.entity_and_event_generator.random.Randomizer;
import com.graduate.work.entity_and_event_generator.random.executor.Executable;
import com.graduate.work.entity_and_event_generator.random.generator.EquipmentInitialStateGenerator;
import com.graduate.work.entity_and_event_generator.random.updater.external.EquipmentExternalUpdater;
import com.graduate.work.entity_and_event_generator.random.updater.internal.EquipmentInternalUpdater;
import com.graduate.work.entity_and_event_generator.repository.jpa.EquipmentRepository;
import com.graduate.work.model.entity.Client;
import com.graduate.work.model.entity.Equipment;
import com.graduate.work.model.entity.Modem;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Setter(onMethod_ = {@Autowired})
public class EquipmentService implements Executable<Equipment> {

    private Randomizer randomizer;
    private EquipmentInitialStateGenerator equipmentRandomGenerator;
    private EquipmentInternalUpdater equipmentInternalUpdater;
    @Setter(onMethod_ = {@Autowired, @Lazy})
    private EquipmentExternalUpdater equipmentExternalUpdater;
    private EquipmentRepository equipmentRepository;

    @Lazy
    private ModemService modemService;

    @Override
    public Equipment add() {
        Equipment equipment = equipmentRandomGenerator.create();
        equipmentRepository.saveAndFlush(equipment);
        return equipment;
    }

    public List<Equipment> getAll() {
        return equipmentRepository.findAll();
    }

    @Override
    public Equipment getRandom() {
        List<Long> list = equipmentRepository.getAllIds();
        if (list.isEmpty()) {
            return add();
        }
        Long id = list.get(randomizer.getRandomId(list.size()));
        Equipment equipment = equipmentRepository.findById(id).orElse(null);
        if (equipment == null) {
            return add();
        }
        return equipment;
    }

    @Override
    public Equipment update() {
        Equipment equipment = getRandom();
        equipment = equipmentInternalUpdater.update(equipment);
        equipment = equipmentExternalUpdater.update(equipment);
        equipmentRepository.save(equipment);
        return equipment;
    }


    public Optional<Client> getClient(Equipment equipment) {
        if (equipment == null) {
            return Optional.empty();
        }
        if (equipment.getFacility() != null) {
            Client client = equipment.getFacility().getClient();
            if (client != null) {
                return Optional.of(client);
            }
        }
        for (Modem modem : equipment.getModems().values()) {
            Optional<Client> client = modemService.getClient(modem);
            if (client.isPresent()) {
                return client;
            }
        }
        return Optional.empty();
    }

    public Equipment getRandomByFacilityNull() {
        List<Equipment> list = equipmentRepository.findByFacilityNull();
        if (list.isEmpty()) {
            return add();
        }
        return list.get(randomizer.getRandomId(list.size()));
    }
}
