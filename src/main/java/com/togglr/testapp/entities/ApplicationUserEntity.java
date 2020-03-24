package com.togglr.testapp.entities;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="USERS")
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class ApplicationUserEntity {


    private Integer id;
    private String name;
    private String email;
    private String eid;
    private Collection<TaskEntity> tasksById;
    private boolean deleted;

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
    @OneToMany(targetEntity = TaskEntity.class, mappedBy = "userByUserId", cascade = CascadeType.REMOVE)
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

    public void setDeleted(boolean deleted) { this.deleted = deleted; }

    @Basic
    @Column(name = "DELETED", columnDefinition = "BOOLEAN default FALSE")
    public boolean getDeleted() {
        return deleted;
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
