package com.graduate.work.entity_and_event_generator.random.relationship;

import com.graduate.work.model.entity.Client;
import com.graduate.work.model.entity.Modem;
import com.graduate.work.model.entity.SimCard;

import java.util.List;

import static com.graduate.work.entity_and_event_generator.random.data.Modem.COUNT_SIM_CARDS;

public class SimCardRelationHandler extends RelationHandler<SimCard> {

    RandomAction<SimCard> addClient = (simCard) -> {
        List<Long> ids = clientRepository.getAllIds();
        if (ids.isEmpty()) {
            Client client = clientService.add();
            simCard.setClient(client);
        } else {
            Long id = ids.get(faker.number().numberBetween(0, ids.size() - 1));
            simCard.setClient(clientRepository.getReferenceById(id));
        }
        return simCard;
    };

    RandomAction<SimCard> removeClient = (simCard) -> {
        simCard.setClient(null);
        return simCard;
    };

    RandomAction<SimCard> addModem = (simCard) -> {
        if (simCard.getStatus() == SimCard.Status.INACTIVE) {
            simCard.setStatus(SimCard.Status.ACTIVE);
        }
        if (simCard.getClient() == null) {
            addClient.activate(simCard);
        }
        List<Modem> modems = modemRepository.findModemsWithSimOwnedByUserAndNumberOfSimsInRange(simCard.getClient().getId(), COUNT_SIM_CARDS);
        Modem modem;
        if (modems.isEmpty()) {
            modem = modemService.add();
        } else {
            modem = modems.get(faker.number().numberBetween(0, modems.size() - 1));
        }
        simCard.setModem(modem);
        return simCard;
    };

    RandomAction<SimCard> removeModem = (simCard) -> {
        simCard.setModem(null);
        return simCard;
    };

    @Override
    List<RandomAction<SimCard>> getRandomActions() {
        return List.of(
                addClient,
                removeClient,
                addModem,
                removeModem
        );
    }
}
