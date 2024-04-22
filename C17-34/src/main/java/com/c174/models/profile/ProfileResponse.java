package com.c174.models.profile;

import com.c174.models.ticket.TicketEntity;
import com.c174.models.ticket.TicketResponse;
import com.c174.models.user.UserResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {
    private Long id;
    private String lastname;
    private String name;
    private String document;
    private UserResponse user;
    private List<TicketResponse> tickets;

}
