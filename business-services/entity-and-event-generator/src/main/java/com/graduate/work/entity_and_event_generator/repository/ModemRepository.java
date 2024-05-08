package com.graduate.work.entity_and_event_generator.repository;

import com.graduate.work.model.entity.Modem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModemRepository extends JpaRepository<Modem, Long> {
}
