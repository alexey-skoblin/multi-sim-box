package com.graduate.work.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Builder
@ToString
public class Client {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    String name;

    @Getter
    @Setter
    String lastName;

    @Getter
    @Setter
    String login;

    @Getter
    @Setter
    String hash;

    @Getter
    @Setter
    String email;

    @Getter
    @Setter
    String ip;

    @OneToMany(mappedBy = "client")
    @ToString.Exclude
    @Builder.Default
    private List<SimCard> simCards = new ArrayList<>();

    public int sizeSimCards() {
        return simCards.size();
    }

    public void addSimCard(SimCard simCard) {
        simCards.add(simCard);
        if (simCard.getClient() != this) {
            simCard.setClient(this);
        }
    }

    public SimCard getSimCard(int id) {
        return simCards.get(id);
    }

    public boolean containsSimCard(SimCard simCard) {
        return simCards.contains(simCard);
    }

    public void removeSimCard(SimCard simCard) {
        simCards.remove(simCard);
        simCard.setClient(null);
    }

    @OneToMany(mappedBy = "client")
    @ToString.Exclude
    @Builder.Default
    private List<Object> objects = new ArrayList<>();

    public int sizeObjects() {
        return objects.size();
    }

    public void addObject(Object object) {
        objects.add(object);
        if (object.getClient() != this) {
            object.setClient(this);
        }
    }

    public Object getObject(int id) {
        return objects.get(id);
    }

    public boolean containsObject(Object object) {
        return objects.contains(object);
    }

    public void removeObject(Object object) {
        objects.remove(object);
        object.setClient(null);
    }

}