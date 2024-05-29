package com.graduate.work.entity_and_event_generator.input;


import com.graduate.work.entity_and_event_generator.service.ClientService;
import com.graduate.work.model.entity.Client;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@Setter(onMethod_ = {@Autowired})
public class ClientController {

    ClientService clientService;

    @RequestMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Client> getAll() {
        return clientService.getAll();
    }
}
