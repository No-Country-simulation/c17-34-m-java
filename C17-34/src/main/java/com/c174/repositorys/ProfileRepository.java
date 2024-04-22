package com.c174.repositorys;

import com.c174.models.profile.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE  ProfileEntity e SET e.isPresent = ?2 where e.id = ?1")
    void updateIsPresent(Long id, Boolean isPresent);

    @Transactional
    @Modifying
    @Query("UPDATE ProfileEntity e SET e.lastname = :lastname," +
            " e.document = :document," +
            " e.name = :name")
    Optional<ProfileEntity> updateProfile(@Param("lastname")String lastname,
                                          @Param("document")String document,
                                          @Param("name")String name);
    @Query("SELECT e FROM ProfileEntity e WHERE e.user.id = ?1")
    ProfileEntity findByUserId(Long userId);

    @Query("SELECT p FROM ProfileEntity p WHERE p.user = :id AND p.isPresent =true")
    Optional<ProfileEntity> findByUserIfIsAvailable(@Param("id")Long id);
}
