package com.graduate.work.entity_and_event_generator.random.executor;

import org.springframework.transaction.annotation.Transactional;

public interface Executable<T> {

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
