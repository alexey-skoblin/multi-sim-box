package com.graduate.work.entity_and_event_generator.service.random.updater.internal;

import com.graduate.work.entity_and_event_generator.service.random.updater.RandomAction;
import com.graduate.work.model.entity.Facility;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class FacilityInternalUpdater extends InternalUpdater<Facility> {

    @Override
    public List<RandomAction<Facility>> getRandomActions() {
        return List.of(
        );
    }
}
