package com.graduate.work.entity_and_event_generator.service.random.updater;

public interface RandomAction<T> {
    T activate(T obj);
}
