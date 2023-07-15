package org.crypto.training.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    public User() {}

    public User(long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Temporal(TemporalType.DATE)
    @Column(name = "registration_date")
    private Date registration_date;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY) // cascade: remove correlated data in another table
    private Set<Investment> investments;

    public Set<Investment> getInvestments() {
        return investments;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Date getRegistration_date() {
        return registration_date;
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
