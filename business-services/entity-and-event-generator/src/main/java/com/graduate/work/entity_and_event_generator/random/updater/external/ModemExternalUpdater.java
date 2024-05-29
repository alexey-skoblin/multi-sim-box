package com.graduate.work.entity_and_event_generator.random.updater.external;

import com.graduate.work.entity_and_event_generator.random.updater.RandomAction;
import com.graduate.work.model.entity.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Getter
@Slf4j
public class ModemExternalUpdater extends ExternalUpdater<Modem> {

    RandomAction<Modem> addSimCard = (modem) -> {
        if (modem.getSimCards() == null) {
            modem.setSimCards(new HashMap<>());
            return modem;
        }
        if (modem.getSimCards().size() >= com.graduate.work.entity_and_event_generator.random.data.Modem.COUNT_SIM_CARDS) {
            return modem;
        }
        Object object = modem.getEquipment();
        if (object == null) {
            return modem;
        }
        object = ((Equipment) object).getFacility();
        if (object == null) {
            return modem;
        }
        Client client = ((Facility) object).getClient();
        if (client == null) {
            return modem;
        }
        SimCard simCard;
        List<SimCard> list = simCardRepository.findAllNotModemByClient_Id(client.getId());
        if (list.isEmpty()) {
            return generateSimCardAndAddInModem(modem, client);
        }
        int index = randomizer.getRandomId(list.size());
        simCard = list.get(index);
        simCard.setModem(modem);
        simCard.setStatus(SimCard.Status.ACTIVE);
//        simCard.setStatus(SimCard.Status.ACTIVE);
        return modem;
    };
    RandomAction<Modem> removeSimCard = (modem) -> {
        if (modem.getSimCards() == null) {
            modem.setSimCards(new HashMap<>());
            return modem;
        }
        if (modem.getSimCards().isEmpty()) {
            return modem;
        }
        List<SimCard> simCards = new ArrayList<>(modem.getSimCards().values());
        int index = randomizer.getRandomId(simCards.size());
        SimCard simCard = simCards.get(index);
        simCard.setModem(null);
        return modem;
    };

    private Modem generateSimCardAndAddInModem(Modem modem, Client client) {
        SimCard simCard;
        simCard = simCardService.add();
        simCard.setClient(client);
        simCard.setModem(modem);
//        simCard.setStatus(SimCard.Status.ACTIVE);
        return modem;
    }

    @Override
    public List<RandomAction<Modem>> getRandomActions() {
        return List.of(
                addSimCard,
                removeSimCard
        );
    }
}
