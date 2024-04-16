package com.c174.services.abstraccion;

import com.c174.exception.*;
import com.c174.models.profile.ProfileRequest;
import com.c174.models.profile.ProfileResponse;
import com.c174.models.ticket.TicketRequest;
import com.c174.models.ticket.TicketResponse;

import java.util.List;
import java.util.UUID;

public interface ProfileService {

    List<ProfileResponse> getAll() throws EntityNotFoundException;
    ProfileResponse getProfileById(Long id) throws EntityNotFoundException;
    ProfileResponse updateProfile(ProfileRequest profile) throws EntityNotFoundException, EntityUploadException;
    String deleteProfile(Long id) throws EntityDeleteException, EntityNotFoundException;
    TicketResponse createTicket  (Long profileId, TicketRequest ticketRequest) throws EntityNotFoundException, AlreadyExistsException, EntityExistsException;

    String getUrlAuthMP(Long id) throws EntityNotFoundException;
}
