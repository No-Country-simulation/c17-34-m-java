package com.c174.models.transaction;

import com.c174.models.ticket.TicketResponse;
import com.c174.models.profile.ProfileResponse;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TransactionResponse {
    private Long id;
    private ProfileResponse profileSeller;
//    private List<TicketResponse> ticket;
    private ProfileResponse profileBuyer;

    public TransactionResponse(Long id, ProfileResponse profileSeller, List<TicketResponse> tickets, ProfileResponse profileBuyer) {
        this.id = id;
        this.profileSeller = profileSeller;
//        this.ticket = tickets;
        this.profileBuyer = profileBuyer;
    }

    public TransactionResponse(TransactionEntity transaction) {
        this.id = transaction.getId();

    }
}
