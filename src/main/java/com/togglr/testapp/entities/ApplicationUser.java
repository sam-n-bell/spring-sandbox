package com.togglr.testapp.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ApplicationUser {

    public ApplicationUser() {

    }

    public ApplicationUser(int id, String name, String email, String eid) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.eid = eid;
    }

    @Id
    private int id;
    private String name;
    private String email;
    private String eid;

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

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
