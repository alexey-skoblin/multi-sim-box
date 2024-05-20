package com.graduate.work.entity_and_event_generator.random.relationship;

import com.github.javafaker.Faker;
import com.graduate.work.entity_and_event_generator.repository.*;
import com.graduate.work.model.entity.Client;
import com.graduate.work.model.entity.Modem;
import com.graduate.work.model.entity.SimCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.graduate.work.entity_and_event_generator.random.data.Modem.COUNT_SIM_CARDS;

@Service
@Getter
@Slf4j
public class ModemRelationHandler extends RelationHandler<Modem> {

    RandomAction<Modem> fullModem = (modem) -> {
        List<Long> ids = clientRepository.getAllIds();
        Long id = ids.get(faker.number().numberBetween(0, ids.size() - 1));
        Client client = clientRepository.getReferenceById(id);
        List<SimCard> simCards = simCardRepository.findAllByModemIsNullAndClient_Id(id);
        if (simCards.size() < COUNT_SIM_CARDS) {
            while (COUNT_SIM_CARDS - simCards.size() > 0) {
                SimCard simCard = simCardService.add();
                log.info(simCard.toString());
                simCard.setClient(client);
                simCards.add(simCard);
            }
        }
        for (int i = 0; i < COUNT_SIM_CARDS; i++) {
            modem.addSimCard(simCards.get(i));
        }
        return modem;
    };

    RandomAction<Modem> clearModem = (modem) -> {
        List<SimCard> simCards = simCardRepository.findAllByModem_Id(modem.getId());
        for (SimCard simCard : simCards) {
            simCard.setModem(null);
        }
        return modem;
    };


    @Override
    List<RandomAction<Modem>> getRandomActions() {
        return List.of(
            fullModem,
            clearModem
        );
    }
}
