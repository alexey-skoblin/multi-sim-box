package com.graduate.work.entity_and_event_generator.input;


import com.graduate.work.entity_and_event_generator.service.ClientService;
import com.graduate.work.model.dto.ClientDto;
import com.graduate.work.model.dto.ClientPageDto;
import com.graduate.work.model.entity.Client;
import com.graduate.work.model.mapper.ClientMapper;
import com.graduate.work.model.types.Role;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
@Setter(onMethod_ = {@Autowired})
@Slf4j
public class ClientController {

    private final ClientMapper clientMapper = ClientMapper.INSTANCE;
    ClientService clientService;

    @GetMapping
    public Iterable<ClientDto> getAll(
            int page,
            int size,
            String sortingField,
            String sortingOrder,
            String searchName,
            String searchLastName,
            String searchLogin,
            String searchEmail,
            String searchRole
//            String search,
//            String searchIccid,
//            String searchDefNumber,
//            String searchAddress,
//            String searchSerialNumber
    ) {
        ClientPageDto clientPageDto = new ClientPageDto(page, size, sortingField, sortingOrder, searchName, searchLastName, searchLogin, searchEmail, searchRole);
        List<Client> clientList = clientService.getAll(clientPageDto);
        return clientList.stream().map(clientMapper::toDto).toList();
    }

    @PostMapping(path = "/update-role")
    public void updateStatus(@RequestParam Role role, @RequestBody List<String> ListId) {
//        simCardService.updateStatusByListSimCards(ListIccid, status);
    }
}
