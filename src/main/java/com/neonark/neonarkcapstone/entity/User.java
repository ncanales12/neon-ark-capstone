package com.neonark.neonarkcapstone.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "system_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String roleName;

    public User() {}

    public User(String username, String roleName) {
        this.username = username;
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}