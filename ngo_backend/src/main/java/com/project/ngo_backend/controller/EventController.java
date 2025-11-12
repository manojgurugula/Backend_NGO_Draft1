package com.project.ngo_backend.controller;

import com.project.ngo_backend.entity.Event;
import com.project.ngo_backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {
    
    @Autowired
    private EventService eventService;
    
    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Optional<Event> event = eventService.getEventById(id);
        return event.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.saveEvent(event);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        Optional<Event> existingEvent = eventService.getEventById(id);
        if (existingEvent.isPresent()) {
            event.setId(id);
            return ResponseEntity.ok(eventService.saveEvent(event));
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/upcoming")
    public List<Event> getUpcomingEvents() {
        return eventService.getUpcomingEvents();
    }
    
    @GetMapping("/past")
    public List<Event> getPastEvents() {
        return eventService.getPastEvents();
    }
    
    @GetMapping("/status/{status}")
    public List<Event> getEventsByStatus(@PathVariable String status) {
        return eventService.getEventsByStatus(status);
    }
    
    @GetMapping("/stats")
    public Map<String, Object> getEventStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("upcomingEvents", eventService.getUpcomingEventsCount());
        return stats;
    }
}