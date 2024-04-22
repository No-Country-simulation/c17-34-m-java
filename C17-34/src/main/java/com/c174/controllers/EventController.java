package com.c174.controllers;
import com.c174.exception.*;
import com.c174.models.event.EventRequest;
import com.c174.models.event.EventResponse;
import com.c174.services.abstraccion.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/event")
@Tag(name = "Event", description = "Event API")
public class EventController {
    @Autowired
    private EventService eventService;
    @Operation(summary = "Get all events and tickets")
    @GetMapping("/all")
    public ResponseEntity<?> getAll() throws EntityNotFoundException {
        Map<String, Object> bodyResponse = new HashMap<>();
        List<EventResponse> response = eventService.getAll();
        bodyResponse.put("data", response);
        bodyResponse.put("success", Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.OK).body(bodyResponse);
    }

    @Operation(summary = "Get event by name, if this include in the name return the event")
    @GetMapping("/name")
    public ResponseEntity<?> getByName( @RequestParam String name) throws EntityNotFoundException {
        Map<String, Object> bodyResponse = new HashMap<>();
        List<EventResponse> response = eventService.getByName(name);
        bodyResponse.put("data", response);
        bodyResponse.put("success", Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.OK).body(bodyResponse);
    }

    @Operation(summary = "Get event by id, if this include in the id return the event")
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById( @PathVariable Long id) throws EntityNotFoundException {
        Map<String, Object> bodyResponse = new HashMap<>();

        EventResponse response = eventService.getById(id);
        bodyResponse.put("data", response);
        bodyResponse.put("success", Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.OK).body(bodyResponse);
    }

    @Operation(summary = "Create a new event - only admin")
    @PostMapping("/create")
    public ResponseEntity<?> saveEvent( @RequestBody @Valid Optional<EventRequest> event) throws NoBodyException,  AlreadyExistsException, EntityExistsException {
        Map<String, Object> bodyResponse = new HashMap<>();
        if( event == null || event.isEmpty() ){
            throw new NoBodyException("No se recibio ningun dato");
        }
        EventResponse response = eventService.save(event.get());
        bodyResponse.put("data", response);
        bodyResponse.put("success", Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.CREATED).body(bodyResponse);
    }

    @Operation(summary = "Delete a event - only admin")
    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) throws EntityDeleteException, EntityNotFoundException {
        Map<String, Object> bodyResponse = new HashMap<>();
        String response = eventService.delete(id);
        bodyResponse.put("data", response);
        bodyResponse.put("success", Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.OK).body(bodyResponse);
    }

    @Operation(summary = "Update a event - only admin")
    @PatchMapping("/id/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Long id, @RequestBody @Valid Optional<EventRequest> event) throws EntityUploadException, EntityNotFoundException, NoBodyException {
        Map<String, Object> bodyResponse = new HashMap<>();
        if( event == null || event.isEmpty() ){
            throw new NoBodyException("No se recibio ningun dato");
        }
        eventService.update(id, event.get());
        bodyResponse.put("data", "Entity update");
        bodyResponse.put("success", Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.OK).body(bodyResponse);
    }

}
