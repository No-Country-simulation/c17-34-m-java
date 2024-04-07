package com.c174.repositorys;

import com.c174.models.profile.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE  ProfileEntity e SET e.isPresent = ?2 where e.id = ?1")
    void updateIsPresent(Long id, Boolean isPresent);

}
