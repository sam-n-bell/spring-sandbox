package com.togglr.testapp.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="USERS")
public class ApplicationUserEntity {


    private Integer id;
    private String name;
    private String email;
    private String eid;
    private Collection<TaskEntity> tasksById;

    public ApplicationUserEntity() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // mappedBy is the property in the target entity that connects these two entities
    @OneToMany(targetEntity = TaskEntity.class, mappedBy = "userByUserId")
    public Collection<TaskEntity> getTasksById() {
        return tasksById;
    }

    public void setTasksById(Collection<TaskEntity> tasksById) {
        this.tasksById = tasksById;
    }

    @Basic
    @Column(name = "EID")
    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "EMAIL")
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
