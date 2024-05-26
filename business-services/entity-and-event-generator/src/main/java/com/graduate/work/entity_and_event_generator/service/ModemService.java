package com.graduate.work.entity_and_event_generator.service;

import com.graduate.work.entity_and_event_generator.service.random.Randomizer;
import com.graduate.work.entity_and_event_generator.service.random.generator.ModemInitialStateGenerator;
import com.graduate.work.entity_and_event_generator.service.random.updater.external.ModemExternalUpdater;
import com.graduate.work.entity_and_event_generator.service.random.updater.internal.ModemInternalUpdater;
import com.graduate.work.entity_and_event_generator.repository.ModemRepository;
import com.graduate.work.entity_and_event_generator.service.random.executor.Executable;
import com.graduate.work.model.entity.Client;
import com.graduate.work.model.entity.Modem;
import com.graduate.work.model.entity.SimCard;
import lombok.AllArgsConstructor;
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
public class ModemService implements Executable<Modem> {

    private Randomizer randomizer;
    private ModemInitialStateGenerator modemRandomGenerator;
    private ModemInternalUpdater modemInternalUpdater;
    @Setter(onMethod_ = {@Autowired, @Lazy})
    private ModemExternalUpdater modemExternalUpdater;
    private ModemRepository modemRepository;

    @Override
    public Modem add() {
        Modem modem = modemRandomGenerator.create();
        modemRepository.saveAndFlush(modem);
        return modem;
    }

    @Override
    public List<Modem> getAll() {
        return modemRepository.findAll();
    }

    @Override
    public Modem getRandom() {
        List<Long> list = modemRepository.getAllIds();
        if (list.isEmpty()) {
            return add();
        }
        Long id = list.get(randomizer.getRandomId(list.size()));
        Modem modem = modemRepository.findById(id).orElse(null);
        if (modem == null) {
            return add();
        }
        return modem;
    }

    @Override
    public Modem update() {
        Modem modem = getRandom();
        modem = modemInternalUpdater.update(modem);
        modem = modemExternalUpdater.update(modem);
        modemRepository.save(modem);
        return modem;
    }

    public Optional<Client> getClient(Modem modem) {
        if (modem == null) {
            return Optional.empty();
        }
        for (SimCard simCard : modem.getSimCards().values()) {
            Client client = simCard.getClient();
            if (client != null) {
                return Optional.of(client);
            }
        }
        return Optional.empty();
    }

    public Modem getRandomByEquipmentNull() {
        List<Modem> list = modemRepository.findByEquipmentNull();
        if (list.isEmpty()) {
            return add();
        }
        return list.get(randomizer.getRandomId(list.size()));
    }
}
