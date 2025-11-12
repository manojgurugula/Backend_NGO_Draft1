package com.project.ngo_backend.repository;

import com.project.ngo_backend.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    
    List<Event> findByStatus(String status);
    
    List<Event> findByEventDateAfterOrderByEventDateAsc(LocalDateTime date);
    
    List<Event> findByEventDateBeforeOrderByEventDateDesc(LocalDateTime date);
    
    @Query("SELECT COUNT(e) FROM Event e WHERE e.status = 'upcoming'")
    Long getUpcomingEventsCount();
}