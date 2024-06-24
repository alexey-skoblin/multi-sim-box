package com.graduate.work.model.entity;

import com.graduate.work.model.types.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
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
    private String name;

    @Setter
    private String lastName;

    @Setter
    private String login;

    @Setter
    private String hash;

    @Setter
    private String email;

    @Setter
    private Role role;

    @Setter
    private String ip;

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
        List<SimCard> simCardsList = new java.util.ArrayList<>(simCards.values());
        for (SimCard simCard : simCardsList) {
            simCard.setClient(null);
        }
        simCards.clear();
    }

    public void clearFacilities() {
        if (facilities == null) {
            return;
        }
        List<Facility> facilitiesList = new java.util.ArrayList<>(facilities.values());
        for (Facility facility : facilitiesList) {
            facility.setClient(null);
        }
        facilities.clear();
    }
}