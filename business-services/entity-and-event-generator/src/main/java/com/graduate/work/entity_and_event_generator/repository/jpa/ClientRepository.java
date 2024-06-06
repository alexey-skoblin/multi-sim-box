package com.graduate.work.entity_and_event_generator.repository.jpa;

import com.graduate.work.model.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("select p.id from #{#entityName} p")
    List<Long> getAllIds();

    Page<Client> findAll(Specification<Client> specification, Pageable pageable);
}
