package com.graduate.work.model.entity;


import com.graduate.work.model.types.ModemStatus;
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
public class Modem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ModemStatus modemStatus;

    private String imei;

    @ManyToOne
    @ToString.Exclude
    private Equipment equipment;

    @Setter
    @OneToMany(mappedBy = "modem", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "id")
    @ToString.Exclude
    private Map<Long, SimCard> simCards;

    public void setModemStatus(ModemStatus modemStatus) {
        if (this.modemStatus == modemStatus) {
            return;
        }
        if (this.modemStatus == ModemStatus.ACTIVE) {
            this.modemStatus = ModemStatus.INACTIVE;
            if (this.equipment != null) {
                this.setEquipment(null);
            }
            this.clearSimCards();
        } else if (this.modemStatus == ModemStatus.INACTIVE) {
            this.modemStatus = ModemStatus.ACTIVE;
        }
    }

    public void setEquipment(Equipment equipment) {
        if (this.equipment == equipment) {
            return;
        }
        if (this.equipment != null) {
            this.equipment.getModems().remove(this.id);
        }
        this.equipment = equipment;
        if (equipment != null) {
            if (equipment.getModems() == null) {
                equipment.setModems(new HashMap<>());
            }
            equipment.getModems().put(this.id, this);
        } else {
            this.setModemStatus(ModemStatus.INACTIVE);
        }
    }

    public void clearSimCards() {
        if (simCards == null) {
            return;
        }
        List<SimCard> simCardsList = simCards.values().stream().toList();
        for (SimCard simCard : simCardsList) {
            simCard.setModem(null);
        }
        simCards.clear();
    }

}