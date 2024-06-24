package com.graduate.work.entity_and_event_generator.random;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Locale;

@Component
public class Randomizer extends Faker {
    public Randomizer() {
        super(Locale.forLanguageTag("ru"));
    }

    public int getRandomId(int size) {
        return this.number().numberBetween(0, size);
    }

    public boolean getRandomBoolean() {
        return this.number().numberBetween(0, 1) == 1;
    }

    public long getRandomDate() {
        return this.date().between(new Date(0), new Date()).getTime();
    }
}
