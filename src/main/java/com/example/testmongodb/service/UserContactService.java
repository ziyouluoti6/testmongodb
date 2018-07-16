package com.example.testmongodb.service;

import com.example.testmongodb.entity.UserContact;
import com.example.testmongodb.entity.UserContactEntity;
import com.example.testmongodb.repository.UserContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserContactService {
    private final UserContactRepository repository;

    public List<UserContact> getContacts(String userAddr) {
        return this.repository.getByUserAddr(userAddr).getContacts();
    }

    public UserContact getContact(String userAddr, String receiAddr) throws Exception {
        return this.getContacts(userAddr).stream()
                .filter(contact -> receiAddr.equals(contact.getReceiAddr()))
                .findFirst().orElseThrow(Exception::new);
    }

    public void addContact(String userAddr, UserContact contact) {
        UserContactEntity entity = this.repository.getByUserAddr(userAddr);
        entity.getContacts().add(contact);
        this.repository.save(entity);
    }

    public void updateContact(String userAddr, UserContact contact) {
        UserContactEntity entity = this.repository.getByUserAddr(userAddr);
        List<UserContact> contacts = entity.getContacts();
        contacts.removeIf(ct -> contact.getReceiAddr().equals(ct.getReceiAddr()));
        contacts.add(contact);
        this.repository.save(entity);
    }

    public void deleteContact(String userAddr, String receiAddr) {
        UserContactEntity entity = this.repository.getByUserAddr(userAddr);
        List<UserContact> contacts = entity.getContacts();
        contacts.removeIf(ct -> receiAddr.equals(ct.getReceiAddr()));
        this.repository.save(entity);
    }

    public void deleteAll(String userAddr) {
        UserContactEntity entity = this.repository.getByUserAddr(userAddr);
        entity.setContacts(new ArrayList<>());
        this.repository.save(entity);
    }

    //@PostConstruct
    public void init() {
        this.repository.save(create());
    }

    private UserContactEntity create() {
        UserContactEntity entity = new UserContactEntity();
        entity.setUserAddr(UUID.randomUUID().toString());
        for (int i = 0; i < 3; i++) {
            UserContact contact = new UserContact();
            contact.setFirstName("First" + i);
            contact.setLastName("Last" + i);
            contact.setReceiAddr(UUID.randomUUID().toString());
            entity.setContacts(new ArrayList<>());
            entity.getContacts().add(contact);
        }
        return entity;

    }
}
