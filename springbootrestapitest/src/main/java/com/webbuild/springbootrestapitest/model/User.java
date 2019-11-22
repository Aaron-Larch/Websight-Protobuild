package com.webbuild.springbootrestapitest.model;

import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name = "USERS")
public class User {
    
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "USERS_SEQ")
    private int PersonID;

    private String username;

    private String password;
    
    private Long roleid;

	@Transient
    private String passwordConfirm;

    @ManyToMany
    private Set<Role> roles;

    public int getId() {
        return PersonID;
    }

    public void setId(int id) {
        this.PersonID = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}
	
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
