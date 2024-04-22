package com.c174.models.user;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Builder
public class AuthDto {

    private String token;
    private UserResponse user;
}
