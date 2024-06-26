package com.graduate.work.entity_and_event_generator.service;

import com.graduate.work.entity_and_event_generator.random.Randomizer;
import com.graduate.work.entity_and_event_generator.random.executor.ExecutableService;
import com.graduate.work.entity_and_event_generator.random.generator.ClientInitialStateGenerator;
import com.graduate.work.entity_and_event_generator.random.updater.external.ClientExternalUpdater;
import com.graduate.work.entity_and_event_generator.random.updater.internal.ClientInternalUpdater;
import com.graduate.work.entity_and_event_generator.repository.jpa.ClientRepository;
import com.graduate.work.entity_and_event_generator.repository.specification.ClientSpecification;
import com.graduate.work.model.dto.ClientPageDto;
import com.graduate.work.model.entity.Client;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Setter(onMethod_ = {@Autowired})
public class ClientService implements ExecutableService<Client> {

    private Randomizer randomizer;
    private ClientInitialStateGenerator clientRandomGenerator;
    private ClientInternalUpdater clientInternalUpdater;
    @Setter(onMethod_ = {@Autowired, @Lazy})
    private ClientExternalUpdater clientExternalUpdater;
    private ClientRepository clientRepository;

    @Override
    public Client add() {
        Client client = clientRandomGenerator.create();
        clientRepository.saveAndFlush(client);
        return client;
    }

    public List<Client> getAll(ClientPageDto clientPageDto) {
        Sort sort = Sort.by(Sort.Direction.fromString(clientPageDto.sortingOrder()), clientPageDto.sortingField());
        Pageable pageable = PageRequest.of(clientPageDto.page(), clientPageDto.size(), sort);
//        Specification<SimCard> specification = SimCardSpecification.bySearchCriteria();
        Specification<Client> specification = ClientSpecification.bySearchCriteria(clientPageDto.searchName(), clientPageDto.searchLastName(), clientPageDto.searchLogin(), clientPageDto.searchEmail(), clientPageDto.searchRole());
        return clientRepository.findAll(specification, pageable).toList();
    }

    @Override
    public Client getRandom() {
        List<Long> list = clientRepository.getAllIds();
        if (list.isEmpty()) {
            return add();
        }
        Long id = list.get(randomizer.getRandomId(list.size()));
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null) {
            return add();
        }
        return client;
    }

    @Override
    public Client update() {
        Client client = getRandom();
        client = clientInternalUpdater.update(client);
        client = clientExternalUpdater.update(client);
        clientRepository.save(client);
        return client;
    }

}
