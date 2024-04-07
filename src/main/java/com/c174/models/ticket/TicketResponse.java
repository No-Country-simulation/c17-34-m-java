package com.c174.models.ticket;

import com.c174.models.embed.Audit;
import com.c174.models.event.EventResponse;
import com.c174.models.profile.ProfileEntity;
import com.c174.models.profile.ProfileResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {
    private Long id;
    private String meta;
    private EventResponse event;
    private Audit audit;
    private ProfileResponse owner;
}
