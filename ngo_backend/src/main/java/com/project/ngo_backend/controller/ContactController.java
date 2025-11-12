package com.project.ngo_backend.controller;

import com.project.ngo_backend.entity.Contact;
import com.project.ngo_backend.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins = "http://localhost:3000")
public class ContactController {
    
    @Autowired
    private ContactService contactService;
    
    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        Optional<Contact> contact = contactService.getContactById(id);
        return contact.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Contact createContact(@RequestBody Contact contact) {
        return contactService.saveContact(contact);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact contact) {
        Optional<Contact> existingContact = contactService.getContactById(id);
        if (existingContact.isPresent()) {
            contact.setId(id);
            return ResponseEntity.ok(contactService.saveContact(contact));
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/status/{status}")
    public List<Contact> getContactsByStatus(@PathVariable String status) {
        return contactService.getContactsByStatus(status);
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<Contact> updateContactStatus(@PathVariable Long id, @RequestBody Map<String, String> statusUpdate) {
        String status = statusUpdate.get("status");
        Contact updatedContact = contactService.updateContactStatus(id, status);
        if (updatedContact != null) {
            return ResponseEntity.ok(updatedContact);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/stats")
    public Map<String, Object> getContactStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("newMessages", contactService.getNewMessagesCount());
        return stats;
    }
}