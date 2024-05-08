package com.graduate.work.entity_and_event_generator.random;

import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Randomizer {

    public final Faker faker;
    public Randomizer() {
        Locale locale = new Locale.Builder().setLanguage("ru").build();
        faker = new Faker(locale);
    }

}
