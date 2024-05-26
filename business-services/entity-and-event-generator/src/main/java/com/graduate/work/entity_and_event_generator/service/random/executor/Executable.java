package com.graduate.work.entity_and_event_generator.service.random.executor;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface Executable<T> {

    List<T> getAll();
    T getRandom();

    T add();

    @Transactional
    T update();


    default void run(Runnable runnable, Integer countCreatingEntities) {
        for (int i = 0; i < countCreatingEntities; i++) {
            runnable.run();
        }
    }
}
