package com.example.demo.dto;

import com.example.demo.persistance.UserProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDto {
    private String dateOfBirth;
    private String sex;
    private String password;
    private String username;
    private Integer id;

    public UserDto() {
    }
    public UserDto(UserProfile up) {
        dateOfBirth = up.getDateOfBirth();
        sex=up.getSex();
        password=up.getPassword();
        username=up.getUsername();
        id=up.getIdUser();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public UserProfile getProfile(){
        UserProfile up= new UserProfile();
        up.setDateOfBirth(dateOfBirth);
        up.setPassword(password);
        up.setSex(sex);
        up.setUsername(username);
        return up;
    }

}