package com.graduate.work.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Builder
@ToString
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    String name;
    @Setter
    String lastName;
    @Setter
    String login;
    @Setter
    String hash;
    @Setter
    String email;
    @Setter
    String ip;

    @Setter
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "id")
    @ToString.Exclude
    private Map<Long, SimCard> simCards;

    @Setter
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "id")
    @ToString.Exclude
    private Map<Long, Facility> facilities;

    public void clearSimCards() {
        if (simCards == null) {
            return;
        }
        for (SimCard simCard : simCards.values()) {
            simCard.setClient(null);
        }
        simCards.clear();
    }

    public void clearFacilities() {
        if (facilities == null) {
            return;
        }
        for (Facility facility : facilities.values()) {
            facility.setClient(null);
        }
        facilities.clear();
    }
}