package com.graduate.work.entity_and_event_generator.random.updater.external;

import com.graduate.work.entity_and_event_generator.random.updater.RandomAction;
import com.graduate.work.model.entity.Client;
import com.graduate.work.model.entity.Facility;
import com.graduate.work.model.entity.SimCard;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Lazy
@Service
@Getter
@Slf4j
public class ClientExternalUpdater extends ExternalUpdater<Client> {

    RandomAction<Client> addSimCard = (client) -> {
        SimCard simCard = simCardService.getRandomByClientNull();
        simCard.setClient(client);
        return client;
    };

    RandomAction<Client> removeSimCard = (client) -> {
        if (client.getSimCards() == null) {
            client.setSimCards(new HashMap<>());
            return client;
        }
        List<SimCard> list = new ArrayList<>(client.getSimCards().values());
        if (list.isEmpty()) {
            return client;
        }
        int index = randomizer.getRandomId(list.size());
        SimCard simCard = list.get(index);
        simCard.setClient(null);
        return client;
    };

    RandomAction<Client> addFacility = (client) -> {
        Facility facility = facilityService.getRandomByClientNull();
        facility.setClient(client);
        facility.setStatus(Facility.Status.ACTIVE);
        return client;
    };

    RandomAction<Client> removeFacility = (client) -> {
        if (client.getFacilities() == null) {
            client.setFacilities(new HashMap<>());
            return client;
        }
        List<Facility> list = new ArrayList<>(client.getFacilities().values());
        if (list.isEmpty()) {
            return client;
        }
        int index = randomizer.getRandomId(list.size());
        Facility facility = list.get(index);
        facility.setClient(null);
        return client;
    };


    @Override
    public List<RandomAction<Client>> getRandomActions() {
        return List.of(
                addSimCard,
                removeSimCard,
                addFacility,
                removeFacility
        );
    }
}
