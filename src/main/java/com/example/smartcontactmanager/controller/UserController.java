package com.example.smartcontactmanager.controller;

import com.example.smartcontactmanager.dto.ContactDto;
import com.example.smartcontactmanager.helper.FileProcessor;
import com.example.smartcontactmanager.helper.Messages;
import com.example.smartcontactmanager.helper.UserChecker;
import com.example.smartcontactmanager.models.Contact;
import com.example.smartcontactmanager.models.User;
import com.example.smartcontactmanager.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserChecker userChecker;
    @Autowired
    private FileProcessor fileProcessor;

    //method for adding common data
    @ModelAttribute
    public void addCommonData(Model model,Principal principal){
        String username = principal.getName();
        User user = userService.getUserByUsername(username);
        model.addAttribute("user" ,user);
    }

    @RequestMapping("/index")
    public String dashboard(Model model){
        model.addAttribute("title","User-Dashboard");
        return "normal/user_dashboard";
    }
    //open add form handler
    @RequestMapping("/add-contact")
    public String openAddContactForm(Model model){
        model.addAttribute("title","Add-Contact");
        model.addAttribute("Contact",new ContactDto());
        return "normal/add_contact_form";
    }
    @PostMapping("/process_contact")
    public String processContact(@Valid @ModelAttribute("contact") ContactDto contactDto, BindingResult bindingResult, Principal principal, Model model, HttpSession session) {
        try {
            if(bindingResult.hasErrors()){
                return "Some error occurs";
            }
            userService.addContact(contactDto, principal.getName());
            session.setAttribute("message",new Messages("Contact is added successfully","alert-success"));
            model.addAttribute("contact",new ContactDto());
            return "normal/add_contact_form";
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message",new Messages("Something went wrong!! try again ","alert-danger"));
            model.addAttribute("contact",contactDto);
            return "normal/add_contact_form";
        }
    }

    @GetMapping("/show_contacts/{page}")
    public String showContacts(@PathVariable("page")Integer page, Model model,Principal principal){
        model.addAttribute("title","Show Contact");
        Page<Contact> contactList = userService.getAllContacts(principal.getName(),page);
        model.addAttribute("contacts",contactList);
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPages",contactList.getTotalPages());
        return "normal/show_contacts";
    }

    @GetMapping("/{id}/delete_contact")
    public String deleteContact(@PathVariable("id")Long id, Model model,Principal principal, HttpSession session){
        Contact contact = userService.getContactById(id);
        String path = contact.getcImage();
        if(userService.deleteContact(contact) >0) {
            session.setAttribute("message", new Messages("Deleted Successfully", "alert-success"));
            fileProcessor.deleteFile(path);
        }
        else {
            session.setAttribute("message", new Messages("Deletion Unsuccessful", "alert-danger"));
        }
        return "redirect:/api/user/show_contacts/0";
    }

    @PostMapping("/update_contact/{id}")
    public String updateForm(@PathVariable("id")Long id, Model model){
        Contact contact = userService.getContactById(id);
        model.addAttribute("title","Update Form");
        model.addAttribute("contact",contact);
        return "normal/update_form";
    }

    @GetMapping("/{cid}/contact")
    public String showContactDetails(@PathVariable("cid") Long cId,Model model,Principal principal){
        Contact contact = userService.getContactById(cId);
        if(userChecker.isValidateUser(principal,contact))
            model.addAttribute("title",contact.getName());
            model.addAttribute("contact",contact);
        return "normal/contact_detail";
    }
    @GetMapping("/my_profile")
    public String showProfile(Model model,Principal principal){
        model.addAttribute("title","My Profile");
        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("user",user);
        return "normal/my_profile";
    }
    @PostMapping("/process_update")
    public String updateHAndler(@ModelAttribute Contact contact, @RequestParam("profileImage") MultipartFile multipartFile, Principal principal, Model model, HttpSession session){
        try{
            model.addAttribute("title","Update Process");
           userService.updateContact(contact,multipartFile,userService.getUserByUsername(principal.getName()));
           session.setAttribute("message",new Messages("Your contact is updated","alert-success"));
            return "redirect:/api/user/show_contacts/0";
        }catch (Exception e){
            e.printStackTrace();
            session.setAttribute("message",new Messages("Your contact isn't updated","alert-danger"));
            return "redirect:/api/user//update_contact/"+contact.getId();

        }
    }
}
