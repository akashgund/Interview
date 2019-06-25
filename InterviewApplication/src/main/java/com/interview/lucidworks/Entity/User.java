package com.interview.lucidworks.Entity;

import javax.persistence.*;

@Entity
@Table(name="User")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="UserId")
    int userId;

    @Column(name="Email")
    String email;

    @Column(name="Password")
    String password;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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





}