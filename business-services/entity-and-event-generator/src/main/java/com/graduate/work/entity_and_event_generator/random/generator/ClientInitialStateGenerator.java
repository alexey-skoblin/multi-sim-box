package com.graduate.work.entity_and_event_generator.random.generator;

import com.github.javafaker.Name;
import com.graduate.work.model.entity.Client;
import com.graduate.work.model.entity.Object;
import com.graduate.work.model.entity.SimCard;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientInitialStateGenerator extends InitialStateGenerator {

    @Setter(onMethod_ = {@Autowired})
    private PasswordEncoder passwordEncoder;

    @Override
    public Client create() {
        Name name = faker.name();
        String firstName = name.firstName();
        String lastName = name.lastName();
        String login = firstName.toLowerCase() + "." + lastName.toLowerCase();
        String password = faker.internet().password(10, 20, true, true);
        String hash = passwordEncoder.encode(login + password);
        String email = login + "@ya.ru";
        String ip = faker.internet().ipV4Address();
        List<Object> objects = new ArrayList<>();
        List<SimCard> simCards = new ArrayList<>();
        return Client.builder()
                .name(firstName)
                .lastName(lastName)
                .login(login)
                .hash(hash)
                .email(email)
                .ip(ip)
                .objects(objects)
                .simCards(simCards)
                .build();
    }

}
