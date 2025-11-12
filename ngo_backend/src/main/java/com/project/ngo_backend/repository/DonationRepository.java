package com.project.ngo_backend.repository;

import com.project.ngo_backend.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    
    List<Donation> findByPaymentStatus(String paymentStatus);
    
    List<Donation> findByDonationType(String donationType);
    
    List<Donation> findByDonationDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    @Query("SELECT SUM(d.amount) FROM Donation d WHERE d.paymentStatus = 'completed'")
    Double getTotalDonations();
    
    @Query("SELECT COUNT(d) FROM Donation d WHERE d.paymentStatus = 'completed'")
    Long getTotalDonorsCount();
    
    @Query("SELECT SUM(d.amount) FROM Donation d WHERE d.paymentStatus = 'completed' AND d.donationDate >= :startDate")
    Double getDonationsFromDate(LocalDateTime startDate);
}