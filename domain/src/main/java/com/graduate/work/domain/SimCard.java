package com.graduate.work.domain;


import lombok.Getter;

import javax.persistence.*;
import java.awt.*;
import java.time.LocalDateTime;

@Getter
@Entity
public class SimCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "iccid")
    @org.hibernate.annotations.GenericGenerator(
            name = "iccid",
            strategy = "com.graduate.work.domain.generator.IccidGenerator"
    )
    String iccid;

    SimCardStatus status;
    String defNumber;
    String mobileOperator;
    String tariff;
    LocalDateTime lastActivation;
    Point lastLocation;
    Double TrafficForYesterday;

    @ManyToOne
    Modem modem;
    /*
     !ICCID
    Статус SIM
    Def номер
    Тариф SIM карты
    Дата последней активации/деактивации - где то надо хранить в частности все действия с симкартами
    Геопозиция последнего расположения SIM
    Израсходованный траффик за прошедшие сутки
                ->(Модем) IMEI последнего модема через который передает SIM*/

}
