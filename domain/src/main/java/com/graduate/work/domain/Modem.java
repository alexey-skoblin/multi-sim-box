package com.graduate.work.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
public class Modem {

    @Id
    private Long id;

    private String imei;

    @ManyToOne
    private Equipment equipment;

    @OneToMany
    private List<SimCard> simCards;
    /*
    !IMEI модема
                ->(Оборудование) Серийный номер оборудования
                ->>(Sim карты) ICCID сим карты (<> со статусом сим карт)*/
}