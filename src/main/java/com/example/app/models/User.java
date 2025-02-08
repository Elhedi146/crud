package com.example.app.models;

public class User {
    private  int id;
    private String name;
    private String lastname;
    private String email;
    private String cin;
    private String password;

    // Constructeur
    public User(int id ,String name, String lastname, String email, String cin, String password) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.cin = cin;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }



    public String getLastname() {
        return lastname;
    }



    public String getName() {
        return name;
    }



    public String getCin() {
        return cin;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public void setCin(String cin) {
        this.cin = cin;
    }
}