package com.graduate.work.entity_and_event_generator.random.updater;

import com.graduate.work.model.entity.Object;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class ObjectDataUpdater extends DataUpdater<Object> {

    RandomAction<Object> updateStatus = (obj) -> {
        if (obj.getStatus() == Object.Status.ACTIVE) {
            obj.setStatus(Object.Status.INACTIVE);
        } else if (obj.getStatus() == Object.Status.INACTIVE) {
            obj.setStatus(Object.Status.ACTIVE);
        }
        return obj;
    };

    @Override
    List<RandomAction<Object>> getRandomActions() {
        return List.of(
                updateStatus
        );
    }
}
