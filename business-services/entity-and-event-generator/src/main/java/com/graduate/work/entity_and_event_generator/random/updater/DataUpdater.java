package com.graduate.work.entity_and_event_generator.random.updater;

import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public abstract class DataUpdater<T> {

    @Setter(onMethod_ = {@Autowired})
    protected Faker faker;

    abstract List<RandomAction<T>> getRandomActions();

    public T update(T t) {
        List<RandomAction<T>> randomActions = getRandomActions();
        RandomAction<T> randomAction = randomActions.get(faker.number().numberBetween(0, randomActions.size() - 1));
        return randomAction.activate(t);
    }

    interface RandomAction<T> {
        T activate(T obj);
    }
}
