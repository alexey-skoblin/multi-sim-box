package com.graduate.work.model.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class Equipment {

    @Id
    Long id;

    String hostname;
    String model;
    String serialNumber;

    @Setter
    @OneToMany
    @ToString.Exclude
    @Builder.Default
    private List<Modem> modems = new ArrayList<>();
}