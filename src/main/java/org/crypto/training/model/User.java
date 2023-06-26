package org.crypto.training.model;


import java.util.Date;

public class User {
    public User() {}

    private long id;

    private String username;

    private String password;

    private String email;

    private Date registration_date;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegistration_date(Date registration_date) {
        this.registration_date = this.registration_date;
    }
}
