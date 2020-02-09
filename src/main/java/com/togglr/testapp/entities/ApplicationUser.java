package com.togglr.testapp.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ApplicationUser {

    public ApplicationUser() {

    }

    public ApplicationUser(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Id
    private int id;
    private String name;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
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

    @Override
    public String toString() {
        return "ApplicationUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
