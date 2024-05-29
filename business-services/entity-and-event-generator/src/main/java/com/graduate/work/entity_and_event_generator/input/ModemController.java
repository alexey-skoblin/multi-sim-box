package com.graduate.work.entity_and_event_generator.input;

import com.graduate.work.entity_and_event_generator.service.ModemService;
import com.graduate.work.model.entity.Modem;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/modem")
@Setter(onMethod_ = {@Autowired})
public class ModemController {

    ModemService modemService;

    @RequestMapping("/all")
    public Iterable<Modem> getAll() {
        return modemService.getAll();
    }
}
