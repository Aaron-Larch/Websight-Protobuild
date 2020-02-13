package com.webbuild.javabrains.Security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.webbuild.javabrains.model.User;


public class UserPrincipal implements UserDetails{
	//establish global variables
	private static final long serialVersionUID = -9066751734589401221L;
	private User user;
	public UserPrincipal(User user){this.user = user;}
	 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();

	        /* Extract list of permissions (name)
	        this.user.getPermissions().forEach(p -> {
	            GrantedAuthority authority = new SimpleGrantedAuthority(p); //Set permissions
	            authorities.add(authority);
	        });*/

	        
	      // Extract list of roles (ROLE_name)
	      this.user.getRoles().forEach(r -> {
	    	GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + r.getDIVISIONNAME()); //Set Role
	    	authorities.add(authority); 
	      });
	      
	      return authorities;
	    }
	
		//Check For any account flags that might require special actions
	    @Override
	    public String getPassword() {
	        return this.user.getPassword();
	    }

	    @Override
	    public String getUsername() {
	        return this.user.getUsername();
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return false;
		}
}
