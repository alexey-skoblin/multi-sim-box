package com.graduate.work.entity_and_event_generator.random.relationship;

import com.github.javafaker.Faker;
import com.graduate.work.entity_and_event_generator.repository.*;
import com.graduate.work.entity_and_event_generator.service.*;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class RelationHandler<T> {


    @Setter(onMethod_ = {@Autowired})
    Faker faker;

    @Setter(onMethod_ = {@Autowired})
    ClientRepository clientRepository;
    @Setter(onMethod_ = {@Autowired})
    EquipmentRepository equipmentRepository;
    @Setter(onMethod_ = {@Autowired})
    ModemRepository modemRepository;
    @Setter(onMethod_ = {@Autowired})
    ObjectRepository objectRepository;
    @Setter(onMethod_ = {@Autowired})
    SimCardRepository simCardRepository;

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

    abstract List<RandomAction<T>> getRandomActions();

    public T manipulate(T t) {
        List<RandomAction<T>> randomActions = getRandomActions();
        RandomAction<T> randomAction = randomActions.get(faker.number().numberBetween(0, randomActions.size() - 1));
        return randomAction.activate(t);
    }

    interface RandomAction<T> {
        T activate(T obj);
    }

}
