package com.graduate.work.model.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.awt.geom.Point2D;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Builder
@ToString
public class Object {

    @Id
    Long id;

    String address;
    Point2D.Double location;
    Status status;

    @Setter
    @ManyToOne
    @ToString.Exclude
    private Client client;

    @Getter
    public enum Status {
        ACTIVE("Active"),
        BLOCKED("Blocked");

        private final String status;

        Status(String status) {
            this.status = status;
        }
    }

}