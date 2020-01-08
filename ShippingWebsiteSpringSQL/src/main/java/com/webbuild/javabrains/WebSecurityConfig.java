package com.webbuild.javabrains;

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

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService; //call user detail method
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(); //Create new encryption key
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests() //Set website available to the user at login
                .antMatchers("/resources/**", "/registration").permitAll()
                .antMatchers("/Sipping/**", "/welcome").authenticated()
                .antMatchers("/Sipping/America").hasAuthority("America")
                .antMatchers("/Sipping/Europe/**", "/Sipping/switchup").hasAuthority("Europe")
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

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder()); //set roles
    }
}
//Don't really know what's going on here will research later