package com.graduate.work.model.dto;

public record ClientPageDto(
        int page,
        int size,
        String sortingField,
        String sortingOrder,
        String searchName,
        String searchLastName,
        String searchLogin,
        String searchEmail,
        String searchRole
) {

}
