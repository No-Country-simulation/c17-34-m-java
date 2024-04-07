package com.c174.models.profile;

import com.c174.models.embed.AuditMapper;
import com.c174.models.ticket.TicketMapper;
import com.c174.models.user.UserMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses={AuditMapper.class, TicketMapper.class, UserMapper.class})
public interface ProfileMapper {

    @Named("toProfileResponse")
    @Mappings({
            @Mapping(target = "user", qualifiedByName = "toUserResponseSinProfile"),
            @Mapping(target = "tickets", qualifiedByName = "toTicketResponseSinOwnerAndEventSinTickets")}
    )
    ProfileResponse toProfileResponse(ProfileEntity profile);

    @Named("toProfileResponseSinUserSinTickets")
    @Mappings(
            {@Mapping(target = "user", ignore = true), @Mapping(target = "tickets", ignore = true)}
    )
    ProfileResponse toProfileResponseSinUserSinTickets(ProfileEntity profile);

    @Named("toProfileResponseSinTicketAndUserSinProfile")
    @Mappings({
            @Mapping(target = "user", qualifiedByName = "toUserResponseSinProfile"),
            @Mapping(target = "tickets", ignore = true)}
    )
    ProfileResponse toProfileResponseSinTicketAndUserSinProfile(ProfileEntity profile);

    @Named("toProfileResponseSinUserConTicketsSinOwnerAndEventSinTickets")
    @Mappings(
            {@Mapping(target = "user", ignore = true),
            @Mapping(target = "tickets", qualifiedByName = "toTicketResponseSinOwnerAndEventSinTickets")}
    )
    ProfileResponse toProfileResponseSinUserConTicketsSinOwnerAndEventSinTickets(ProfileEntity profiles);

    @Named("toProfileResponseConUserSinOwnerAndTicketSinEventSinProfile")
    @Mappings(
            {@Mapping(target = "user", qualifiedByName = "toUserResponseSinProfile"),
            @Mapping(target = "tickets", qualifiedByName = "toTicketResponseSinEventAndSinProfile")}
    )
    ProfileResponse toProfileResponseConUserSinOwnerAndTicketSinEventSinProfile(ProfileEntity profile);

    // -------------- Lists
    @Named("toListProfileResponse")
    @IterableMapping(qualifiedByName = "toProfileResponse")
    List<ProfileResponse> toListProfileResponse(List<ProfileEntity> profiles);

    @Named("toListProfileResponseSinUser")
    @IterableMapping(qualifiedByName = "toProfileResponseSinUserConTicketsSinOwnerAndEventSinTickets")
    List<ProfileResponse> toListProfileResponseSinUser(List<ProfileEntity> profiles);

    @Named("toListProfileResponseSinTicketAndUserSinProfile")
    @IterableMapping(qualifiedByName = "toProfileResponseSinTicketAndUserSinProfile")
    List<ProfileResponse> toListProfileResponseSinTicketAndUserSinProfile(List<ProfileEntity> profiles);

    @Named("toListProfileResponseConUserSinOwnerAndTicketSinEventSinProfile")
    @IterableMapping(qualifiedByName = "toProfileResponseConUserSinOwnerAndTicketSinEventSinProfile")
    List<ProfileResponse> toListProfileResponseConUserSinOwnerAndTicketSinEventSinProfile(List<ProfileEntity> profiles);

    @Named("toListProfileResponseSinUserSinTickets")
    @IterableMapping(qualifiedByName = "toProfileResponseSinUserSinTickets")
    List<ProfileResponse> toListProfileResponseSinUserSinTickets(List<ProfileEntity> profiles);

    @Named("toListProfileResponseSinUserConTicketsSinOwnerAndEventSinTickets")
    @IterableMapping(qualifiedByName = "toProfileResponseSinUserConTicketsSinOwnerAndEventSinTickets")
    List<ProfileResponse> toListProfileResponseSinUserConTicketsSinOwnerAndEventSinTickets(List<ProfileEntity> profiles);

}



