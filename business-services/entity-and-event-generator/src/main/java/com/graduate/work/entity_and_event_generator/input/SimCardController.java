package com.graduate.work.entity_and_event_generator.input;

import com.graduate.work.entity_and_event_generator.service.SimCardService;
import com.graduate.work.model.dto.SimCardDto;
import com.graduate.work.model.dto.SimCardPageDto;
import com.graduate.work.model.entity.SimCard;
import com.graduate.work.model.mapper.SimCardMapper;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/sim-cards", produces = MediaType.APPLICATION_JSON_VALUE)
@Setter(onMethod_ = {@Autowired})
@Slf4j
public class SimCardController {

    private final SimCardMapper simCardMapper = SimCardMapper.INSTANCE;
    SimCardService simCardService;

    @GetMapping
    public Iterable<SimCardDto> getAll(
            int page,
            int size,
            String sortingField,
            String sortingOrder,
            String searchMobileOperator,
            String searchIccid,
            String searchDefNumber,
            String searchAddress,
            String searchSerialNumber
    ) {
        SimCardPageDto simCardPageDto = new SimCardPageDto(page, size, sortingField, sortingOrder, searchMobileOperator, searchIccid, searchDefNumber, searchAddress, searchSerialNumber);
        List<SimCard> simCards = simCardService.getAll(simCardPageDto);
        return simCards.stream().map(simCardMapper::simCardToSimCardDto).toList();
    }


    @PostMapping(path = "/update-status")
    public void updateStatus(@RequestParam SimCard.Status status, @RequestBody List<String> ListIccid) {
        simCardService.updateStatusByListSimCards(ListIccid, status);
    }


    /*    @RequestMapping(path = "/sim-cards", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<SimCardDto> getAll() {
        log.atInfo().log("Get all sim-cards");
        List<SimCard> simCards = simCardService.getAll();
        return simCards.stream().map(simCardMapper::simCardToSimCardDto).toList();
    }*/

}
