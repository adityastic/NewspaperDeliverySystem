package com.laqr.NewspaperDeliverySystem.model;

import javax.persistence.*;

@Entity
@Table(name = "user_details")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole type;

    public User(String username, String password, UserRole type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserRole getType() {
        return type;
    }

    public User setType(UserRole type) {
        this.type = type;
        return this;
    }
}
