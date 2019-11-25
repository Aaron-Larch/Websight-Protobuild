package com.webbuild.javabrains.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "DIVISIONS") //Table Reference in database
public class Role {
    @Id //identify primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long DIVISIONID;

    private String DIVISIONNAME;

    @ManyToMany(mappedBy = "roles") //Declare reference table
    private Set<User> users; //set a many to many relation with the user table

    public Long getDIVISIONID() {
        return DIVISIONID; //Retrieve a value
    }

    public void setDIVISIONID(Long id) {
        this.DIVISIONID = id; // save a value
    }

    public String getDIVISIONNAME() {
        return DIVISIONNAME; //Retrieve a value
    }

    public void setDIVISIONNAME(String name) {
        this.DIVISIONNAME = name; // save a value
    }

    public Set<User> getUsers() {
        return users; //Retrieve a value
    }

    public void setUsers(Set<User> users) {
        this.users = users; // save a value
    }
}