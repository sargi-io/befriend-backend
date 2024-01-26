package com.befriend.befriend.model;
import lombok.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users") // Change table name as per your requirement
public class User {
    
    public User() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Column(nullable = false)
    @Getter @Setter private String name;

    @Column(nullable = false, unique = true)
    @Getter @Setter private String email;

    @Column(nullable = false)
    @Getter @Setter private String password;

    // Constructors, getters and setters
}