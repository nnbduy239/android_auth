package com.iuh.authapp;

public class UserData {
    private String userId;
    private String name;
    private String email;
    private String password;
    private String happy;
    private String sad;
    private String normal;

    public UserData() {
    }

    public UserData(String userId, String name, String email, String password, String happy, String sad, String normal) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.happy = happy;
        this.sad = sad;
        this.normal = normal;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHappy() {
        return happy;
    }

    public void setHappy(String happy) {
        this.happy = happy;
    }

    public String getSad() {
        return sad;
    }

    public void setSad(String sad) {
        this.sad = sad;
    }

    public String getNormal() {
        return normal;
    }

    public void setNormal(String normal) {
        this.normal = normal;
    }
}
