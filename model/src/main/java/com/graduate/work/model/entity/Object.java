package com.graduate.work.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.awt.geom.Point2D;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Builder
@ToString
public class Object {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Setter
    String address;

    @Setter
    Point2D.Double location;

    @Setter
    Status status;

    @ManyToOne
    @ToString.Exclude
    private Client client;

    public void setClient(Client client) {
        if (this.client != null) {
            this.client.removeObject(this);
        }
        if (client != null) {
            this.client = client;
            if (!client.containsObject(this)) {
                client.addObject(this);
            }
        } else {
            this.client = null;
        }
    }

    @OneToOne
    @ToString.Exclude
    private Equipment equipment;

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
        if (equipment != null) {
            equipment.setObject(this);
        }
    }

    @Getter
    public enum Status {
        ACTIVE,
        INACTIVE
    }

}