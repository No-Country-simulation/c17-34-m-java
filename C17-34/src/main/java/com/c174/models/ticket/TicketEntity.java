package com.c174.models.ticket;

import com.c174.models.event.EventEntity;
import com.c174.models.embed.Audit;
import com.c174.models.transaction.TransactionEntity;
import com.c174.models.profile.ProfileEntity;
import jakarta.persistence.*;
import lombok.Data;



@Entity
@Table(name="tickets")
@Data
public class TicketEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String meta;
    @ManyToOne(
            cascade = {CascadeType.MERGE}
    )
    @JoinColumn(name = "event_id")
    private EventEntity event;
    @Embedded
    private Audit audit;
    @ManyToOne(
            cascade = {CascadeType.MERGE}
    )
    @JoinColumn(name = "transaction_id")
    private TransactionEntity transaction;
    @ManyToOne(
            cascade = {CascadeType.MERGE}
    )
    @JoinColumn(name = "profile_id")
    private ProfileEntity owner;
    private Boolean isPresent;
    private Boolean isLock;

    private Double price;

    public TicketEntity(TicketEnterpriceDto ticket){
        this.isPresent = ticket.getIsPresent();
        this.isLock = ticket.getIsLocked();
        this.price = ticket.getPrice();
        this.meta = ticket.getQr();
    }

    @PrePersist
    public void prePersist() {
        this.isPresent = Boolean.TRUE;
    }
}
