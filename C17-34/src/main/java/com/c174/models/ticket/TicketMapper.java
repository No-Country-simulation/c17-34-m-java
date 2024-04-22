package com.c174.models.ticket;

import com.c174.models.embed.AuditMapper;
import com.c174.models.event.EventMapper;
import com.c174.models.profile.ProfileMapper;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring", uses = {AuditMapper.class, ProfileMapper.class, EventMapper.class})
public interface TicketMapper {
    //To TicketEntity: Sin owner, event sin tickets
    @Named("toTicketEntity")
    @Mappings({
            @Mapping(target = "owner", ignore = true),
            @Mapping(target = "event", qualifiedByName = "toEventEntity")
    })
    TicketEntity toTicketEntity(TicketRequest ticketRequest);

    // To TicketResponse: ALL atributos
    @Named("toTicketResponse")
    @Mappings(
            {@Mapping(target = "event", qualifiedByName = "toEventResponseSinTickets"),
            @Mapping(target = "owner", qualifiedByName = "toProfileResponseSinUserSinTickets")}
    )
    // To TicketResponse: ALL atributos
    // el atributo EVENT y OWNER(profile) tiene que mapear sin tickets
    TicketResponse toTicketResponse(TicketEntity ticket);

    @Named("toTicketResponseSinOwnerAndEventSinTickets")
    @Mappings(
            {@Mapping(target = "owner", ignore = true),
            @Mapping(target = "event", qualifiedByName = "toEventResponseSinTickets")}
    )
    TicketResponse toTicketResponseSinOwnerAndEventSinTickets(TicketEntity ticket);

    @Named("toTicketResponseSinEventAndProfileSinTicketAndUserSinProfile")
    @Mappings(
            {@Mapping(target = "event", ignore = true),
            @Mapping(target = "owner", qualifiedByName = "toProfileResponseSinTicketAndUserSinProfile")}
    )
    TicketResponse toTicketResponseSinEventAndProfileSinTicketAndUserSinProfile(TicketEntity ticket);

    @Named("toTicketResponseSinEventAndSinProfile")
    @Mappings(
            {@Mapping(target = "event", ignore = true),
            @Mapping(target = "owner", ignore = true)}
    )
    TicketResponse toTicketResponseSinEventAndSinProfile(TicketEntity ticket);

    @Named("toTicketResponseConEventSinTicketConAndProfileConUserSinProfileAndTicketSinEventSinProfile")
    @Mappings(
            {@Mapping(target = "event", qualifiedByName = "toEventResponseSinTickets"),
            @Mapping(target = "owner", qualifiedByName = "toProfileResponseConUserSinOwnerAndTicketSinEventSinProfile")}
    )
    TicketResponse toTicketResponseConEventSinTicketConAndProfileConUserSinProfileAndTicketSinEventSinProfile(TicketEntity ticket);



    // -------------- Lists
    @Named("toListTicketResponse")
    @IterableMapping(qualifiedByName = "toTicketResponse")
    List<TicketResponse> toListTicketResponse(List<TicketEntity> tickets);

    @Named("toListTicketResponseSinOwnerAndEventSinTickets")
    @IterableMapping(qualifiedByName = "toTicketResponseSinOwnerAndEventSinTickets")
    List<TicketResponse> toListTicketResponseSinOwnerAndEventSinTickets(List<TicketEntity> tickets);

    @Named("toListTicketResponseSinEventAndProfileSinTicketAndUserSinProfile")
    @IterableMapping(qualifiedByName = "toTicketResponseSinEventAndProfileSinTicketAndUserSinProfile")
    List<TicketResponse> toListTicketResponseSinEventAndProfileSinTicketAndUserSinProfile(List<TicketEntity> tickets);

    @Named("toListTicketResponseSinEventAndSinProfile")
    @IterableMapping(qualifiedByName = "toTicketResponseSinEventAndSinProfile")
    List<TicketResponse> toListTicketResponseSinEventAndSinProfile(List<TicketEntity> tickets);

    @Named("toListTicketResponseConEventSinTicketConAndProfileConUserSinProfileAndTicketSinEventSinProfile")
    @IterableMapping(qualifiedByName = "toTicketResponseConEventSinTicketConAndProfileConUserSinProfileAndTicketSinEventSinProfile")
    List<TicketResponse> toListTicketResponseConEventSinTicketConAndProfileConUserSinProfileAndTicketSinEventSinProfile(List<TicketEntity> tickets);
}
