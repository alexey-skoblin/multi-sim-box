package com.graduate.work.entity_and_event_generator.repository.jpa;

import com.graduate.work.model.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findAll(Specification<Task> specification, Pageable pageable);

    @Query("select p.id from #{#entityName} p")
    List<Long> getAllIds();
}
