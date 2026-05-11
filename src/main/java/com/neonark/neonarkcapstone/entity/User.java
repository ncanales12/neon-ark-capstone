package com.neonark.neonarkcapstone.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "system_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String phone;
    private String roleName;

    public User() {
    }

    public User(String fullName, String email, String phone, String roleName) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}