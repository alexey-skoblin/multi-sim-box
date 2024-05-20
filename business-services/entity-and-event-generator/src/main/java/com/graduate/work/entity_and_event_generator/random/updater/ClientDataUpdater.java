package com.graduate.work.entity_and_event_generator.random.updater;

import com.graduate.work.model.entity.Client;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class ClientDataUpdater extends DataUpdater<Client> {
    RandomAction<Client> updateName = (client) -> {
        client.setName(faker.name().firstName());
        return client;
    };
    RandomAction<Client> updateLastName = (client) -> {
        client.setLastName(faker.name().lastName());
        return client;
    };
    RandomAction<Client> updateEmail = (client) -> {
        client.setEmail(faker.internet().emailAddress());
        return client;
    };
    RandomAction<Client> updateIp = (client) -> {
        client.setIp(faker.internet().ipV4Address());
        return client;
    };

    @Override
    List<RandomAction<Client>> getRandomActions() {
        return List.of(
                updateName,
                updateLastName,
                updateEmail,
                updateIp
        );
    }

}
