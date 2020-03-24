package com.togglr.testapp.entities;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name="TASKS")
@SQLDelete(sql = "UPDATE tasks SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class TaskEntity {

    public TaskEntity() {

    }
    private Integer id;
    private String description;
    private ApplicationUserEntity userByUserId;
    private Integer userId;
    private boolean deleted;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    @Basic
    @Column(name = "USER_ID")
    public Integer getUserId() {
        return userId;
    }

    @Basic
    @Column(name = "DELETED", columnDefinition = "BOOLEAN default FALSE")
    public boolean getDeleted() {
        return deleted;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    // name is the foreign key
    // referencedcolumnname is the name of the PK column in the foreign table
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    public ApplicationUserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserByUserId(ApplicationUserEntity applicationUserEntity) {
        this.userByUserId = userByUserId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDeleted(boolean deleted) { this.deleted = deleted; }

    public void setDescription(String description) {
        this.description = description;
    }

}
