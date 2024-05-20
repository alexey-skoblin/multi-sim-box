package com.graduate.work.entity_and_event_generator.random.updater;

import com.graduate.work.model.entity.Modem;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class ModemDataUpdater extends DataUpdater<Modem> {
    RandomAction<Modem> updateStatus = (modem) -> {
        if (modem.getStatus() == Modem.Status.ACTIVE) {
            modem.setStatus(Modem.Status.INACTIVE);

        } else if (modem.getStatus() == Modem.Status.INACTIVE) {
            modem.setStatus(Modem.Status.ACTIVE);
        }
        return modem;
    };

    @Override
    List<RandomAction<Modem>> getRandomActions() {
        return List.of(
                updateStatus
        );
    }

}