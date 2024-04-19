package com.c174.models.mpuser;

import com.c174.models.profile.ProfileEntity;
import com.c174.models.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "credential_mp_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CredentialMPUser {
    @Id
    UUID id;
    @OneToOne(fetch = FetchType.LAZY)
    UserEntity userApp;
    String access_token;
    String public_key;
    String refresh_token;
    Boolean live_mode;
    String user_id;
    String token_type;
    Long expires_in;
    String scope;
}
