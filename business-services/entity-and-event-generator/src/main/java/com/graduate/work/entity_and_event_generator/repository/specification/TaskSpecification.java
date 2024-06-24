package com.graduate.work.entity_and_event_generator.repository.specification;

import com.graduate.work.model.entity.Task;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecification {

    public static Specification<Task> BySearchCriteria(String taskStatus) {
        return (root, query, criteriaBuilder) -> {
            if (taskStatus != null) {
                return criteriaBuilder.equal(root.get("status"), taskStatus);
            }
            return null;
        };
    }
}
