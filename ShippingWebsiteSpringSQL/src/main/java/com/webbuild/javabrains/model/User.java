package com.webbuild.javabrains.model;

import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name = "USERS") //Table Reference in database
public class User {
    
	@Id //identify primary key
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence") //Set Value to auto populate in database
    @SequenceGenerator(name = "id_Sequence", sequenceName = "USERS_SEQ") //Declare Database Sequence you want to use
    private int PersonID;

    private String username;

    private String password;
    
    private Long roleid;

	@Transient
    private String passwordConfirm;

    @ManyToMany //Declare value as receiving value from other table
    private Set<Role> roles; //set a many to many relation with the Role table

    public int getId() {
        return PersonID; //Retrieve a value
    }

    public void setId(int id) {
        this.PersonID = id; // save a value
    }

    public String getUsername() {
        return username; //Retrieve a value
    }

    public void setUsername(String username) {
        this.username = username; // save a value
    }

    public String getPassword() {
        return password; //Retrieve a value
    }

    public void setPassword(String password) {
        this.password = password; // save a value
    }

    public String getPasswordConfirm() {
        return passwordConfirm; //Retrieve a value
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm; // save a value
    }

    public Long getRoleid() {
		return roleid; //Retrieve a value
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid; // save a value
	}
	
    public Set<Role> getRoles() {
        return roles; //Retrieve a value
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles; // save a value
    }
}
