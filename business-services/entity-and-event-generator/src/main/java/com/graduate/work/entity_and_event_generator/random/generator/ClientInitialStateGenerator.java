package com.graduate.work.entity_and_event_generator.random.generator;

import com.github.javafaker.Name;
import com.graduate.work.model.entity.Client;
import com.graduate.work.model.entity.Facility;
import com.graduate.work.model.entity.SimCard;
import com.graduate.work.model.types.Role;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class ClientInitialStateGenerator extends InitialStateGenerator<Client> {
    private PasswordEncoder passwordEncoder;

    @Override
    public Client create() {
        Name name = randomizer.name();
        String firstName = name.firstName();
        String lastName = name.lastName();
        String login = firstName.toLowerCase() + "." + lastName.toLowerCase();
        String password = randomizer.internet().password(10, 20, true, true);
        Role role = Role.values()[randomizer.getRandomId(Role.values().length)];
        String hash = passwordEncoder.encode(login + password);
        String email = login + "@ya.ru";
        String ip = randomizer.internet().ipV4Address();
        Map<Long, Facility> objectMap = new HashMap<>();
        Map<Long, SimCard> simCardMap = new HashMap<>();
        return Client.builder()
                .name(firstName)
                .lastName(lastName)
                .login(login)
                .hash(hash)
                .email(email)
                .role(role)
                .ip(ip)
                .facilities(objectMap)
                .simCards(simCardMap)
                .build();
    }

}
