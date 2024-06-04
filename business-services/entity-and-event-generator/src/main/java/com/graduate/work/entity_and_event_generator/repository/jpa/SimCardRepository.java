package com.graduate.work.entity_and_event_generator.repository.jpa;

import com.graduate.work.model.entity.SimCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SimCardRepository extends JpaRepository<SimCard, Long> {

    @Query("select p.id from #{#entityName} p")
    List<Long> getAllIds();

    List<SimCard> findByClientNull();

    List<SimCard> findAllNotModemByClient_Id(Long id);

    SimCard findByIccid(String iccid);

    @Transactional
    @Modifying
    @Query("update SimCard s set s.status = ?1 where s.iccid = ?2")
    int updateStatusByIccid(SimCard.Status status, String iccid);

}
