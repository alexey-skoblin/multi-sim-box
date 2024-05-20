package com.graduate.work.entity_and_event_generator.service;

import com.github.javafaker.Faker;
import com.graduate.work.entity_and_event_generator.random.event.Executable;
import com.graduate.work.entity_and_event_generator.random.generator.ClientInitialStateGenerator;
import com.graduate.work.entity_and_event_generator.random.updater.ClientDataUpdater;
import com.graduate.work.entity_and_event_generator.repository.ClientRepository;
import com.graduate.work.model.entity.Client;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClientService implements Executable<Client> {

    @Setter(onMethod_ = {@Autowired})
    private Faker faker;
    @Setter(onMethod_ = {@Autowired})
    private ClientInitialStateGenerator clientRandomGenerator;
    @Setter(onMethod_ = {@Autowired})
    private ClientDataUpdater clientRandomUpdater;
    @Setter(onMethod_ = {@Autowired})
    private ClientRepository clientRepository;

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client getRandom() {
        List<Long> list = clientRepository.getAllIds();
        Long id = list.get(faker.number().numberBetween(0, list.size() - 1));
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public Client add() {
        Client client = clientRandomGenerator.create();
        clientRepository.saveAndFlush(client);
        return client;
    }

    @Override
    public Client update() {
        Client client = getRandom();
        if (client == null) {
            client = add();
        }
        client = clientRandomUpdater.update(client);
        clientRepository.save(client);
        return client;
    }

}
