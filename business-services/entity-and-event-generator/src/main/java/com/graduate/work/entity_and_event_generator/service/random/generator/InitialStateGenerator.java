package com.graduate.work.entity_and_event_generator.service.random.generator;

import com.graduate.work.entity_and_event_generator.service.random.Randomizer;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class InitialStateGenerator<T> {

    @Setter(onMethod_ = {@Autowired})
    protected Randomizer randomizer;

    public abstract T create();

}
