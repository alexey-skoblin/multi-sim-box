package com.graduate.work.entity_and_event_generator.repository;

import com.graduate.work.model.entity.Object;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjectRepository extends JpaRepository<Object, Long> {
    @Query("select p.id from #{#entityName} p")
    List<Long> getAllIds();
}
