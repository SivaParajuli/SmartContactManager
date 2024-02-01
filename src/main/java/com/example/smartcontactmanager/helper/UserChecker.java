package com.example.smartcontactmanager.helper;

import com.example.smartcontactmanager.models.Contact;
import com.example.smartcontactmanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserChecker {
    @Autowired
    private UserService userService;

    public boolean isValidateUser(Principal principal, Contact contact){
        if(userService.getUserByUsername(principal.getName()).getId()==contact.getUser().getId()){
            return true;
        }
        return false;
    }
}
