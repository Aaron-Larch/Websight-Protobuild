package com.webbuild.springbootrestapitest.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webbuild.springbootrestapitest.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
