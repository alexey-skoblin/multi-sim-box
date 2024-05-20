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
public class SimCard {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String iccid;

    @Setter
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
    private Point2D.Double lastLocation;

    @Setter
    private Double trafficForYesterday;

    @ToString.Exclude
    @ManyToOne
    private Client client;

    public void setClient(Client client) {
        if (this.client != null) {
            this.client.removeSimCard(this);
        }
        if (client != null) {
            this.client = client;
            if (!client.containsSimCard(this)) {
                client.addSimCard(this);
            }
        } else {
            this.client = null;
        }
    }

    @ToString.Exclude
    @ManyToOne
    private Modem modem;

    public void setModem(Modem modem) {
        if (this.modem != null) {
            this.modem.removeSimCard(this);
        }
        if (modem != null) {
            this.modem = modem;
            if (!modem.containsSimCard(this)) {
                modem.addSimCard(this);
            }
        } else {
            this.modem = null;
        }
    }

    @Getter
    public enum Status {
        ACTIVE,
        INACTIVE;
    }
}