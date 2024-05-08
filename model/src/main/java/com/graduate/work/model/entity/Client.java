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
public class Client{

    @Id
    private Long id;

    String name;
    String surname;
    String login;
    String hash;
    String email;
    String ip;

    @Setter
    @OneToMany
    @ToString.Exclude
    @Builder.Default
    private List<SimCard> simCards = new ArrayList<>();

    @Setter
    @OneToMany
    @ToString.Exclude
    @Builder.Default
    private List<Object> objects = new ArrayList<>();
}