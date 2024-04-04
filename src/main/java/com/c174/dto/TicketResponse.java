package com.c174.dto;

import com.c174.models.Ticket;

public record TicketResponse(String event,
                             Long id,
                             String qr) {


    public TicketResponse(Ticket ticket){
        this(ticket.getEventName(), ticket.getId(), ticket.getQr());
    }
}
