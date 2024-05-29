package com.graduate.work.entity_and_event_generator.random.updater.internal;

import com.graduate.work.entity_and_event_generator.random.updater.Updater;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public abstract class InternalUpdater<T> extends Updater<T> {
}
