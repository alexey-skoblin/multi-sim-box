package com.graduate.work.entity_and_event_generator.repository.specification;

import com.graduate.work.model.entity.Client;
import com.graduate.work.model.types.Role;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ClientSpecification {

    public static Specification<Client> bySearchCriteria(String searchName, String searchLastName, String searchLogin, String searchEmail, String searchRole) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (!searchName.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + searchName + "%"));
            }
            if (!searchLastName.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("lastName"), "%" + searchLastName + "%"));
            }
            if (!searchLogin.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("login"), "%" + searchLogin + "%"));
            }
            if (!searchEmail.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("email"), "%" + searchEmail + "%"));
            }
            if (!searchRole.isEmpty()) {
                Role role = Role.getRole(searchRole);
                if (role != null) {
                    predicates.add(criteriaBuilder.equal(root.get("role"), role));
                }
            }

            return criteriaBuilder.and(predicates.toArray(new jakarta.persistence.criteria.Predicate[0]));

        };
    }
}