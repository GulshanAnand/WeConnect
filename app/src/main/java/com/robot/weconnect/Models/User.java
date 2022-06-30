package com.robot.weconnect.Models;

public class User {
    String profilePhoto, userName, email, password, userId, lastMessage;

    public User(String profilePhoto, String userName, String email, String password, String userId, String lastMessage) {
        this.profilePhoto = profilePhoto;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userId = userId;
        this.lastMessage = lastMessage;
    }

    //empty constructor
    public User(){}

    //custom constructor for signing up (does not have profilePhoto, userId and lastmessage
    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
