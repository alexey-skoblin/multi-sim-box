package com.graduate.work.entity_and_event_generator.input;

import com.graduate.work.entity_and_event_generator.service.SimCardService;
import com.graduate.work.model.dto.SimCardDto;
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
@Setter(onMethod_ = {@Autowired})
@Slf4j
public class SimCardController {

    private final SimCardMapper simCardMapper = SimCardMapper.INSTANCE;
    SimCardService simCardService;

    @GetMapping(path = "/sim-cards", params = {"page", "size"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<SimCardDto> getAll(int page, int size) {
        log.atInfo().log("Page: " + page + " Size: " + size);
        List<SimCard> simCards = simCardService.getAll(page, size);
        return simCards.stream().map(simCardMapper::simCardToSimCardDto).toList();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/activate", produces = MediaType.APPLICATION_JSON_VALUE)
    public void activate(@RequestBody List<String> iccids) {
        log.atInfo().log("Activate: " + iccids);
    }
/*    @RequestMapping(path = "/sim-cards", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<SimCardDto> getAll() {
        log.atInfo().log("Get all sim-cards");
        List<SimCard> simCards = simCardService.getAll();
        return simCards.stream().map(simCardMapper::simCardToSimCardDto).toList();
    }*/

}
