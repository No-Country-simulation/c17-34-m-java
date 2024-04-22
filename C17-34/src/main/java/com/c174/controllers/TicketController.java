package com.c174.controllers;

import com.c174.models.ticket.TicketEnterpriceDto;
import com.c174.models.ticket.TicketResponse;
import com.c174.services.abstraccion.TicketService;
import com.c174.services.implementation.EnterpriseConsumeServiceImp;
import com.c174.exception.EntityNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
@Tag(name = "Ticket", description = "Ticket API")
public class TicketController {

    private final TicketService ticketServiceImp;
    private final EnterpriseConsumeServiceImp enterpriseConsumeServiceImp;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody File file) throws EntityNotFoundException {
        TicketEnterpriceDto ticketResponseEnterprice = enterpriseConsumeServiceImp.checkTicket(file);
        if (ticketResponseEnterprice.getIsLocked()){
            return new ResponseEntity<>("Ticket is already on service", HttpStatus.NOT_ACCEPTABLE);
        }


        TicketResponse ticketResponse = ticketServiceImp.create(ticketResponseEnterprice);
        return new ResponseEntity<>(ticketResponse,HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listTickets(){
        List<TicketResponse> tickets = ticketServiceImp.listTickets();
        if (tickets.isEmpty()) return new ResponseEntity<>(tickets, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(tickets,HttpStatus.OK);
    }

    @GetMapping("/checkTicket")
    public ResponseEntity<?> checkTicket(@RequestParam File file){
        TicketResponse ticketResponse = ticketServiceImp.checkTicket(file);
        return new ResponseEntity<>(ticketResponse, HttpStatus.OK);
    }

    @PostMapping("/renew")
    public ResponseEntity<?> renewQr(@RequestParam File file){
        TicketResponse ticketResponse = ticketServiceImp.renewQr(file);
        return  new ResponseEntity<>(ticketResponse,HttpStatus.OK);
    }

    @PutMapping("/lockTicket/{id}")
    public ResponseEntity<?> lockTicket(@PathVariable Long id){
        TicketResponse ticketResponse = ticketServiceImp.lockTicket(id);
        return new ResponseEntity<>(ticketResponse,HttpStatus.LOCKED);
    }

    @Operation(summary = "Get all Tickets")
    @GetMapping("/all")
    public ResponseEntity<?> getAll() throws EntityNotFoundException {
        Map<String, Object> bodyResponse = new HashMap<>();
        List<TicketResponse> response = ticketServiceImp.getAll();
        bodyResponse.put("data", response);
        bodyResponse.put("success", Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.OK).body(bodyResponse);
    }

    @Operation(summary = "Get ticket by PROFILE_ID, if this include in the PROFILE return the ticket")
    @GetMapping("/profile/{id}")
    public ResponseEntity<?> getByProfile( @PathVariable Long id){
        Map<String, Object> bodyResponse = new HashMap<>();
        try{
            List<TicketResponse> response = ticketServiceImp.getTicketByProfile(id);
            bodyResponse.put("data", response);
            bodyResponse.put("success", Boolean.TRUE);
            return ResponseEntity.status(HttpStatus.OK).body(bodyResponse);
        } catch (Exception e) {
            bodyResponse.put("data", e.getMessage());
            bodyResponse.put("success", Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse);
        }
    }

    @Operation(summary = "Get ticket by EVENT_NAME, if this include in the EVENT return the ticket")
    @GetMapping("/event")
    public ResponseEntity<?> getByEvent( @RequestParam String name){
        Map<String, Object> bodyResponse = new HashMap<>();
        try{
            List<TicketResponse> response = ticketServiceImp.getTicketByEvent(name);
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
            List<TicketResponse> response = ticketServiceImp.getTicketByEvent(id);
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
        TicketResponse response = ticketServiceImp.getById(id);
        bodyResponse.put("data", response);
        bodyResponse.put("success", Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.OK).body(bodyResponse);
    }


}
