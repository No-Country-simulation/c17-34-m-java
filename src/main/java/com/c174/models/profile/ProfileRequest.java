package com.c174.models.profile;

import com.c174.models.user.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequest {
    private Long id;
    private String name;
    private String lastname;
    private String document;
    private UserRequest user;
}
