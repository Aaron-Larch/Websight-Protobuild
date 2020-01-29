package com.webbuild.javabrains;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.webbuild.javabrains.model.Role;
import com.webbuild.javabrains.model.User;
import com.webbuild.javabrains.repository.RoleRepository;
import com.webbuild.javabrains.repository.UserRepository;


@SpringBootTest
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@EntityScan(basePackageClasses = User.class)
public class JPARepositoryTests {
	User test = new User();
	
	@Autowired
    private UserRepository userRepository;
 
	@Autowired
	private RoleRepository roleRepository;
	
    @Test
    public void testSaveAndFindByUsername() {
    	//Given
    	test.setUsername("Test");
        userRepository.saveAndFlush(test);

        //When
        User found = userRepository.findByUsername("Test");
        
        //Then
        assertThat(found.getUsername()).isEqualTo(test.getUsername());
        userRepository.delete(test);
    }
    
    @Test
    public void testDelete() {
    	userRepository.delete(test);
        User found = userRepository.findByUsername("Test");
        assertThat(found).isEqualTo(null);
    }
	
    @Test
    public void testRoleFindAll() {
    	//Given
    	Role role=new Role();
    	String[] Test= {"America", "Europe", "Pacific"};

    	//When
        Iterable<Role> findAll = roleRepository.findAll();

        //Then
        assertThat(findAll).hasSize(3);

        for (Iterator<Role> i = findAll.iterator(); i.hasNext();) {
        	role=i.next();
        	assertThat(role.getDIVISIONNAME()).isEqualTo(Test[(int) (role.getDIVISIONID()-1)]); 
        }
    }
}
