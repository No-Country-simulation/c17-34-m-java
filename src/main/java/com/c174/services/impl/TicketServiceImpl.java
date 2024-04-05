package com.c174.services.impl;

import com.c174.dto.TicketDTO;
import com.c174.dto.TicketResponse;

import com.c174.models.Ticket;
import com.c174.repositories.TicketRepository;
import com.c174.services.TicketService;
import com.c174.tools.QrGeneration;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;

    /**
     * Este metodo crea un registro en una base de datos postgre
     * mediante una instancia de jpa repository
     *
     * @param ticketDTO informacion a persistir
     * @return la informacion persistida
     */
    @Override
    public TicketDTO create(TicketDTO ticketDTO) {
        LocalDateTime currentDateTime = LocalDateTime.now();

        Ticket ticket = new Ticket();
        ticket.setEventName(ticketDTO.getEventName());
        ticket.setIsPresent(true);
        ticket.setIsLocked(false);
        ticket.setCreationDate(currentDateTime);
        //Generate QR
        UUID uuid = UUID.randomUUID();
        String chain = uuid.toString();
        try {
            QrGeneration.generateQr(chain+".png",chain,200, 200);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
        ticket.setQr(chain);

        ticketRepository.save(ticket);

        return modelMapper.map(ticket, TicketDTO.class);
    }

    /**
     * MOdifica un registro de un ticket con nueva informacion
     *
     * @param id primary key para buscar el registro
     * @param ticketDTO nueva informacion
     * @return infomacion actualizada
     */
    @Override
    public TicketDTO update(Long id, TicketDTO ticketDTO) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);
        if(ticketOptional.isPresent()){
            Ticket ticket = ticketOptional.get();
            ticket.setEventName(ticketDTO.getEventName());
            ticketRepository.save(ticket);
            return modelMapper.map(ticket, TicketDTO.class);
        }
        return null;
    }

    /**
     * Localiza y da una baja logica a un registro de un ticket
     *
     * @param id primary key para buscar el registro
     * @return informacion dada de baja
     */
    @Override
    public TicketDTO disable(Long id) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);
        if(ticketOptional.isPresent()){
            Ticket ticket = ticketOptional.get();
            ticket.setIsPresent(false);
            ticketRepository.save(ticket);
            return modelMapper.map(ticket, TicketDTO.class);
        }
        return null;
    }

    /**
     * Metodo por el cual se bloquea un ticket que no va a poder entrar
     * en una transaccion
     *
     * @param id primary key para encontrar el registro del ticket
     * @return eñ toclet blequeado
     */
    @Override
    public TicketDTO lockTicket(Long id) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);
        if(ticketOptional.isPresent()){
            Ticket ticket = ticketOptional.get();
            ticket.setIsLocked(true);
            ticketRepository.save(ticket);
            return modelMapper.map(ticket, TicketDTO.class);
        }
        return null;
    }

    /**
     * Chequea el estado del ticket, si es real a partir de un archivo
     * de imagen ademas si es que no esta bloqueado en nuestro sistema
     *
     * @param file imagen a analizar
     * @return informacion del ticket si es que existe, de lo contrario retorna datos nulos
     * @throws IOException excepcion relacionada con el manejo de el archivo
     */
    @Override
    public TicketDTO checkTicket(File file) throws IOException {
        String data = null;
        try {
            data = QrGeneration.decodeQR(file);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
        Optional<Ticket> ticketResponse = ticketRepository.checkTicket(data);
        if(ticketResponse.isPresent()){
            return modelMapper.map(ticketResponse, TicketDTO.class);
        }
        return null;
    }

    /**
     * Con este motodo moddificamos la imagen del ticket
     * generamos nueva infomacion aleatoria
     * a partir de una imagen valida
     * @param file Archivo idealmente valido ( de lo contrario retorna nulo)
     * @return informacion actualizada del ticket
     * @throws IOException excepcion relacionada con el manejo de imagenes
     */
    @Override
    public TicketDTO renewQr(File file) throws IOException {
        String data = null;
        try {
            data = QrGeneration.decodeQR(file);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
        Optional<Ticket> ticketResponse = ticketRepository.checkTicket(data);
        if(ticketResponse.isPresent()){
            Ticket newTicket = ticketResponse.get();
            //Generate new QR
            UUID uuid = UUID.randomUUID();
            String newChain = uuid.toString();
            try {
                QrGeneration.generateQr("NEW"+newChain+".png",newChain,200, 200);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (WriterException e) {
                throw new RuntimeException(e);
            }
            newTicket.setQr(newChain);

            ticketRepository.save(newTicket);
            return modelMapper.map(newTicket, TicketDTO.class);
        }
        return null;
    }

    /**
     * Metodo que busca y retorna una lista de tickets dados de baja
     * @return listado de tickets
     */
    @Override
    public List<TicketDTO> findDisabledTickets() {
        List<Ticket> tickets = ticketRepository.findDisabledTickets();
        return tickets.stream()
                .map(ticket -> modelMapper.map(ticket, TicketDTO.class))
                .toList();
    }

    /**
     * Metodo que busca y retorna una lista de tickets validos(alta)
     * @return listado de tickets
     */
    @Override
    public List<TicketDTO> findAllAvailableTickets() {
        List<Ticket> tickets = ticketRepository.findAllAvailableTickets();
        return tickets.stream()
                .map(ticket -> modelMapper.map(ticket, TicketDTO.class))
                .toList();
    }

    /**
     * Metodo que busca y retorna una lista de tickets bloqueados
     * @return listado de tickets
     */
    @Override
    public List<TicketDTO> findAllLockedTickets() {
        List<Ticket> tickets = ticketRepository.findAllLockedTickets();
        return tickets.stream()
                .map(ticket -> modelMapper.map(ticket, TicketDTO.class))
                .toList();
    }

    /**
     * MEtodo que retorna todos los tickets que pertenescan a un evento especifico
     * @param eventName cadona de caracteres(nombre del evento)
     * @return listado de tickets que pertenecen a este evento
     */
    @Override
    public List<TicketDTO> findTicketsByEvent(String eventName) {
        List<Ticket> tickets = ticketRepository.findTicketsByEvent(eventName);
        return tickets.stream()
                .map(ticket -> modelMapper.map(ticket, TicketDTO.class))
                .toList();
    }

}
