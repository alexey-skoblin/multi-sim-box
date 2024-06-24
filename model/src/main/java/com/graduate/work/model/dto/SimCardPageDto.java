package com.graduate.work.model.dto;

public record SimCardPageDto(
        int page,
        int size,
        String sortingField,
        String sortingOrder,
        String searchMobileOperator,
        String searchIccid,
        String searchDefNumber,
        String searchAddress,
        String searchSerialNumber
) {
}
