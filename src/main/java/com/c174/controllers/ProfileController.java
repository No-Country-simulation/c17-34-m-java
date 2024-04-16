package com.c174.controllers;

import com.c174.exception.*;
import com.c174.models.mpuser.RequestToken;
import com.c174.models.profile.ProfileRequest;
import com.c174.models.profile.ProfileResponse;
import com.c174.models.ticket.TicketRequest;
import com.c174.models.ticket.TicketResponse;
import com.c174.services.abstraccion.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/profile")
@CrossOrigin("*") //TODO modificar por el dominio correspondiente

@Tag(name = "Profile", description = "Profile API")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @Operation(summary = "Get all profiles")
    @GetMapping("/all")
    public ResponseEntity<?> getAllProfile() throws EntityNotFoundException {
        Map<String, Object> bodyResponse = new HashMap<>();
        List<ProfileResponse> response = profileService.getAll();
        bodyResponse.put("data", response);
        bodyResponse.put("success", Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.OK).body(bodyResponse);
    }
    @Operation(summary = "Get profile by id")
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable Long id) throws EntityNotFoundException {
        Map<String, Object> bodyResponse = new HashMap<>();

        ProfileResponse response = profileService.getProfileById(id);
        bodyResponse.put("data", response);
        bodyResponse.put("success", Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.OK).body(bodyResponse);
    }

    @Operation(summary = "Update profile")
    @PatchMapping("/update")
    public ResponseEntity<?> updateProfile(@RequestBody @Valid Optional<ProfileRequest> profile) throws EntityUploadException, EntityNotFoundException, NoBodyException {
        if( profile == null || profile.isEmpty() ){
            throw new NoBodyException("No se recibio ningun dato");
        }
        return ResponseEntity.ok(profileService.updateProfile(profile.get()));
    }

    @Operation(summary = "Delete profile")
    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable Long id) throws EntityDeleteException, EntityNotFoundException {
        Map<String, Object> bodyResponse = new HashMap<>();
        String response = profileService.deleteProfile(id);
        bodyResponse.put("data", response);
        bodyResponse.put("success", Boolean.TRUE);

        return ResponseEntity.status(HttpStatus.OK).body(bodyResponse);
    }

    @Operation(summary = "Create tickets")
    @PostMapping("/{id}/ticket")
    public ResponseEntity<?> createTicket(@PathVariable Long id, @RequestBody @Valid Optional<TicketRequest> ticket) throws EntityNotFoundException, NoBodyException, AlreadyExistsException, EntityExistsException {
        if( ticket == null || ticket.isEmpty() ){
            throw new NoBodyException("No se recibio ningun dato");
        }
        Map<String, Object> bodyResponse = new HashMap<>();
        TicketResponse response = profileService.createTicket(id, ticket.get());
        bodyResponse.put("new_ticket", response);
        //TODO: CAMBIAR donde corresponda la accion para pedir acceso a mp
        bodyResponse.put("mp_url",profileService.getUrlAuthMP(id));  // URL que da permisos, el id es del profile, corresponde genera un id aleatorio por cada peticion en realidad
        bodyResponse.put("success", Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.OK).body(bodyResponse);
    }

    //TODO: Implementar los métodos restantes
    // UPDATE TICKET
    // DELETE TICKET
}
