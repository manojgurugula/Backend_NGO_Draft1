package com.project.ngo_backend.service;

import com.project.ngo_backend.entity.Volunteer;
import com.project.ngo_backend.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VolunteerService {
    
    @Autowired
    private VolunteerRepository volunteerRepository;
    
    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }
    
    public Optional<Volunteer> getVolunteerById(Long id) {
        return volunteerRepository.findById(id);
    }
    
    public Volunteer saveVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }
    
    public void deleteVolunteer(Long id) {
        volunteerRepository.deleteById(id);
    }
    
    public List<Volunteer> getVolunteersByStatus(String status) {
        return volunteerRepository.findByStatus(status);
    }
    
    public boolean emailExists(String email) {
        return volunteerRepository.existsByEmail(email);
    }
    
    public Long getApprovedVolunteersCount() {
        Long count = volunteerRepository.getApprovedVolunteersCount();
        return count != null ? count : 0L;
    }
    
    public Long getPendingVolunteersCount() {
        Long count = volunteerRepository.getPendingVolunteersCount();
        return count != null ? count : 0L;
    }
    
    public Volunteer updateVolunteerStatus(Long id, String status) {
        Optional<Volunteer> volunteerOpt = volunteerRepository.findById(id);
        if (volunteerOpt.isPresent()) {
            Volunteer volunteer = volunteerOpt.get();
            volunteer.setStatus(status);
            return volunteerRepository.save(volunteer);
        }
        return null;
    }
}