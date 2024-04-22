package com.c174.models.user;

import com.c174.models.profile.ProfileMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProfileMapper.class})
public interface UserMapper {
    // ToUserResponse: Todos los atributos
    // El atributo Profile tiene que mapear sin user
    @Named("toUserResponse")
    @Mappings({
            @Mapping(target = "profile", qualifiedByName = "toProfileResponseSinUserConTicketsSinOwnerAndEventSinTickets"),
            @Mapping(target="email", source="email"),
    })
    UserResponse toUserResponse(UserEntity user);

    @Named("toUserResponseSinProfile")
    @Mappings({
            @Mapping(target = "profile", ignore = true),
            @Mapping(target="email", source="email"),
    })
    UserResponse toUserResponseSinProfile(UserEntity user);

    // LISTAS
    @Named("toListUserResponseSinProfile")
    @IterableMapping(qualifiedByName = "toUserResponseSinProfile")
    List<UserResponse> toListUserResponseSinProfile(List<UserEntity> users);

    @Named("toListUserResponse")
    @IterableMapping(qualifiedByName = "toUserResponse")
    List<UserResponse> toListUserResponse(List<UserEntity> users);
}
