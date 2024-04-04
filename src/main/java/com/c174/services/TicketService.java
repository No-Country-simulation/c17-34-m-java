package com.c174.services;

import com.c174.dto.TicketDTO;
import com.c174.dto.TicketResponse;
import com.c174.models.Ticket;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface TicketService {
    TicketDTO create(TicketDTO ticket);
    TicketDTO update(Long id, TicketDTO ticketDTO);
    TicketDTO disable(Long id);
    TicketDTO lockTicket(Long id);
    TicketDTO checkTicket(File file) throws IOException;
    TicketDTO renewQr(File file) throws IOException;
    List<TicketDTO> findDisabledTickets();
    List<TicketDTO> findAllAvailableTickets();
    List<TicketDTO> findAllLockedTickets();
    List<TicketDTO>findTicketsByEvent(String eventName);
}
