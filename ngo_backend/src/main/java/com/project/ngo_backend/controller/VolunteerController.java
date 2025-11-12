package com.project.ngo_backend.controller;

import com.project.ngo_backend.entity.Volunteer;
import com.project.ngo_backend.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/volunteers")
@CrossOrigin(origins = "http://localhost:3000")
public class VolunteerController {
    
    @Autowired
    private VolunteerService volunteerService;
    
    @GetMapping
    public List<Volunteer> getAllVolunteers() {
        return volunteerService.getAllVolunteers();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Volunteer> getVolunteerById(@PathVariable Long id) {
        Optional<Volunteer> volunteer = volunteerService.getVolunteerById(id);
        return volunteer.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Object> createVolunteer(@RequestBody Volunteer volunteer) {
        if (volunteerService.emailExists(volunteer.getEmail())) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Email already exists");
            return ResponseEntity.badRequest().body(error);
        }
        return ResponseEntity.ok(volunteerService.saveVolunteer(volunteer));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Volunteer> updateVolunteer(@PathVariable Long id, @RequestBody Volunteer volunteer) {
        Optional<Volunteer> existingVolunteer = volunteerService.getVolunteerById(id);
        if (existingVolunteer.isPresent()) {
            volunteer.setId(id);
            return ResponseEntity.ok(volunteerService.saveVolunteer(volunteer));
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVolunteer(@PathVariable Long id) {
        volunteerService.deleteVolunteer(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/status/{status}")
    public List<Volunteer> getVolunteersByStatus(@PathVariable String status) {
        return volunteerService.getVolunteersByStatus(status);
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<Volunteer> updateVolunteerStatus(@PathVariable Long id, @RequestBody Map<String, String> statusUpdate) {
        String status = statusUpdate.get("status");
        Volunteer updatedVolunteer = volunteerService.updateVolunteerStatus(id, status);
        if (updatedVolunteer != null) {
            return ResponseEntity.ok(updatedVolunteer);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/stats")
    public Map<String, Object> getVolunteerStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("approvedVolunteers", volunteerService.getApprovedVolunteersCount());
        stats.put("pendingVolunteers", volunteerService.getPendingVolunteersCount());
        return stats;
    }
}