package com.c174.dto;
import com.c174.models.Ticket;
import jakarta.persistence.Column;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    private Long id;
    private String qr;
    private Boolean isPresent;
    private Boolean isLocked;
    private String eventName;
    private LocalDateTime creationDate;
    private LocalDateTime eventDate;

}
