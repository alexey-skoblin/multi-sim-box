package com.graduate.work.entity_and_event_generator.service;

import com.github.javafaker.Faker;
import com.graduate.work.entity_and_event_generator.random.event.Executable;
import com.graduate.work.entity_and_event_generator.random.generator.SimCardInitialStateGenerator;
import com.graduate.work.entity_and_event_generator.random.updater.SimCardDataUpdater;
import com.graduate.work.entity_and_event_generator.repository.SimCardRepository;
import com.graduate.work.model.entity.SimCard;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SimCardService implements Executable<SimCard> {

    @Setter(onMethod_ = {@Autowired})
    private Faker faker;
    @Setter(onMethod_ = {@Autowired})
    private SimCardInitialStateGenerator simCardRandomGenerator;
    @Setter(onMethod_ = {@Autowired})
    private SimCardDataUpdater simCardRandomUpdater;
    @Setter(onMethod_ = {@Autowired})
    private SimCardRepository simCardRepository;

    @Override
    public List<SimCard> getAll() {
        return simCardRepository.findAll();
    }

    @Override
    public SimCard getRandom() {
        List<Long> list = simCardRepository.getAllIds();
        Long id = list.get(faker.number().numberBetween(0, list.size() - 1));
        return simCardRepository.findById(id).orElse(null);
    }

    @Override
    public SimCard add() {
        SimCard simCard = simCardRandomGenerator.create();
        simCardRepository.saveAndFlush(simCard);
        return simCard;
    }

    @Override
    public SimCard update() {
        SimCard simCard = getRandom();
        if (simCard == null) {
            simCard = add();
        }
        simCard = simCardRandomUpdater.update(simCard);
        simCardRepository.save(simCard);
        return simCard;
    }

}
