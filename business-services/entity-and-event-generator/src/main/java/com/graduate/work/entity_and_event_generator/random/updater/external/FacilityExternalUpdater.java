package com.graduate.work.entity_and_event_generator.random.updater.external;

import com.graduate.work.entity_and_event_generator.random.updater.RandomAction;
import com.graduate.work.model.entity.Equipment;
import com.graduate.work.model.entity.Facility;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
@Slf4j
public class FacilityExternalUpdater extends ExternalUpdater<Facility> {

    RandomAction<Facility> addEquipment = (object) -> {
        if (object.getClient() == null) {
            return object;
        }
        Equipment equipment = equipmentService.getRandomByFacilityNull();
        equipment.setFacility(object);
        return object;
    };

    RandomAction<Facility> removeEquipment = (object) -> {
        Equipment equipment = object.getEquipment();
        if (equipment == null) {
            return object;
        }
        equipment.setFacility(null);
        return object;
    };

    @Override
    public List<RandomAction<Facility>> getRandomActions() {
        return List.of(
                addEquipment,
                removeEquipment
        );
    }
}
