package com.example.smartcontactmanager.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Name field is required")
    @Size(min = 2,max = 20,message = "Name must be between 2-20 character")
    private String name;
    @Column(unique = true)
    @NotBlank(message = "Email mustn't be blank.")
    @Email(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z]+.[a-zA-Z]{3}$",message = "Invalid email !!")
    private String email;
    private String role;
    private boolean enabled;
    @Column(length =500)
    private String about;
    @AssertTrue(message = "Please accept the terms and conditions.")
    private boolean agreed;
    private String image;
    @NotBlank(message = "Password shouldn't be blank.")
    @Size(min=8,message = "Password must be minimum 8 character.")
    private String password;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
    private List<Contact> contactList;

    public User(){
        super();
    }

    public User(Long id,String name, String email, String role, boolean enabled, String about, boolean agreed, String image, String password, List<Contact> contactList) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.enabled = enabled;
        this.about = about;
        this.agreed = agreed;
        this.image = image;
        this.password = password;
        this.contactList = contactList;
        this.id=id;
    }

    public boolean isAgreed() {
        return agreed;
    }

    public void setAgreed(boolean agreed) {
        this.agreed = agreed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public long getId() {
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
}
