package com.graduate.work.entity_and_event_generator.random;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Randomizer extends Faker {
    public Randomizer() {
        super(Locale.forLanguageTag("ru"));
    }

    public int getRandomId(int size) {
        return this.number().numberBetween(0, size - 1);
    }
}
