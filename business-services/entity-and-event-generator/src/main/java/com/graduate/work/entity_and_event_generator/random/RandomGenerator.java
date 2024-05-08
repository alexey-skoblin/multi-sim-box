package com.graduate.work.entity_and_event_generator.random;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class RandomGenerator {

    protected Faker faker;

    @Autowired
    public void setFaker(Randomizer randomizer) {
        faker = randomizer.faker;
    }

    public abstract Object create();


}
