package com.graduate.work.entity_and_event_generator.repository.jpa;

import com.graduate.work.model.entity.Modem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModemRepository extends JpaRepository<Modem, Long> {

    @Query("select p.id from #{#entityName} p")
    List<Long> getAllIds();

    List<Modem> findByEquipmentNull();
}
