package com.graduate.work.entity_and_event_generator.repository;

import com.graduate.work.model.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {
    @Query("select p.id from #{#entityName} p")
    List<Long> getAllIds();

    List<Facility> findByClientNull();
}
