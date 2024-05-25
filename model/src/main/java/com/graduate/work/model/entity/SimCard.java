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
public class SimCard {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String iccid;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Setter
    private String defNumber;

    @Setter
    private String mobileOperator;

    @Setter
    private String tariff;

    @Setter
    private long lastActionDate;

    @Setter
    @Convert(converter = Point2DConverter.class)
    private Point2D.Double lastLocation;

    @Setter
    private Double trafficForYesterday;

    @ToString.Exclude
    @ManyToOne
    private Client client;

    @ToString.Exclude
    @ManyToOne
    private Modem modem;

    public void setStatus(Status status){
        if (this.status == status) {
            return;
        }
        if (this.status == Status.ACTIVE) {
            this.status = Status.INACTIVE;
        } else if (this.status == Status.INACTIVE) {
            this.status = Status.ACTIVE;
        }
        this.setLastActionDate(System.currentTimeMillis());
    }

    public void setClient(Client client) {
        if (this.client != null) {
            this.client.getSimCards().remove(this.id);
        }
        this.client = client;
        if (client != null) {
            if (client.getSimCards() == null) {
                client.setSimCards(new HashMap<>());
            }

            client.getSimCards().put(this.id, this);
        } else {
            this.setStatus(Status.INACTIVE);
        }
    }

    public void setModem(Modem modem) {
        if (this.modem != null) {
            this.modem.getSimCards().remove(this.id);
        }
        this.modem = modem;
        if (modem != null) {
            if (modem.getSimCards() == null) {
                modem.setSimCards(new HashMap<>());
            }
            modem.getSimCards().put(this.id, this);
        }
    }

    @Getter
    public enum Status {
        ACTIVE,
        INACTIVE;
    }
}