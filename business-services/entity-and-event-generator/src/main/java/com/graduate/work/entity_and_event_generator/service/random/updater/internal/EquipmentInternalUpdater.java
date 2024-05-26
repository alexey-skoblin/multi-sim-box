package com.graduate.work.entity_and_event_generator.service.random.updater.internal;

import com.graduate.work.entity_and_event_generator.service.random.updater.RandomAction;
import com.graduate.work.model.entity.Equipment;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class EquipmentInternalUpdater extends InternalUpdater<Equipment> {
    RandomAction<Equipment> updateHostName = (equipment) -> {
        equipment.setHostname(randomizer.internet().domainName());
        return equipment;
    };

    @Override
    public List<RandomAction<Equipment>> getRandomActions() {
        return List.of(
                updateHostName
        );
    }
}
