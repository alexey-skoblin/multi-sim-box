package com.graduate.work.entity_and_event_generator.input;

import com.graduate.work.entity_and_event_generator.service.FacilityService;
import com.graduate.work.model.entity.Facility;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/facility")
@Setter(onMethod_ = {@Autowired})
public class FacilityController {

    FacilityService facilityService;

    @RequestMapping("/all")
    public Iterable<Facility> getAll() {
        return facilityService.getAll();
    }
}
