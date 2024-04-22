package com.c174.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "qr")
    private String qr;
    @Column(name = "is_present")
    private Boolean isPresent;
    @Column(name = "is_locked")
    private Boolean isLocked;
    @Column(name = "event_name")
    private String eventName;
    @Column(name = "price")
    private Double price;
    @Column(name = "start_event_date")
    private LocalDateTime startEvent;
    @Column(name = "end_event_date")
    private LocalDateTime endEvent;
    @Column(name = "creation_Date")
    private LocalDateTime creationDate;
    @Column(name = "event_date")
    private LocalDateTime eventDate;
}
