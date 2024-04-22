package com.c174.models.ticket;


import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketEnterpriceDto {
    private Long id;
    private String qr;
    private Double price;
    private Boolean isPresent;
    private Boolean isLocked;
    private String eventName;
    private LocalDateTime creationDate;
    private LocalDateTime eventDate;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;

}