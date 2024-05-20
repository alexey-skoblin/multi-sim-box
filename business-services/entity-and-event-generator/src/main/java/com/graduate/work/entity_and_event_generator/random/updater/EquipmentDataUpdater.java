package com.graduate.work.entity_and_event_generator.random.updater;

import com.graduate.work.model.entity.Equipment;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class EquipmentDataUpdater extends DataUpdater<Equipment> {
    RandomAction<Equipment> updateHostName = (equipment) -> {
        equipment.setHostname(faker.internet().domainName());
        return equipment;
    };

    @Override
    List<RandomAction<Equipment>> getRandomActions() {
        return List.of(
                updateHostName
        );
    }
}
