package com.graduate.work.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Builder
@ToString
public class Modem {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String imei;

    @ManyToOne
    @ToString.Exclude
    private Equipment equipment;

    @Setter
    @OneToMany(mappedBy = "modem", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "id")
    @ToString.Exclude
    private Map<Long, SimCard> simCards;

    public void setStatus(Status status){
        if (this.status == status) {
            return;
        }
        if (this.status == Status.ACTIVE) {
            this.status = Status.INACTIVE;
            if (this.equipment != null) {
                this.setEquipment(null);
            }
            this.clearSimCards();
        } else if (this.status == Status.INACTIVE) {
            this.status = Status.ACTIVE;
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
            this.setStatus(Status.INACTIVE);
        }
    }

    public void clearSimCards() {
        if (simCards == null) {
            return;
        }
        for (SimCard simCard : simCards.values()) {
            simCard.setModem(null);
        }
        simCards.clear();
    }

    public enum Status {
        ACTIVE,
        INACTIVE
    }
}