package com.c174.services.implementation;

import com.c174.exception.*;
import com.c174.models.event.EventEntity;
import com.c174.models.ticket.*;
import com.c174.repositorys.EventRepository;
import com.c174.repositorys.TicketRepository;
import com.c174.services.abstraccion.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImp implements TicketService {
    @Override
    public List<TicketResponse> getTicketByEvent(String name) {
        return null;
    }

    private final EnterpriseConsumeServiceImp enterpriseConsumeServiceImp;
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final EventRepository eventRepository;

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
    public List<TicketResponse> getTicketByProfile(Long id) {

        List<TicketEntity> tickets = ticketRepository.findByProfileId(id);
        if(tickets == null || tickets.isEmpty()){
            throw new NullPointerException("No tickets found");
        }
        List<TicketResponse> ticketResponse = ticketMapper.toListTicketResponse(tickets);
        return ticketResponse;
    }

    @Override
    public List<TicketResponse> getTicketByEvent(Long id) {
        return null;
    }

    @Override
    public List<TicketResponse> findTicketsByProfileAndByLock(Long id, Boolean lock) {
        return null;
    }

    @Override
    public List<TicketResponse> findAllTicketsNotLock() {
        return null;
    }

    @Override
    public List<TicketResponse> getAll() throws EntityNotFoundException {
        return null;
    }

    @Override
    public List<TicketResponse> getByName(String name) throws EntityNotFoundException {
        return null;
    }

    @Override
    public TicketResponse getById(Long id) throws EntityNotFoundException {
        return null;
    }

    @Override
    public TicketResponse save(TicketRequest request) throws AlreadyExistsException, EntityExistsException {
        return ticketMapper
                .toTicketResponse(ticketRepository
                        .save(ticketMapper
                                .toTicketEntity(request)));
    }

    @Override
    public String delete(Long id) throws EntityDeleteException, EntityNotFoundException {
        return null;
    }

    @Override
    public TicketResponse update(Long id, TicketRequest request) throws EntityUploadException, EntityNotFoundException {
        return null;
    }
}
