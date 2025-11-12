package com.project.ngo_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false, length = 2000)
    private String description;
    
    @Column(nullable = false)
    private LocalDateTime eventDate;
    
    private String location;
    
    private String imageUrl;
    
    private Integer maxParticipants;
    
    private Integer currentParticipants = 0;
    
    @Column(nullable = false)
    private String status; // upcoming, ongoing, completed, cancelled
    
    @Column(nullable = false)
    private LocalDateTime createdDate;
    
    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
        if (status == null) {
            status = "upcoming";
        }
    }
}