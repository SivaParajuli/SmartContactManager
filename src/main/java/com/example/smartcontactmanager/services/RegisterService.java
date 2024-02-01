package com.example.smartcontactmanager.services;

import com.example.smartcontactmanager.models.User;
import org.springframework.stereotype.Service;

public interface RegisterService {
    User doRegister(User user);
}
