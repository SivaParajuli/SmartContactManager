package com.example.smartcontactmanager.repo;

import com.example.smartcontactmanager.models.Contact;
import com.example.smartcontactmanager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    @Query("select u from User u where u.email=:e")
    User getUserByUserName(@Param("e") String email);
}
