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
public class Client {

    @Id
    private Long id;

    String name;
    String surname;
    String login;
    String password; //TODO: Hash password
    String email;
    String ip;

    @OneToMany
    private List<SimCard> simCards;

    @OneToMany
    private List<Object> objects;

}