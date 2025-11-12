package com.project.ngo_backend.controller;

import com.project.ngo_backend.entity.Donation;
import com.project.ngo_backend.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/donations")
@CrossOrigin(origins = "http://localhost:3000")
public class DonationController {
    
    @Autowired
    private DonationService donationService;
    
    @GetMapping
    public List<Donation> getAllDonations() {
        return donationService.getAllDonations();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Donation> getDonationById(@PathVariable Long id) {
        Optional<Donation> donation = donationService.getDonationById(id);
        return donation.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Donation createDonation(@RequestBody Donation donation) {
        return donationService.saveDonation(donation);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Donation> updateDonation(@PathVariable Long id, @RequestBody Donation donation) {
        Optional<Donation> existingDonation = donationService.getDonationById(id);
        if (existingDonation.isPresent()) {
            donation.setId(id);
            return ResponseEntity.ok(donationService.saveDonation(donation));
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonation(@PathVariable Long id) {
        donationService.deleteDonation(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/status/{status}")
    public List<Donation> getDonationsByStatus(@PathVariable String status) {
        return donationService.getDonationsByStatus(status);
    }
    
    @GetMapping("/stats")
    public Map<String, Object> getDonationStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalDonations", donationService.getTotalDonations());
        stats.put("totalDonors", donationService.getTotalDonorsCount());
        stats.put("monthlyDonations", donationService.getMonthlyDonations());
        return stats;
    }
}