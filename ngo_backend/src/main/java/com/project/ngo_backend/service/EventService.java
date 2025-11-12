package com.project.ngo_backend.service;

import com.project.ngo_backend.entity.Event;
import com.project.ngo_backend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    
    @Autowired
    private EventRepository eventRepository;
    
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }
    
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }
    
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
    
    public List<Event> getUpcomingEvents() {
        return eventRepository.findByEventDateAfterOrderByEventDateAsc(LocalDateTime.now());
    }
    
    public List<Event> getPastEvents() {
        return eventRepository.findByEventDateBeforeOrderByEventDateDesc(LocalDateTime.now());
    }
    
    public List<Event> getEventsByStatus(String status) {
        return eventRepository.findByStatus(status);
    }
    
    public Long getUpcomingEventsCount() {
        Long count = eventRepository.getUpcomingEventsCount();
        return count != null ? count : 0L;
    }
}