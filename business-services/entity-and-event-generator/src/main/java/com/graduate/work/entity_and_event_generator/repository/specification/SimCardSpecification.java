package com.graduate.work.entity_and_event_generator.repository.specification;

import com.graduate.work.model.entity.Equipment;
import com.graduate.work.model.entity.Facility;
import com.graduate.work.model.entity.Modem;
import com.graduate.work.model.entity.SimCard;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SimCardSpecification {

    public static Specification<SimCard> bySearchCriteria(String searchMobileOperator, String searchIccid, String searchDefNumber, String searchAddress, String searchSerialNumber) {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);

            List<jakarta.persistence.criteria.Predicate> predicates = new ArrayList<>();

            if (!searchMobileOperator.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("mobileOperator"), "%" + searchMobileOperator + "%"));
            }
            if (!searchIccid.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("iccid"), "%" + searchIccid + "%"));
            }
            if (!searchDefNumber.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("defNumber"), "%" + searchDefNumber + "%"));
            }

            if (!searchAddress.isEmpty() || !searchSerialNumber.isEmpty()) {
                Join<SimCard, Modem> modemJoin = root.join("modem", JoinType.LEFT);
                if (modemJoin != null) {
                    Join<Modem, Equipment> equipmentJoin = modemJoin.join("equipment", JoinType.LEFT);
                    if (equipmentJoin != null) {
                        if (!searchSerialNumber.isEmpty()) {
                            predicates.add(criteriaBuilder.like(equipmentJoin.get("serialNumber"), "%" + searchSerialNumber + "%"));
                        }
                        Join<Equipment, Facility> facilityJoin = equipmentJoin.join("facility", JoinType.LEFT);
                        if (facilityJoin != null) {
                            if (!searchAddress.isEmpty()) {
                                predicates.add(criteriaBuilder.like(facilityJoin.get("address"), "%" + searchAddress + "%"));
                            }
                        }
                    }
                }
            }

            return criteriaBuilder.and(predicates.toArray(new jakarta.persistence.criteria.Predicate[0]));
        };
    }
}
