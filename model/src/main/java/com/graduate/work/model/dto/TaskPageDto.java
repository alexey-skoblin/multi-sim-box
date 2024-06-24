package com.graduate.work.model.dto;

public record TaskPageDto(
        int page,
        int size,
        String sortingField,
        String sortingOrder,
        String searchTaskStatus
) {
}
