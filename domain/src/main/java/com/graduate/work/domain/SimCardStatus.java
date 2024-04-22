package com.graduate.work.domain;

import lombok.Getter;

@Getter
public enum SimCardStatus {
    ACTIVE("Active"),
    BLOCKED("Blocked");

    private final String status;

    SimCardStatus(String status) {
        this.status = status;
    }

}
