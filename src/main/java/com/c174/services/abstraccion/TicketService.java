package com.c174.services.abstraccion;

import com.c174.exception.AlreadyExistsException;
import com.c174.models.ticket.TicketRequest;
import com.c174.models.ticket.TicketResponse;
import com.c174.models.ticket.TicketShop;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;

import java.util.List;

public interface TicketService extends GenericService<TicketResponse, TicketRequest>{

    List<TicketResponse> getTicketByProfile(Long id);
    List<TicketResponse> getTicketByEvent(Long id);
}
