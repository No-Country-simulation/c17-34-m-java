package com.c174.models.user;

import com.c174.models.embed.Audit;
import com.c174.models.profile.ProfileResponse;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    @Column(unique = true)
    @Email
    private String email;
    private ProfileResponse profile;
    private Audit audit;
}
