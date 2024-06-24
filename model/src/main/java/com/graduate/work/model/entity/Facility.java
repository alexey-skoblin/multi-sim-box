package com.graduate.work.model.entity;


import com.graduate.work.model.converter.Point2DConverter;
import com.graduate.work.model.types.FacilityStatus;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Setter
    private String address;

    @Setter
    @Convert(converter = Point2DConverter.class)
    private Point2D.Double location;

    @Enumerated(EnumType.STRING)
    private FacilityStatus facilityStatus;

    @ManyToOne
    @ToString.Exclude
    private Client client;

    @OneToOne
    private Equipment equipment;

    public void setFacilityStatus(FacilityStatus facilityStatus) {
        if (this.facilityStatus == facilityStatus) {
            return;
        }
        if (this.facilityStatus == FacilityStatus.ACTIVE) {
            this.facilityStatus = FacilityStatus.INACTIVE;
            if (this.equipment != null) {
                this.setEquipment(null);
            }
        } else if (this.facilityStatus == FacilityStatus.INACTIVE) {
            this.facilityStatus = FacilityStatus.ACTIVE;
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
            this.setFacilityStatus(FacilityStatus.INACTIVE);
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

}