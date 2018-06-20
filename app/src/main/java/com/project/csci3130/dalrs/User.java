package com.project.csci3130.dalrs;

public class User {

    private String id;
    private String Email;
    private String DisplayName;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return Email;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }


    public User(String id, String email, String displayName) {
        this.id = id;
        Email = email;
        DisplayName = displayName;
    }

}
