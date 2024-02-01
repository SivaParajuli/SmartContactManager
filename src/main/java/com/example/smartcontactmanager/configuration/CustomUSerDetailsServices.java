package com.example.smartcontactmanager.configuration;

import com.example.smartcontactmanager.models.User;
import com.example.smartcontactmanager.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUSerDetailsServices implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user =  userRepo.getUserByUserName(username);
       if(user==null){
           throw new UsernameNotFoundException("User not found!!");
       }
       CustomUserDetails userDetails = new CustomUserDetails(user);
        return userDetails;
    }
}
