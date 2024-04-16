package com.c174.repositorys;

import com.c174.models.mpuser.CredentialMPUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MpUserRepository extends JpaRepository<CredentialMPUser, UUID> {
    Optional<CredentialMPUser> findById(UUID id);
    Optional<CredentialMPUser> findByProfileId(Long profileId);
}
