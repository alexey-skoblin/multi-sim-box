package com.graduate.work.entity_and_event_generator.random.event;

import java.util.List;
import java.util.Optional;

public interface Executable<T> {

    List<T> getAll();
    T getRandom();

    T add();

    T update();

    default void run(Runnable runnable, Integer countCreatingEntities) {
        for (int i = 0; i < countCreatingEntities; i++) {
            runnable.run();
        }
    }
}
