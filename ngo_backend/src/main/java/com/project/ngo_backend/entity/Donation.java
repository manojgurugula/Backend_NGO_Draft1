package com.project.ngo_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "donations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Donation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String donorName;
    
    @Column(nullable = false)
    private String email;
    
    private String phone;
    
    @Column(nullable = false)
    private Double amount;
    
    private String message;
    
    @Column(nullable = false)
    private String donationType; // one-time, monthly
    
    @Column(nullable = false)
    private LocalDateTime donationDate;
    
    private String paymentStatus; // pending, completed, failed
    
    private String transactionId;
    
    @PrePersist
    protected void onCreate() {
        donationDate = LocalDateTime.now();
        if (paymentStatus == null) {
            paymentStatus = "pending";
        }
    }
}