package com.graduate.work.entity_and_event_generator.service.random.updater;

import com.graduate.work.entity_and_event_generator.service.random.Randomizer;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class Updater<T> {

    @Setter(onMethod_ = @Autowired)
    public Randomizer randomizer;

    public abstract List<RandomAction<T>> getRandomActions();

    public T update(T t) {
        List<RandomAction<T>> randomActions = getRandomActions();
        if ( randomActions == null ||randomActions.isEmpty()) {
            return t;
        }
        RandomAction<T> randomAction = randomActions.get(randomizer.getRandomId(randomActions.size()));
//        T object = randomAction.activate(t);
//        if (object == null) {
//            throw new RuntimeException("Object is null");
//        }
//        return object;
        return randomAction.activate(t);
    }

}
