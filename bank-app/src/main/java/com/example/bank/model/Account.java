package com.example.bank.model;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    private String id;

    private String name;

    private String password;

    private String email;

    private int amount;

    // Getters and Setters
}
