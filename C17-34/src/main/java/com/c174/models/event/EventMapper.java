package com.c174.models.event;

import com.c174.models.embed.AuditMapper;
import com.c174.models.ticket.TicketMapper;
import org.mapstruct.*;


import java.util.List;

@Mapper(componentModel = "spring", uses={AuditMapper.class, TicketMapper.class})
public interface EventMapper {

    // To EventEntity: ALL atributos
    @Named("toEventEntity")
    @Mapping(target = "tickets", ignore = true)
    EventEntity toEventEntity(EventRequest eventRequest);

    @Named("toEventResponse")
    // To EventResponse: ALL atributos, pero los TicketResponse ignoran los eventos
    @Mappings({
            @Mapping(target = "tickets", qualifiedByName = "toListTicketResponseSinEventAndProfileSinTicketAndUserSinProfile")
    })
    EventResponse toEventResponse(EventEntity event);

    @Named("toEventResponseSinTickets")
    @Mapping(target = "tickets", ignore = true)
    EventResponse toEventResponseSinTickets(EventEntity event);

    // ------------------- List

    @Named("toListEventResponse")
    @IterableMapping(qualifiedByName = "toEventResponse")
    List<EventResponse> toListEventResponse(List<EventEntity> events);

    @Named("toListEventResponseSinTickets")
    @IterableMapping(qualifiedByName = "toEventResponseSinTickets")
    List<EventResponse> toListEventResponseSinTickets(List<EventEntity> events);



}
