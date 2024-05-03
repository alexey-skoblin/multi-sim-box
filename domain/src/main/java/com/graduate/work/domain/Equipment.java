package com.graduate.work.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
public class Equipment {

    @Id
    Long id;

    String hostname;
    String model;
    String serialNumber;

    @OneToMany
    private List<Modem> modems;

}