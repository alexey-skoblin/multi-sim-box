package com.graduate.work.entity_and_event_generator.random.updater.external;

import com.graduate.work.entity_and_event_generator.random.updater.RandomAction;
import com.graduate.work.model.entity.SimCard;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Lazy
@Service
@Getter
@Slf4j
public class SimCardExternalUpdater extends ExternalUpdater<SimCard> {

    @Override
    public List<RandomAction<SimCard>> getRandomActions() {
        return List.of();
    }

}
