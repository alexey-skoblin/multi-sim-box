package com.graduate.work.entity_and_event_generator.service;

import com.github.javafaker.Faker;
import com.graduate.work.entity_and_event_generator.random.event.Executable;
import com.graduate.work.entity_and_event_generator.random.generator.ObjectInitialStateGenerator;
import com.graduate.work.entity_and_event_generator.random.updater.ObjectDataUpdater;
import com.graduate.work.entity_and_event_generator.repository.ObjectRepository;
import com.graduate.work.model.entity.Object;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ObjectService implements Executable<Object> {

    @Setter(onMethod_ = {@Autowired})
    private Faker faker;
    @Setter(onMethod_ = {@Autowired})
    private ObjectInitialStateGenerator objectRandomGenerator;
    @Setter(onMethod_ = {@Autowired})
    private ObjectDataUpdater objectRandomUpdater;
    @Setter(onMethod_ = {@Autowired})
    private ObjectRepository objectRepository;

    @Override
    public List<Object> getAll() {
        return objectRepository.findAll();
    }

    @Override
    public Object getRandom() {
        List<Long> list = objectRepository.getAllIds();
        Long id = list.get(faker.number().numberBetween(0, list.size() - 1));
        return objectRepository.findById(id).orElse(null);
    }

    @Override
    public Object add() {
        Object object = objectRandomGenerator.create();
        objectRepository.saveAndFlush(object);
        return object;
    }

    @Override
    public Object update() {
        Object object = getRandom();
        if (object == null) {
            object = add();
        }
        object = objectRandomUpdater.update(object);
        objectRepository.save(object);
        return object;
    }

}
