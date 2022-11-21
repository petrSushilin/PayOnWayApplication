package com.petrsushilin.ifmo.payonway.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
@Component
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @SequenceGenerator(
            name = "client_generator",
            sequenceName = "client_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "client_generator"
    )
    private long id;
    private String name;
    private String login;
    private String email;
    private String mobilePhoneNumber;
    private String password;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
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

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
