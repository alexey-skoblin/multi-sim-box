package com.graduate.work.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class Modem {

    @Id
    String imei;

    @OneToMany
    private Map<String, SimCard> simCards;
    /*
    !IMEI модема
                ->(Оборудование) Серийный номер оборудования
                ->>(Sim карты) ICCID сим карты (<> со статусом сим карт)*/
}
