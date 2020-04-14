package com.example.minijobmobile.remote.response;

public class OnBoardingResponse {
    private String user_id = "";
    private String password = "";
    private String name = "";
    private String first_name = "";
    private String last_name = "";
    private String status = "";

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
