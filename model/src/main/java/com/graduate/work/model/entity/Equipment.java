package com.graduate.work.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Builder
@ToString
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Setter
    String hostname;

    @Setter
    String model;

    @Setter
    String serialNumber;

    @OneToOne
    @ToString.Exclude
    private Facility facility;

    @Setter
    @OneToMany(mappedBy = "equipment", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "id")
    @ToString.Exclude
    private Map<Long, Modem> modems;

    public void setFacility(Facility facility) {
        if (this.facility == facility) {
            return;
        }
        if (this.facility != null) {
            Facility oldFacility = this.facility;
            this.facility = null;
            oldFacility.setEquipment(null);
        }
        this.facility = facility;
        if (facility != null) {
            facility.setEquipment(this);
        } else {
            this.clearModems();
        }
    }

    public void clearModems() {
        if (modems == null) {
            return;
        }
        List<Modem> modemsList = modems.values().stream().toList();
        for (Modem modem : modemsList) {
            modem.setEquipment(null);
        }
        modems.clear();
    }

}