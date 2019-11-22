package com.webbuild.springbootrestapitest.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "DIVISIONS")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long DIVISIONID;

    private String DIVISIONNAME;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Long getDIVISIONID() {
        return DIVISIONID;
    }

    public void setDIVISIONID(Long id) {
        this.DIVISIONID = id;
    }

    public String getDIVISIONNAME() {
        return DIVISIONNAME;
    }

    public void setDIVISIONNAME(String name) {
        this.DIVISIONNAME = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}