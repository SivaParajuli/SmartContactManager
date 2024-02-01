package com.example.smartcontactmanager.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "contact")
public class Contact {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message="Name shouldn't be empty")
    private String name;
    private String nickName;
    @NotBlank(message = "Phone Number mustn't be null.")
    @Size(min=10)
    private String phone;
    private String work;
    @Email
    private String email;
    private String cImage;
    @Column(length = 1000)
    private String description;
    @ManyToOne
    private User user;

    public Contact(){
        super();
    }

    public Contact(Long id,String name, String nickName, String phone, String cImage, String description, String work, String email, User user) {
        this.name = name;
        this.nickName = nickName;
        this.phone = phone;
        this.cImage = cImage;
        this.description = description;
        this.work = work;
        this.email = email;
        this.user = user;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getcImage() {
        return cImage;
    }

    public void setcImage(String cImage) {
        this.cImage = cImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
}
