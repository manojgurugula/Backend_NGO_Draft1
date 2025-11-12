package com.project.ngo_backend.service;

import com.project.ngo_backend.entity.Donation;
import com.project.ngo_backend.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DonationService {
    
    @Autowired
    private DonationRepository donationRepository;
    
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }
    
    public Optional<Donation> getDonationById(Long id) {
        return donationRepository.findById(id);
    }
    
    public Donation saveDonation(Donation donation) {
        return donationRepository.save(donation);
    }
    
    public void deleteDonation(Long id) {
        donationRepository.deleteById(id);
    }
    
    public List<Donation> getDonationsByStatus(String status) {
        return donationRepository.findByPaymentStatus(status);
    }
    
    public Double getTotalDonations() {
        Double total = donationRepository.getTotalDonations();
        return total != null ? total : 0.0;
    }
    
    public Long getTotalDonorsCount() {
        Long count = donationRepository.getTotalDonorsCount();
        return count != null ? count : 0L;
    }
    
    public Double getMonthlyDonations() {
        LocalDateTime startOfMonth = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        Double monthly = donationRepository.getDonationsFromDate(startOfMonth);
        return monthly != null ? monthly : 0.0;
    }
}