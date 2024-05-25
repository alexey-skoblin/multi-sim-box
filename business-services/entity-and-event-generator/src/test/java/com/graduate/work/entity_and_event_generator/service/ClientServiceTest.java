package com.graduate.work.entity_and_event_generator.service;

import com.graduate.work.entity_and_event_generator.random.Randomizer;
import com.graduate.work.entity_and_event_generator.random.generator.ClientInitialStateGenerator;
import com.graduate.work.entity_and_event_generator.random.updater.internal.ClientInternalUpdater;
import com.graduate.work.entity_and_event_generator.repository.ClientRepository;
import com.graduate.work.model.entity.Client;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
@SpringBootTest(properties =
        """
                spring.datasource.url=jdbc:h2:mem:db;DB_CLOSE_DELAY=-1;NON_KEYWORDS=KEY,VALUE
                spring.datasource.driverClassName=org.h2.Driver
                spring.datasource.driver-class-name=org.h2.Driver"""
)
@Setter(onMethod_ = {@Autowired})
class ClientServiceTest {

    @Mock
    private Randomizer randomizer;

    private ClientInitialStateGenerator clientRandomGenerator;

    @Mock
    private ClientInternalUpdater clientInternalUpdater;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addClientTest() {
        Client client = clientRandomGenerator.create(); // Создаем клиента для тестирования
        when(clientRepository.saveAndFlush(any(Client.class))).thenReturn(client);

        Client savedClient = clientService.add(); // Вызываем метод add

        assertNotNull(client);
        assertNotNull(savedClient);
        verify(clientRepository, times(1)).saveAndFlush(any(Client.class)); // Проверяем, что метод saveAndFlush был вызван 1 раз
    }

    @Test
    void getAllClientsTest() {
        Client client = clientRandomGenerator.create(); // Создаем клиента для тестирования
        Client client2 = clientRandomGenerator.create();
        List<Client> clients = Arrays.asList(client, client2); // Создаем список клиентов для тестирования
        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> retrievedClients = clientService.getAll(); // Вызываем метод getAll

        assertEquals(clients, retrievedClients); // Проверяем, что возвращенный список клиентов совпадает с ожидаемым
        verify(clientRepository, times(1)).findAll(); // Проверяем, что метод findAll был вызван 1 раз
    }

    @Test
    void getRandomClientTest() {
        Client client = clientRandomGenerator.create(); // Создаем клиента для тестирования
        List<Long> ids = Arrays.asList(1L, 2L); // Создаем список идентификаторов
        when(clientRepository.getAllIds()).thenReturn(ids);
        when(randomizer.getRandomId(ids.size())).thenReturn(0); // Мы устанавливаем индекс, чтобы вернуть первый идентификатор из списка
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Client retrievedClient = clientService.getRandom(); // Вызываем метод getRandom

        assertEquals(client, retrievedClient); // Проверяем, что возвращенный клиент совпадает с ожидаемым
        verify(clientRepository, times(1)).getAllIds(); // Проверяем, что метод getAllIds был вызван 1 раз
        verify(clientRepository, times(1)).findById(1L); // Проверяем, что метод findById был вызван 1 раз
    }

    @Test
    void updateClientTest() {
        Client client = clientRandomGenerator.create(); // Создаем клиента для тестирования
        when(clientService.getRandom()).thenReturn(client); // Мы устанавливаем случайного клиента для возврата при вызове метода getRandom
        Client updatedClient = clientRandomGenerator.create(); // Создаем обновленного клиента
//        when(clientInternalUpdater.update(client)).thenReturn(updatedClient); // Мы устанавливаем обновленного клиента для возврата при вызове метода update
        when(clientRepository.save(updatedClient)).thenReturn(updatedClient); // Мы устанавливаем обновленного клиента для возврата при вызове метода save

        Client result = clientService.update(); // Вызываем метод update

        assertEquals(updatedClient, result);
        verify(clientService, times(1)).getRandom(); // Проверяем, что метод getRandom был вызван 1 раз
        verify(clientInternalUpdater, times(1)).update(client); // Проверяем, что метод update был вызван 1 раз с данным клиентом
        verify(clientRepository, times(1)).save(updatedClient); // Проверяем, что метод save был вызван 1 раз с обновленным клиентом
    }
}