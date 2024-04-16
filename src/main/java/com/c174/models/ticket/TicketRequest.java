package com.c174.models.ticket;

import com.c174.models.embed.Audit;
import com.c174.models.event.EventRequest;
import com.c174.models.profile.ProfileEntity;
import com.c174.models.profile.ProfileRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
