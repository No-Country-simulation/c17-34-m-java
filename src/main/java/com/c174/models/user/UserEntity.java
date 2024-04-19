package com.c174.models.user;

import com.c174.models.embed.Audit;
import com.c174.models.mpuser.CredentialMPUser;
import com.c174.models.profile.ProfileEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @Email
    private String email;
    private String password;
    private Boolean isPresent;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProfileEntity profile;
    @Embedded
    private Audit audit = new Audit();
    @OneToOne(mappedBy = "userApp", cascade = CascadeType.ALL, orphanRemoval = true)
    private CredentialMPUser credentialMPUser;

    @PrePersist
    public void prePersist() {
        this.isPresent = Boolean.TRUE;
    }


}


