package com.example.smartcontactmanager.dto;

import org.springframework.web.multipart.MultipartFile;

public class ContactDto {
    private String name;
    private String nickName;
    private String phone;
    private String work;
    private String email;
    private MultipartFile cImage;
    private String description;

    public ContactDto(){
        super();
    }

    public ContactDto( String name, String nickName, String phone, MultipartFile cImage, String description, String work, String email) {
        this.name = name;
        this.nickName = nickName;
        this.phone = phone;
        this.cImage = cImage;
        this.description = description;
        this.work = work;
        this.email = email;
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

    public MultipartFile getcImage() {
        return cImage;
    }

    public void setcImage(MultipartFile cImage) {
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

}
