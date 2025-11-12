package com.project.ngo_backend.repository;

import com.project.ngo_backend.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    
    List<Volunteer> findByStatus(String status);
    
    boolean existsByEmail(String email);
    
    @Query("SELECT COUNT(v) FROM Volunteer v WHERE v.status = 'approved'")
    Long getApprovedVolunteersCount();
    
    @Query("SELECT COUNT(v) FROM Volunteer v WHERE v.status = 'pending'")
    Long getPendingVolunteersCount();
}