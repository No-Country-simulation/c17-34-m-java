package com.c174.controllers;

import com.c174.exception.EntityNotFoundException;
import com.c174.models.event.EventResponse;
import com.c174.models.ticket.TicketResponse;
import com.c174.services.abstraccion.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ticket")
@Tag(name = "Ticket", description = "Ticket API")
public class TicketController {
    private final TicketService ticketService;
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Operation(summary = "Get all Tickets")
    @GetMapping("/all")
    public ResponseEntity<?> getAll() throws EntityNotFoundException {
        Map<String, Object> bodyResponse = new HashMap<>();
        List<TicketResponse> response = ticketService.getAll();
        bodyResponse.put("data", response);
        bodyResponse.put("success", Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.OK).body(bodyResponse);
    }

    @Operation(summary = "Get ticket by PROFILE_ID, if this include in the PROFILE return the ticket")
    @GetMapping("/profile/{id}")
    public ResponseEntity<?> getByProfile( @PathVariable Long id){
        Map<String, Object> bodyResponse = new HashMap<>();
        try{
            List<TicketResponse> response = ticketService.getTicketByProfile(id);
            bodyResponse.put("data", response);
            bodyResponse.put("success", Boolean.TRUE);
            return ResponseEntity.status(HttpStatus.OK).body(bodyResponse);
        } catch (Exception e) {
            bodyResponse.put("data", e.getMessage());
            bodyResponse.put("success", Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse);
        }
    }

    @Operation(summary = "Get ticket by EVENT_ID, if this include in the EVENT return the ticket")
    @GetMapping("/event/{id}")
    public ResponseEntity<?> getByEvent( @PathVariable Long id){
        Map<String, Object> bodyResponse = new HashMap<>();
        try{
            List<TicketResponse> response = ticketService.getTicketByEvent(id);
            bodyResponse.put("data", response);
            bodyResponse.put("success", Boolean.TRUE);
            return ResponseEntity.status(HttpStatus.OK).body(bodyResponse);
        } catch (Exception e) {
            bodyResponse.put("data", e.getMessage());
            bodyResponse.put("success", Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse);
        }
    }

    @Operation(summary = "Get ticket by ID")
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) throws EntityNotFoundException {
        Map<String, Object> bodyResponse = new HashMap<>();
        TicketResponse response = ticketService.getById(id);
        bodyResponse.put("data", response);
        bodyResponse.put("success", Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.OK).body(bodyResponse);
    }



    //TODO: Implementar los métodos restantes
    // UPDATE TICKET
    // DELETE TICKET
    // CREATE TICKET

}
