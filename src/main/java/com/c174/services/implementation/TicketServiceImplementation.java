package com.c174.services.implementation;

import com.c174.exception.AlreadyExistsException;
import com.c174.exception.EntityDeleteException;
import com.c174.exception.EntityNotFoundException;
import com.c174.exception.EntityUploadException;
import com.c174.models.ticket.*;
import com.c174.repositorys.TicketRepository;
import com.c174.services.abstraccion.TicketService;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImplementation implements TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    public TicketServiceImplementation(TicketRepository ticketRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;

    }
    @Override
    @Transactional(readOnly = true)
    public List<TicketResponse> getAll() throws NullPointerException {
        List<TicketEntity> tickets = ticketRepository.findAll();
        if(tickets == null || tickets.isEmpty()){
            throw new NullPointerException("No tickets found");
        }
        List<TicketResponse> ticketResponse = ticketMapper.toListTicketResponse(tickets);
        return ticketResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketResponse> getTicketByProfile(Long id) throws NullPointerException {
        List<TicketEntity> tickets = ticketRepository.findByProfileId(id);
        if(tickets == null || tickets.isEmpty()){
            throw new NullPointerException("No tickets found");
        }
        List<TicketResponse> ticketResponse = ticketMapper.toListTicketResponse(tickets);
        return ticketResponse;
    }

    @Override
    public List<TicketResponse> getTicketByEvent(Long id) {
        List<TicketEntity> tickets = ticketRepository.findByEventId(id);
        if(tickets == null || tickets.isEmpty()){
            throw new NullPointerException("No tickets found");
        }
        List<TicketResponse> ticketResponse = ticketMapper.toListTicketResponse(tickets);
        return ticketResponse;
    }

    @Override
    public TicketResponse save(TicketRequest request) throws AlreadyExistsException {
        if(ticketRepository.existsById(request.getId())){
            throw new AlreadyExistsException("Ticket already exists");
        }
        TicketEntity ticket = ticketMapper.toTicketEntity(request);
        TicketEntity ticketResponse = ticketRepository.save(ticket);
        return ticketMapper.toTicketResponse(ticketResponse);
    }



    @Override
    public TicketResponse getById(Long id) throws EntityNotFoundException {
        Optional<TicketEntity> searchTicket = ticketRepository.findById(id);
        if(searchTicket.isEmpty() || searchTicket == null){
            throw new EntityNotFoundException("No tickets found");
        }
        TicketResponse response = ticketMapper.toTicketResponse(searchTicket.get());
        return response;
    }
    @Override
    public String delete(Long id) throws EntityDeleteException {
        return null;
    }

    @Override
    public TicketResponse update(Long id, TicketRequest request) throws EntityUploadException {
        return null;
    }

    @Override
    public List<TicketResponse> getByName(String name) {
        return null;
    }




}
