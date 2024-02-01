package com.example.smartcontactmanager.repo;

import com.example.smartcontactmanager.models.Contact;
import com.example.smartcontactmanager.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ContactRepo extends JpaRepository<Contact,Long> {
    @Query("SELECT c from Contact c where c.user= :u")
    Page<Contact> findContactsByUser(@Param("u") User user, Pageable pageable);
    @Modifying
    @Transactional
    @Query("DELETE From Contact c Where c.id=:i")
    int deleteContact(@Param("i")Long id);
    @Query("select c From Contact c Where c.phone=:p and c.user=:u")
    Contact getContactByPhoneAndUser(@Param("p")String phone,@Param("u")User user);
}
