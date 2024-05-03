package com.graduate.work.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.awt.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
public class Object {

    @Id
    Long id;

    String address;
    Point location;
    Status status;

    @ManyToOne
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