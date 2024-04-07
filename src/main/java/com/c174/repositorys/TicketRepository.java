package com.c174.repositorys;

import com.c174.models.ticket.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
    @Query("SELECT t FROM TicketEntity t WHERE t.owner.id = ?1")
    List<TicketEntity> findByProfileId(Long id);

    @Query("SELECT t FROM TicketEntity t WHERE t.event.id = ?1")
    List<TicketEntity> findByEventId(Long id);
}
