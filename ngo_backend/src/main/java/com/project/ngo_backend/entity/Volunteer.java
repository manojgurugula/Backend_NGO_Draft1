package com.project.ngo_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "volunteers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Volunteer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String fullName;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String phone;
    
    private String address;
    
    private String skills;
    
    private String availability;
    
    private String motivation;
    
    @Column(nullable = false)
    private String status; // pending, approved, rejected
    
    @Column(nullable = false)
    private LocalDateTime applicationDate;
    
    @PrePersist
    protected void onCreate() {
        applicationDate = LocalDateTime.now();
        if (status == null) {
            status = "pending";
        }
    }
}