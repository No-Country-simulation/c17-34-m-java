package com.c174.models.ticket;

import com.c174.exception.EntityNotFoundException;
import com.c174.models.embed.Audit;
import com.c174.models.event.EventRequest;
import com.c174.models.profile.ProfileRequest;
import com.c174.services.abstraccion.EventService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZoneId;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequest {
    private Long id;
    private String meta;
    private EventRequest event;
    private Audit audit;
    private ProfileRequest owner;

    private Double price;

}
