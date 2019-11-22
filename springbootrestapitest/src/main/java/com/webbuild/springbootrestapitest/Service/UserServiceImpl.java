package com.webbuild.springbootrestapitest.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.webbuild.springbootrestapitest.model.Role;
import com.webbuild.springbootrestapitest.model.User;
import com.webbuild.springbootrestapitest.respository.RoleRepository;
import com.webbuild.springbootrestapitest.respository.UserRepository;




@Service
public class UserServiceImpl implements UserService {
	@Autowired
    private UserRepository userRepository;
    
	@Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    Set<Role> Security= new HashSet<Role>();

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Security.add(roleRepository.findById(user.getRoleid()).get());
        user.setRoles(Security);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}