package com.graduate.work.model.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Builder
@ToString
public class Modem {

    @Id
    private Long id;

    private String imei;

    @Setter
    @ManyToOne
    @ToString.Exclude
    private Equipment equipment;

    @Setter
    @OneToMany
    @ToString.Exclude
    @Builder.Default
    private List<SimCard> simCards = new ArrayList<>();

    /*
    !IMEI модема
                ->(Оборудование) Серийный номер оборудования
                ->>(Sim карты) ICCID сим карты (<> со статусом сим карт)*/
}