package com.graduate.work.automated_workstation.output.client;

import com.graduate.work.model.dto.SimCardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value ="sim-card", url = "http://localhost:8000/entity-and-event-generator")
public interface generatorClient {

    @RequestMapping("/sim-cards")
   Iterable<SimCardDto> getAllSimCards();

}
