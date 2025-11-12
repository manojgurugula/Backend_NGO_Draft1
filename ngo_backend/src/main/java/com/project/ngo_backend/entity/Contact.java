package com.project.ngo_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String email;
    
    private String phone;
    
    @Column(nullable = false)
    private String subject;
    
    @Column(nullable = false, length = 1000)
    private String message;
    
    @Column(nullable = false)
    private String status; // new, read, replied
    
    @Column(nullable = false)
    private LocalDateTime createdDate;
    
    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
        if (status == null) {
            status = "new";
        }
    }
}