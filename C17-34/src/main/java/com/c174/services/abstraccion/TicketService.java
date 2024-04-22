package com.c174.services.abstraccion;

import com.c174.exception.EntityNotFoundException;
import com.c174.models.ticket.TicketEnterpriceDto;
import com.c174.models.ticket.TicketRequest;
import com.c174.models.ticket.TicketResponse;

import java.io.File;
import java.util.List;

public interface TicketService extends GenericService<TicketResponse, TicketRequest>{

    TicketResponse create(TicketEnterpriceDto ticketRequest) throws EntityNotFoundException;
    List<TicketResponse> listTickets();
    TicketResponse checkTicket(File file);
    TicketResponse renewQr(File file);
    TicketResponse lockTicket(Long id);
    List<TicketResponse> getTicketByProfile(Long id);
    List<TicketResponse> getTicketByEvent(Long id);
    List<TicketResponse> getTicketByEvent(String  name);
    List<TicketResponse> findTicketsByProfileAndByLock(Long id, Boolean lock);
    List<TicketResponse> findAllTicketsNotLock();
}
