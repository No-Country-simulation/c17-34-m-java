package com.c174.models.event;

import com.c174.models.embed.AuditResponse;
import com.c174.models.ticket.TicketEntity;
import com.c174.models.ticket.TicketResponse;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jdk.jfr.Event;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventResponse {
    @Positive
    private Long id;
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "El nombre no puede contener caracteres especiales")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 1 y 50 caracteres")
    private String name;
    private String address;
    private Date dateStart;
    private Date dateEnd;
    private AuditResponse audit;
    private List<TicketResponse> tickets;


}
