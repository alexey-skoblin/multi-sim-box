package com.graduate.work.model.entity;


import com.graduate.work.model.converter.Point2DConverter;
import com.graduate.work.model.types.SimCardStatus;
import jakarta.persistence.*;
import lombok.*;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Builder
@ToString
public class SimCard {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String iccid;

    @Enumerated(EnumType.STRING)
    private SimCardStatus simCardStatus;

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

    @Setter
    @OneToMany(mappedBy = "simCard", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "id")
    @ToString.Exclude
    private Map<Long, Task> tasks;

    public void setSimCardStatus(SimCardStatus simCardStatus) {
        if (this.simCardStatus == simCardStatus) {
            return;
        }
        if (this.simCardStatus == SimCardStatus.ACTIVE) {
            this.simCardStatus = SimCardStatus.INACTIVE;
        } else if (this.simCardStatus == SimCardStatus.INACTIVE) {
            this.simCardStatus = SimCardStatus.ACTIVE;
        }
        this.setLastActionDate(System.currentTimeMillis());
    }

    public void setClient(Client client) {
        if (this.client == client) {
            return;
        }
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
            this.setSimCardStatus(SimCardStatus.INACTIVE);
        }
    }

    public void setModem(Modem modem) {
        if (this.modem == modem) {
            return;
        }
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

}