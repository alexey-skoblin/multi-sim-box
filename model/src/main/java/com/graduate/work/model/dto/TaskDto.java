package com.graduate.work.model.dto;

import com.graduate.work.model.types.SimCardStatus;
import com.graduate.work.model.types.TaskStatus;

import java.io.Serializable;

/**
 * DTO for {@link com.graduate.work.model.entity.Task}
 */
public record TaskDto(
        Long id,
        TaskStatus status,
        Long taskDate,
        String simCardIccid,
        SimCardStatus simCardStatus,
        String simCardDefNumber,
        String simCardMobileOperator,
        String clientName,
        String clientLastName,
        String clientEmail
) implements Serializable {
}