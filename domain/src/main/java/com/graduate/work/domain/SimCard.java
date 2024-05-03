package com.graduate.work.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
public class SimCard {

    @Id
    private Long id;

    private String iccid;
    private Status status;
    private String defNumber;
    private String mobileOperator;
    private String tariff;
    private LocalDateTime lastActivation;
    private Point lastLocation;
    private Double trafficForYesterday;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Modem modem;

    @Getter
    public enum Status {
        ACTIVE("Active"),
        BLOCKED("Blocked");

        private final String status;

        Status(String status) {
            this.status = status;
        }
    }
}