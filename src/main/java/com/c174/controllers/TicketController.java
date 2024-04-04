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
@RequestMapping("/api/v1/ticket")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody TicketDTO ticketDTO){
        return new ResponseEntity<>(ticketService.create(ticketDTO),HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody TicketDTO ticketDTO){
        return new ResponseEntity<>(ticketService.update(id,ticketDTO),HttpStatus.OK);
    }
    @PutMapping("/disable/{id}")
    public ResponseEntity<?> disable(@PathVariable Long id){
        return new ResponseEntity<>(ticketService.disable(id),HttpStatus.OK);
    }
    @PutMapping("/lock/{id}")
    public ResponseEntity<?> lockTicket(@PathVariable Long id){
        return new ResponseEntity<>(ticketService.lockTicket(id),HttpStatus.OK);
    }
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

    @GetMapping("/list/disabled")
    public ResponseEntity<?> findDisabledTickets(){
        List<TicketDTO> tickets = ticketService.findDisabledTickets();
        if (tickets.isEmpty()) return new ResponseEntity<>("NO tickets available", HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }
    @GetMapping("/list/available")
    public ResponseEntity<?> findAllAvailableTickets(){
        List<TicketDTO> tickets = ticketService.findAllAvailableTickets();
        if (tickets.isEmpty()) return new ResponseEntity<>("NO tickets available", HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }
    @GetMapping("/list/locked")
    public ResponseEntity<?> findAllLockedTickets(){
        List<TicketDTO> tickets = ticketService.findAllLockedTickets();
        if (tickets.isEmpty()) return new ResponseEntity<>("NO tickets available", HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }
    @GetMapping("/findByEvent")
    public ResponseEntity<?> findTicketsByEvent(@RequestParam String eventName){
        List<TicketDTO> tickets = ticketService.findTicketsByEvent(eventName);
        if (tickets.isEmpty()) return new ResponseEntity<>("NO tickets available", HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

}
