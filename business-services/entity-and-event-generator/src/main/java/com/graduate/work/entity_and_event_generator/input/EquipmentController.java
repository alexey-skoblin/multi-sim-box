package com.graduate.work.entity_and_event_generator.input;

import com.graduate.work.entity_and_event_generator.service.EquipmentService;
import com.graduate.work.model.entity.Equipment;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equipment")
@Setter(onMethod_ = {@Autowired})
public class EquipmentController {

    EquipmentService equipmentService;

    @RequestMapping("/all")
    public Iterable<Equipment> getAll() {
        return equipmentService.getAll();
    }
}
