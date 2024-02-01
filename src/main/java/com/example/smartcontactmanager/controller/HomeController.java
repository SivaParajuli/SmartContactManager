package com.example.smartcontactmanager.controller;

import com.example.smartcontactmanager.helper.Messages;
import com.example.smartcontactmanager.models.User;
import com.example.smartcontactmanager.services.RegisterService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @Autowired
    private RegisterService registerService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("title","Home-Smart Contact Manager");
        return "home";
    }

    @RequestMapping("/about")
    public String about(Model model){
        model.addAttribute("title","About-Smart Contact Manager");
        return "about";
    }

    @RequestMapping("/signup")
    public String signup(Model model){
        model.addAttribute("title","Signup-Smart Contact Manager");
        model.addAttribute("user",new User());
        return "signup";
    }

    //this is for handling register
    @PostMapping(value = "/do_register")
    public String registerUser(@Valid @ModelAttribute("user") User user,BindingResult bindingResult, Model model, HttpSession session){
       try{
//           if(!user.isAgreed()) {
//               System.out.println("you doesn't check terms and conditions");
//               bindingResult.rejectValue("agreed", "error.user", "Please accept the terms and conditions");
//               throw new Exception("you doesn't check terms and conditions");
//           }
           if(bindingResult.hasErrors()){
               model.addAttribute("user",user);
               return "signup";
           }
           this.registerService.doRegister(user);
           model.addAttribute("user",new User());
           session.setAttribute("message",new Messages("Successfully registered!!","alert-primary"));
           return "signup";

       }catch(Exception e){
           e.printStackTrace();
           model.addAttribute("user",user);
           session.setAttribute("message",new Messages("Something went wrong.. "+e.getMessage().substring(0,e.getMessage().indexOf("com")+3),"alert-danger"));
           return "signup";
       }
    }
    @RequestMapping(value="/signin")
    public String login(Model model){
        model.addAttribute("tittle","Login-Smart Contact Manager");
        return "login";
    }
}
