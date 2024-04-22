package com.c174.repositorys;

import com.c174.models.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.isPresent = true")
    List<UserEntity> findAllAvailable();

    @Query("SELECT u FROM UserEntity u WHERE u.isPresent = true AND u.id = :id ")
    Optional<UserEntity> findByIdIfAvailable(@Param("id")Long id);

    @Query("SELECT u FROM UserEntity u WHERE u.email = :email")
    Optional<UserEntity> findByEmail(@Param("email")String email);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM UserEntity u JOIN u.owner p WHERE p.name = :name")
    Optional<UserEntity> findByUserName(@Param("name")String name);
}
