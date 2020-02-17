package com.togglr.testapp.entities;

import javax.persistence.*;

@Entity
@Table(name="TASKS")
public class TaskEntity {

    public TaskEntity() {

    }
    private Integer id;
    private String description;
    private ApplicationUserEntity userByUserId;
    private Integer userId;

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



    public void setDescription(String description) {
        this.description = description;
    }

}
