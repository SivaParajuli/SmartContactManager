package com.example.smartcontactmanager.services;

import com.example.smartcontactmanager.dto.ContactDto;
import com.example.smartcontactmanager.helper.FileProcessor;
import com.example.smartcontactmanager.models.Contact;
import com.example.smartcontactmanager.models.User;
import com.example.smartcontactmanager.repo.ContactRepo;
import com.example.smartcontactmanager.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ContactRepo contactRepo;
    @Autowired
    private FileProcessor fileProcessor;

    @Override
    public User getUserByUsername(String email) {
        return userRepo.getUserByUserName(email);
    }

    @Override
    public Page<Contact> getAllContacts(String email,Integer page) {
        Pageable pageable = PageRequest.of(page,8);
        return contactRepo.findContactsByUser(userRepo.getUserByUserName(email),pageable);
    }

    @Override
    public Contact getContactById(Long id) {
         Optional<Contact> contactOptional= contactRepo.findById(id);
         Contact contact = contactOptional.get();
         return contact;
    }

    @Override
    public int deleteContact(Contact contact) {
       return contactRepo.deleteContact(contact.getId());
    }

    @Override
    public Contact getContactByUserAndPhone(String phone, User user) {
        Contact contact = contactRepo.getContactByPhoneAndUser(phone,user);
        return contact;
    }

    @Override
    public int updateContact(Contact contact, MultipartFile multipartFile, User user) {
        Optional<Contact> contact1 = contactRepo.findById(contact.getId());
        String file = fileProcessor.saveFile(multipartFile);
        fileProcessor.deleteFile(contact1.get().getcImage());
        contact.setcImage(file);
        contact.setUser(user);
        contactRepo.save(contact);
        return 1;
    }

    @Override
    public Contact addContact(ContactDto contactDto, String email) {
        Contact contact = new Contact();
        contact.setUser(userRepo.getUserByUserName(email));
        contact.setName(contactDto.getName());
        contact.setNickName(contactDto.getNickName());
        contact.setEmail(contactDto.getEmail());
        contact.setcImage(fileProcessor.saveFile(contactDto.getcImage()));
        contact.setWork(contactDto.getWork());
        contact.setDescription(contactDto.getDescription());
        contact.setPhone(contactDto.getPhone());
        Contact contact1 = contactRepo.save(contact);
        return contact1;
    }
}
