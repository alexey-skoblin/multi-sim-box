package com.graduate.work.model.entity;


import com.graduate.work.model.converter.Point2DConverter;
import jakarta.persistence.*;
import lombok.*;

import java.awt.geom.Point2D;
import java.util.HashMap;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Builder
@ToString
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Setter
    String address;

    @Setter
    @Convert(converter = Point2DConverter.class)
    Point2D.Double location;

    @Enumerated(EnumType.STRING)
    Status status;

    @ManyToOne
    @ToString.Exclude
    private Client client;

    @OneToOne
    private Equipment equipment;

    public void setStatus(Status status) {
        if (this.status == status) {
            return;
        }
        if (this.status == Status.ACTIVE) {
            this.status = Status.INACTIVE;
            if (this.equipment != null) {
                this.setEquipment(null);
            }
        } else if (this.status == Status.INACTIVE) {
            this.status = Status.ACTIVE;
        }
    }

    public void setClient(Client client) {
        if (this.client == client) {
            return;
        }
        if (this.client != null) {
            this.client.getFacilities().remove(this.id);
        }
        this.client = client;
        if (client != null) {
            if (client.getFacilities() == null) {
                client.setFacilities(new HashMap<>());
            }
            client.getFacilities().put(this.id, this);
        } else {
            this.setStatus(Status.INACTIVE);
        }
    }

    public void setEquipment(Equipment equipment) {
        if (this.equipment == equipment) {
            return;
        }
        if (this.equipment != null) {
            Equipment oldEquipment = this.equipment;
            this.equipment = null;
            oldEquipment.setFacility(null);
        }
        this.equipment = equipment;
        if (equipment != null) {
            equipment.setFacility(this);
        }
    }

    @Getter
    public enum Status {
        ACTIVE,
        INACTIVE
    }

}