package com.graduate.work.entity_and_event_generator.service;

import com.graduate.work.entity_and_event_generator.random.Randomizer;
import com.graduate.work.entity_and_event_generator.random.event.Executable;
import com.graduate.work.entity_and_event_generator.random.generator.SimCardInitialStateGenerator;
import com.graduate.work.entity_and_event_generator.random.updater.internal.SimCardInternalUpdater;
import com.graduate.work.entity_and_event_generator.repository.SimCardRepository;
import com.graduate.work.model.entity.Client;
import com.graduate.work.model.entity.SimCard;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class SimCardService implements Executable<SimCard> {

    Randomizer randomizer;

    private SimCardInitialStateGenerator simCardRandomGenerator;
    private SimCardInternalUpdater simCardRandomUpdater;
    private SimCardRepository simCardRepository;

    @Override
    public SimCard add() {
        SimCard simCard = simCardRandomGenerator.create();
        simCardRepository.saveAndFlush(simCard);
        return simCard;
    }

    @Override
    public List<SimCard> getAll() {
        return simCardRepository.findAll();
    }

    @Override
    public SimCard getRandom() {
        List<Long> list = simCardRepository.getAllIds();
        if (list.isEmpty()) {
            return add();
        }
        Long id = list.get(randomizer.getRandomId(list.size()));
        SimCard simCard = simCardRepository.findById(id).orElse(null);
        if (simCard == null) {
            return add();
        }
        return simCard;
    }

    @Override
    public SimCard update() {
        SimCard simCard = getRandom();
        simCard = simCardRandomUpdater.update(simCard);
        simCardRepository.save(simCard);
        return simCard;
    }

    public Optional<Client> getClient(SimCard simCard) {
        if (simCard == null) {
            return Optional.empty();
        }
        Client client = simCard.getClient();
        if (client != null) {
            return Optional.of(client);
        }
        return Optional.empty();
    }

    public SimCard getRandomByClientNull() {
        List<SimCard> list = simCardRepository.findByClientNull();
        if (list.isEmpty()) {
            return add();
        }
        return list.get(randomizer.getRandomId(list.size()));
    }

}
