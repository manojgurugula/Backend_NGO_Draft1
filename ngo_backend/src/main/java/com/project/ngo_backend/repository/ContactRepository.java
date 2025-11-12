package com.project.ngo_backend.repository;

import com.project.ngo_backend.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    
    List<Contact> findByStatus(String status);
    
    List<Contact> findByOrderByCreatedDateDesc();
    
    @Query("SELECT COUNT(c) FROM Contact c WHERE c.status = 'new'")
    Long getNewMessagesCount();
}