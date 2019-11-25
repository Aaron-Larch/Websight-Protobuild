package com.webbuild.javabrains.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webbuild.javabrains.model.User;

//connect to the user table In the Database
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}