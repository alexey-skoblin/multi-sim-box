package com.graduate.work.model.dto;

import com.graduate.work.model.types.Role;

import java.io.Serializable;

/**
 * DTO for {@link com.graduate.work.model.entity.Client}
 */
public record ClientDto(
        Long id,
        String name,
        String lastName,
        String login,
        String email,
        Role role,
        String ip
) implements Serializable {
}