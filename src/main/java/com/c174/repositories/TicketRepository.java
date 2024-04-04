package com.c174.repositories;

import com.c174.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT t FROM Ticket t WHERE t.eventName = :eventName")
    List<Ticket> findTicketsByEvent(@Param("event")String eventName);
    @Query("SELECT t FROM Ticket t WHERE t.isPresent = false")
    List<Ticket> findDisabledTickets();
    @Query("SELECT t FROM Ticket t WHERE t.isPresent = true AND t.isLocked = false")
    List<Ticket> findAllAvailableTickets();
    @Query("SELECT t FROM Ticket t WHERE t.isPresent = true AND t.isLocked = true")
    List<Ticket> findAllLockedTickets();
    @Query("SELECT t FROM Ticket t WHERE t.qr = :qr AND t.isPresent = true")
    Optional<Ticket> checkTicket(@Param("qr")String qr);

}
