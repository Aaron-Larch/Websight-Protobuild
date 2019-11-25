package com.webbuild.javabrains.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webbuild.javabrains.model.Role;

//Connect to The Role Table in the database
public interface RoleRepository extends JpaRepository<Role, Long>{
}
