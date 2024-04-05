package com.c174.controllers;


import com.c174.dto.TicketDTO;
import com.c174.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;

    /**
     * Crea un ticket
     * @param ticketDTO informacion minima del ticket
     * @return estado de la respuesta
     */
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody TicketDTO ticketDTO){
        return new ResponseEntity<>(ticketService.create(ticketDTO),HttpStatus.OK);
    }

    /**
     * Modifica un registro
     * @param id primary key
     * @param ticketDTO informacion actualizada
     * @return estado de la respuesta
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody TicketDTO ticketDTO){
        return new ResponseEntity<>(ticketService.update(id,ticketDTO),HttpStatus.OK);
    }

    /**
     * da de baja a un ticket
     * @param id primary key
     * @return estado de la respuesta
     */
    @PutMapping("/disable/{id}")
    public ResponseEntity<?> disable(@PathVariable Long id){
        return new ResponseEntity<>(ticketService.disable(id),HttpStatus.OK);
    }

    /**
     * blockea un ticket
     * @param id primary key
     * @return estado de la respuesta
     */
    @PutMapping("/lock/{id}")
    public ResponseEntity<?> lockTicket(@PathVariable Long id){
        return new ResponseEntity<>(ticketService.lockTicket(id),HttpStatus.OK);
    }

    /**
     * check in de tickets
     * @param file imagen a validar
     * @return estado de la respuesta
     * @throws IOException excepcion relacionada a el manejo de archivos
     */
    @PostMapping("/checkTicket")
    public ResponseEntity<?> checkTicket(@RequestParam("file") MultipartFile file) throws IOException {
        String filePath = System.getProperty("user.dir") + File.separator + file.getOriginalFilename();

        File convFile = new File(filePath);
        file.transferTo(convFile);
        TicketDTO ticketDTO = ticketService.checkTicket(convFile);

        if(ticketDTO != null){
            Map<String, Object> response = new HashMap<>();
            response.put("status", "Valid ticket");
            response.put("ticket", ticketDTO);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid Ticket", HttpStatus.BAD_REQUEST);
    }

    /**
     * genera nueva informacion de una imagen valida
     * @param file imagen valida
     * @return estado de la respuesta
     * @throws IOException excepcion relacionada a el manejo de imagenes
     */
    @PostMapping("/renewQr")
    public ResponseEntity<?> renewQr(@RequestParam("file") MultipartFile file) throws IOException {
        String filePath = System.getProperty("user.dir") + File.separator + file.getOriginalFilename();

        File convFile = new File(filePath);
        file.transferTo(convFile);
        TicketDTO ticketDTO = ticketService.renewQr(convFile);

        if(ticketDTO != null){
            Map<String, Object> response = new HashMap<>();
            response.put("status", "Renewed qr");
            response.put("ticket", ticketDTO);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid Ticket", HttpStatus.BAD_REQUEST);
    }

    /**
     * retorna un listado de tickets dados de baja
     * @return estado de la respuesta
     */
    @GetMapping("/list/disabled")
    public ResponseEntity<?> findDisabledTickets(){
        List<TicketDTO> tickets = ticketService.findDisabledTickets();
        if (tickets.isEmpty()) return new ResponseEntity<>("NO tickets available", HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    /**
     * retorna un listado de tickets disponibles
     * @return estado de la respuesta
     */
    @GetMapping("/list/available")
    public ResponseEntity<?> findAllAvailableTickets(){
        List<TicketDTO> tickets = ticketService.findAllAvailableTickets();
        if (tickets.isEmpty()) return new ResponseEntity<>("NO tickets available", HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    /**
     * retorna un listado de tickets blockeado
     * @return estado de la respuesta
     */
    @GetMapping("/list/locked")
    public ResponseEntity<?> findAllLockedTickets(){
        List<TicketDTO> tickets = ticketService.findAllLockedTickets();
        if (tickets.isEmpty()) return new ResponseEntity<>("NO tickets available", HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    /**
     * retorna un listado de tickets relacionados con un evento
     * @param eventName cadena de caracteres (nombre del evento)
     * @return estado de la respuesta
     */
    @GetMapping("/findByEvent")
    public ResponseEntity<?> findTicketsByEvent(@RequestParam String eventName){
        List<TicketDTO> tickets = ticketService.findTicketsByEvent(eventName);
        if (tickets.isEmpty()) return new ResponseEntity<>("NO tickets available", HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

}
