package com.graduate.work.entity_and_event_generator.service;

import com.github.javafaker.Faker;
import com.graduate.work.entity_and_event_generator.random.event.Executable;
import com.graduate.work.entity_and_event_generator.random.generator.ModemInitialStateGenerator;
import com.graduate.work.entity_and_event_generator.random.updater.ModemDataUpdater;
import com.graduate.work.entity_and_event_generator.repository.ModemRepository;
import com.graduate.work.model.entity.Modem;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ModemService implements Executable<Modem> {

    @Setter(onMethod_ = {@Autowired})
    private Faker faker;
    @Setter(onMethod_ = {@Autowired})
    private ModemInitialStateGenerator modemRandomGenerator;
    @Setter(onMethod_ = {@Autowired})
    private ModemDataUpdater modemRandomUpdater;
    @Setter(onMethod_ = {@Autowired})
    private ModemRepository modemRepository;

    @Override
    public List<Modem> getAll() {
        return modemRepository.findAll();
    }

    @Override
    public Modem getRandom() {
        List<Long> list = modemRepository.getAllIds();
        Long id = list.get(faker.number().numberBetween(0, list.size() - 1));
        return modemRepository.findById(id).orElse(null);
    }

    @Override
    public Modem add() {
        Modem modem = modemRandomGenerator.create();
        modemRepository.saveAndFlush(modem);
        return modem;
    }

    @Override
    public Modem update() {
        Modem modem = getRandom();
        if (modem == null) {
            modem = add();
        }
        modem = modemRandomUpdater.update(modem);
        modemRepository.save(modem);
        return modem;
    }


}
