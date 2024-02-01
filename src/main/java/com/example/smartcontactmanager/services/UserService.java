package com.example.smartcontactmanager.services;

import com.example.smartcontactmanager.dto.ContactDto;
import com.example.smartcontactmanager.models.Contact;
import com.example.smartcontactmanager.models.User;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;



public interface UserService {
    Contact addContact(ContactDto contactDto, String email);
    User getUserByUsername(String email);
    Page<Contact> getAllContacts(String email, Integer page);
    Contact getContactById(Long id);
    int deleteContact(Contact contact);
    Contact getContactByUserAndPhone(String phone,User user);
    int updateContact(Contact contact, MultipartFile multipartFile,User user);
}
