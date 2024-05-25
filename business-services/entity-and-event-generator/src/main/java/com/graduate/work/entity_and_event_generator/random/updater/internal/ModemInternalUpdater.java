package com.graduate.work.entity_and_event_generator.random.updater.internal;

import com.graduate.work.entity_and_event_generator.random.updater.RandomAction;
import com.graduate.work.model.entity.Modem;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class ModemInternalUpdater extends InternalUpdater<Modem> {

    @Override
    public List<RandomAction<Modem>> getRandomActions() {
        return List.of();
    }

}