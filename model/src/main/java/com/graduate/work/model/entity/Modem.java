package com.graduate.work.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Builder
@ToString
public class Modem {

    @Id
    @Getter
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private Status status;

    @Getter
    private String imei;

    @Getter
    @ManyToOne
    @ToString.Exclude
    private Equipment equipment;

    public void setEquipment(Equipment equipment) {
        if (this.equipment != null) {
            this.equipment.removeModem(this);
        }
        if (equipment != null) {
            this.equipment = equipment;
            equipment.addModem(this);
        } else {
            this.equipment = null;
        }
    }

    @OneToMany(mappedBy = "modem")
    @ToString.Exclude
    @Builder.Default
    private List<SimCard> simCards = new ArrayList<>();

    public int getSizeSimCards() {
        return simCards.size();
    }

    public void addSimCard(SimCard simCard) {
        simCards.add(simCard);
        if (simCard.getModem() != this) {
            simCard.setModem(this);
        }
    }

    public SimCard getSimCard(int id){
       return simCards.get(id);
    }

    public boolean containsSimCard(SimCard simCard) {
        return simCards.contains(simCard);
    }

    public void removeSimCard(SimCard simCard) {
        simCards.remove(simCard);
        simCard.setModem(null);
    }

    public enum Status {
        ACTIVE,
        INACTIVE
    }
}