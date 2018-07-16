package com.example.testmongodb.controller;

import com.example.testmongodb.entity.UserContact;
import com.example.testmongodb.service.UserContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserContactController {
    private final UserContactService userContactService;

    @GetMapping("/all")
    public List<UserContact> getAll(String userAddr) {
        return this.userContactService.getContacts(userAddr);
    }

    @GetMapping("/contact")
    public UserContact get(String userAddr, String receiAddr) throws Exception {
        return this.userContactService.getContact(userAddr, receiAddr);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
    public void add(String userAddr, @RequestBody UserContact contact) {
        this.userContactService.addContact(userAddr, contact);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(String userAddr, UserContact contact) {
        this.userContactService.updateContact(userAddr, contact);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(String userAddr, String receiAddr) {
        this.userContactService.deleteContact(userAddr, receiAddr);
    }

    @RequestMapping(value = "/delete/all", method = RequestMethod.DELETE)
    public void deleteAll(String userAddr) {
        this.userContactService.deleteAll(userAddr);
    }
}
