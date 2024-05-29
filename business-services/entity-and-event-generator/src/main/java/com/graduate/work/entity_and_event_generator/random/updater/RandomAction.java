package com.graduate.work.entity_and_event_generator.random.updater;

public interface RandomAction<T> {
    T activate(T obj);
}
