package com.graduate.work.entity_and_event_generator.random.generator;

import com.github.javafaker.Faker;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public abstract class InitialStateGenerator {

    @Setter(onMethod_ = {@Autowired})
    protected Faker faker;

    public abstract Object create();

}
