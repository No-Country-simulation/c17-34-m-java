package com.c174.repositorys;

import com.c174.models.event.EventEntity;
import com.c174.models.event.EventRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
    @Query("SELECT e FROM EventEntity e WHERE e.isPresent = true")
    List<EventEntity> findAllPresent();
    @Query("SELECT e FROM EventEntity e WHERE LOWER (e.name) LIKE  %:searchTerm% AND e.isPresent = true")
    Optional<List<EventEntity>> findByNameIgnoreCaseContaining(@Param("searchTerm")String name);

    @Query("SELECT e FROM EventEntity e WHERE e.id=?1 and e.isPresent = true")
    Optional<EventEntity> findById(Long id);
    @Query("SELECT COUNT(e) >0 FROM EventEntity e WHERE LOWER (e.name) LIKE  %:searchTerm% AND e.isPresent = true")
    boolean existsByName(@Param("searchTerm")String name);
    @Transactional
    @Modifying
    @Query("UPDATE  EventEntity e SET e.name = ?2 where e.id = ?1 ")
    void updateEventName(Long id, String newName);

    @Transactional
    @Modifying
    @Query("UPDATE  EventEntity e SET e.isPresent = ?2 where e.id = ?1")
    void updateIsPresent(Long id, Boolean isPresent);
}
