package com.graduate.work.entity_and_event_generator.repository;

import com.graduate.work.model.entity.Modem;
import com.graduate.work.model.entity.SimCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModemRepository extends JpaRepository<Modem, Long> {

    @Query("select p.id from #{#entityName} p")
    List<Long> getAllIds();

    @Query("SELECT m FROM Modem m JOIN m.simCards s JOIN s.client u WHERE u.id = :clientId AND SIZE(m.simCards) > 0 AND SIZE(m.simCards) < :n")
    List<Modem> findModemsWithSimOwnedByUserAndNumberOfSimsInRange(long clientId, int n);


}
