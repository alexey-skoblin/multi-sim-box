package com.graduate.work.entity_and_event_generator.service.random.updater.internal;

import com.graduate.work.entity_and_event_generator.service.random.updater.RandomAction;
import com.graduate.work.model.entity.Client;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class ClientInternalUpdater extends InternalUpdater<Client> {
    RandomAction<Client> updateName = (client) -> {
        client.setName(randomizer.name().firstName());
        return client;
    };
    RandomAction<Client> updateLastName = (client) -> {
        client.setLastName(randomizer.name().lastName());
        return client;
    };
    RandomAction<Client> updateEmail = (client) -> {
        client.setEmail(randomizer.internet().emailAddress());
        return client;
    };
    RandomAction<Client> updateIp = (client) -> {
        client.setIp(randomizer.internet().ipV4Address());
        return client;
    };

    @Override
    public List<RandomAction<Client>> getRandomActions() {
        return List.of(
                updateName,
                updateLastName,
                updateEmail,
                updateIp
        );
    }

}
