package com.graduate.work.automated_workstation.controller;

import com.graduate.work.automated_workstation.output.client.generatorClient;
import com.graduate.work.model.dto.SimCardDto;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@Slf4j
@Setter(onMethod_ = {@Autowired})
public class SimCardController {

    generatorClient simCardClient;

    @RequestMapping("/sim-cards")
    Iterable<SimCardDto> getAll() {
        return simCardClient.getAllSimCards();
    }
}
