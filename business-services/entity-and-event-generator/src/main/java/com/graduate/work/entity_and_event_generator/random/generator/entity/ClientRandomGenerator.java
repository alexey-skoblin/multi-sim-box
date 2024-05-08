package com.graduate.work.entity_and_event_generator.random.generator.entity;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.graduate.work.entity_and_event_generator.random.Randomizer;
import com.graduate.work.entity_and_event_generator.random.generator.RandomGenerator;
import com.graduate.work.model.entity.Client;
import com.graduate.work.model.entity.Object;
import com.graduate.work.model.entity.SimCard;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientRandomGenerator extends RandomGenerator {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Client create() {
        Name fakeName = faker.name();
        String firstName = fakeName.firstName();
        String surname = fakeName.lastName();
        String login = firstName.toLowerCase() + "." + surname.toLowerCase();
        String password = faker.internet().password(10, 20, true, true);
        String hash = passwordEncoder.encode(login + password);
        String email = login + "@ya.ru";
        String ip = faker.internet().ipV4Address();
        List<Object> objects = new ArrayList<>();
        List<SimCard> simCards = new ArrayList<>();
        return Client.builder()
                .name(firstName)
                .surname(surname)
                .login(login)
                .hash(hash)
                .email(email)
                .ip(ip)
                .objects(objects)
                .simCards(simCards)
                .build();
    }

}
