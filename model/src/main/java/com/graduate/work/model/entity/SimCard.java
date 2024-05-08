package com.graduate.work.model.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.awt.*;
import java.awt.geom.Point2D;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Builder
@ToString
public class SimCard {

    @Id
    private Long id;

    private String iccid;
    private Status status;
    private String defNumber;
    private String mobileOperator;
    private String tariff;
    private long lastActivationDate;
    private long lastDeactivationDate;
    private Point2D.Double lastLocation;
    private Double trafficForYesterday;

    @ToString.Exclude
    @ManyToOne
    private Client client;

    @ToString.Exclude
    @ManyToOne
    private Modem modem;

    @Getter
    public enum Status {
        ACTIVE("Active"),
        DEACTIVATE("Deactivate"),
        BLOCKED("Blocked");

        private final String status;

        Status(String status) {
            this.status = status;
        }
    }
}