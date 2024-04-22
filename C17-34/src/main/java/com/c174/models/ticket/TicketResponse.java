package com.c174.models.ticket;

import lombok.Builder;
import com.c174.models.embed.Audit;
import com.c174.models.event.EventResponse;
import com.c174.models.profile.ProfileResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {
    private Long id;
    private String meta;
    private EventResponse event;
    private Audit audit;
    private ProfileResponse owner;
    private Double price;

}
