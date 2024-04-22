package com.c174.services.implementation;

import com.c174.exception.AlreadyExistsException;
import com.c174.exception.EntityDeleteException;
import com.c174.exception.EntityNotFoundException;
import com.c174.exception.EntityUploadException;
import com.c174.models.event.EventEntity;
import com.c174.models.ticket.*;
import com.c174.repositorys.EventRepository;
import com.c174.repositorys.TicketRepository;
import com.c174.services.abstraccion.TicketService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImplementation implements TicketService {
    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;
    private final TicketMapper ticketMapper;
    private final EnterpriseConsumeServiceImp enterpriseConsumeServiceImp;
    public TicketServiceImplementation(TicketRepository ticketRepository,
                                       EventRepository eventRepository,
                                       TicketMapper ticketMapper,
                                       EnterpriseConsumeServiceImp enterpriseConsumeServiceImp) {

        this.ticketRepository = ticketRepository;
        this.eventRepository = eventRepository;
        this.ticketMapper = ticketMapper;

        this.enterpriseConsumeServiceImp = enterpriseConsumeServiceImp;
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
    public TicketResponse create(TicketEnterpriceDto ticketRequest) throws EntityNotFoundException {
        EventEntity event = eventRepository.findByNameIgnoreCaseContaining(ticketRequest.getEventName()).get(0);

        TicketEntity ticket = new TicketEntity(ticketRequest);
        ticket.setEvent(event);
        ticketRepository.save(ticket);

        TicketResponse ticketResponse = ticketMapper.toTicketResponse(ticket);
        ticketResponse.setMeta(ticketRequest.getQr());

        return ticketResponse;
    }

    @Override
    public List<TicketResponse> listTickets() {
        return ticketMapper.toListTicketResponse(ticketRepository.findAll());
    }

    @Override
    public TicketResponse checkTicket(File file) {
        TicketEnterpriceDto ticketEnterprice = enterpriseConsumeServiceImp.checkTicket(file);
        TicketResponse ticketResponse = TicketResponse.builder()
                .id(ticketEnterprice.getId())
                .build();

        return ticketResponse;
    }

    @Override
    public TicketResponse renewQr(File file) {
        TicketEnterpriceDto ticketEnterpriceDto = enterpriseConsumeServiceImp.renewQr(file);
        TicketResponse ticketResponse = TicketResponse.builder()
                .id(ticketEnterpriceDto.getId())
                .build();
        return null;
    }

    @Override
    public TicketResponse lockTicket(Long id) {
        TicketEnterpriceDto ticketEnterpriceDto = enterpriseConsumeServiceImp.lockTicket(id);
        TicketResponse ticketResponse = TicketResponse.builder()
                .id(ticketEnterpriceDto.getId())
                .build();
        return null;
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
    @Transactional(readOnly = true)
    public List<TicketResponse> getTicketByEvent(Long id) {
        List<TicketEntity> tickets = ticketRepository.findByEventId(id);
        if(tickets == null || tickets.isEmpty()){
            throw new NullPointerException("No tickets found");
        }
        List<TicketResponse> ticketResponse = ticketMapper.toListTicketResponse(tickets);
        return ticketResponse;
    }

    @Override
    public List<TicketResponse> getTicketByEvent(String name) {
        List<TicketEntity> tickets =ticketRepository.findByEventName(name);
        List<TicketResponse> ticketResponses = ticketMapper.toListTicketResponse(tickets);

        return ticketResponses;
    }

    @Override
    public List<TicketResponse> findTicketsByProfileAndByLock(Long id, Boolean lock) {
        List<TicketEntity> tickets = ticketRepository.findByProfileIdAndByLock(id,lock);
        List<TicketResponse> ticketResponses = ticketMapper.toListTicketResponse(tickets);

        return ticketResponses;
    }

    @Override
    public List<TicketResponse> findAllTicketsNotLock() {
        List<TicketEntity> tickets = ticketRepository.findAllLock(false);
        List<TicketResponse> ticketResponses = ticketMapper.toListTicketResponse(tickets);

        return ticketResponses;
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
        Optional<TicketEntity> ticket = ticketRepository.updateTicket(request.getEvent().getId(),
                request.getPrice(),
                request.getOwner().getId(),
                request.getMeta(),
                request.getId());
        if (ticket.isPresent()) return ticketMapper.toTicketResponse(ticket.get());

        return null;
    }

    @Override
    public List<TicketResponse> getByName(String name) {
        return null;
    }




}
