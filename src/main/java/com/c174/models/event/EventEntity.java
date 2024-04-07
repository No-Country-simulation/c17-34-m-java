package com.c174.models.event;

import com.c174.models.embed.Audit;
import com.c174.models.ticket.TicketEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name="events")
@Data
@NoArgsConstructor
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(
            mappedBy = "event",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    private List<TicketEntity> tickets;
    @Embedded
    private Audit audit = new Audit();

    private Boolean isPresent;

    public EventEntity(String name) {
        this.name = name;
    }

    public EventEntity(EventRequest eventRequest) {
        this.name = eventRequest.getName();
    }

    @PrePersist
    public void prePersist() {
        this.isPresent = Boolean.TRUE;
    }
}
