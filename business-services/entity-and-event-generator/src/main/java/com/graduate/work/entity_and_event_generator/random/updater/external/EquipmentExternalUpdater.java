package com.graduate.work.entity_and_event_generator.random.updater.external;

import com.graduate.work.entity_and_event_generator.random.updater.RandomAction;
import com.graduate.work.model.entity.Equipment;
import com.graduate.work.model.entity.Modem;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Getter
@Slf4j
public class EquipmentExternalUpdater extends ExternalUpdater<Equipment> {

    RandomAction<Equipment> addModem = (equipment) -> {
        if (equipment.getFacility() == null) {
            return equipment;
        }
        Modem modem = modemService.getRandomByEquipmentNull();
        modem.setEquipment(equipment);
        modem.setStatus(Modem.Status.ACTIVE);
        return equipment;
    };

    RandomAction<Equipment> removeModem = (equipment) -> {
        if (equipment.getModems() == null) {
            equipment.setModems(new HashMap<>());
            return equipment;
        }
        List<Modem> list = new ArrayList<>(equipment.getModems().values());
        if (list.isEmpty()) {
            return equipment;
        }
        int index = randomizer.getRandomId(list.size());
        Modem modem = list.get(index);
        modem.setEquipment(null);
        return equipment;
    };

    @Override
    public List<RandomAction<Equipment>> getRandomActions() {
        return List.of(
                addModem,
                removeModem
        );
    }
}
