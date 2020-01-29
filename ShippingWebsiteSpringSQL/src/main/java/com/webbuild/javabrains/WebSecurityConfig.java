package com.webbuild.javabrains;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService; //call user detail method
    
    @Autowired
    private DataSource dataSource;
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(); //Create new encryption key
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder()); //set roles
    }//This Method must come first so that there are rolls to read??
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests() //Set website available to the user at login
                .antMatchers("/resources/**", "/registration", "/HomePage").permitAll()
                .antMatchers("/welcome", "/Sipping/America/**").hasRole("America")
                .antMatchers("/welcome", "/Sipping/Europe/**", "/Sipping/switchup").hasRole("Europe")
                .antMatchers("/Stats/**").hasAuthority("Europe")
                .anyRequest().authenticated()
                .and()
            .formLogin()//declare sites available to the which user after login
                .loginPage("/login").permitAll()
                .and()
            .logout()
            	.deleteCookies("JSESSIONID")
            	.invalidateHttpSession(true)
                .permitAll();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();//gather server log
    }
    
    PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        tokenRepositoryImpl.setDataSource(dataSource);
        return tokenRepositoryImpl;
    }
}
//Don't really know what's going on here will research later