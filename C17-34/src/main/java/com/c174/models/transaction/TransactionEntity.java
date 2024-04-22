package com.c174.models.transaction;

import com.c174.models.embed.Audit;
import com.c174.models.enumerators.StateTransaction;
import com.c174.models.ticket.TicketEntity;
import com.c174.models.profile.ProfileEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="transactions")
@Data
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ProfileEntity profileSeller;
    @OneToMany(
            mappedBy = "transaction",
            fetch = FetchType.LAZY
    )
    private List<TicketEntity> tickets;
    @ManyToOne
    private ProfileEntity profileBuyer;

    @Enumerated(EnumType.STRING)
    private StateTransaction stateTransaction;
    @Embedded
    private Audit audit = new Audit();
    private Boolean isPresent;
}
