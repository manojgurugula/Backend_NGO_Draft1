package com.project.ngo_backend.service;

import com.project.ngo_backend.entity.Contact;
import com.project.ngo_backend.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    
    @Autowired
    private ContactRepository contactRepository;
    
    public List<Contact> getAllContacts() {
        return contactRepository.findByOrderByCreatedDateDesc();
    }
    
    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }
    
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }
    
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
    
    public List<Contact> getContactsByStatus(String status) {
        return contactRepository.findByStatus(status);
    }
    
    public Long getNewMessagesCount() {
        Long count = contactRepository.getNewMessagesCount();
        return count != null ? count : 0L;
    }
    
    public Contact updateContactStatus(Long id, String status) {
        Optional<Contact> contactOpt = contactRepository.findById(id);
        if (contactOpt.isPresent()) {
            Contact contact = contactOpt.get();
            contact.setStatus(status);
            return contactRepository.save(contact);
        }
        return null;
    }
}