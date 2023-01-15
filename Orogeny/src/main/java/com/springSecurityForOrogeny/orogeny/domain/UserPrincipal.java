package com.springSecurityForOrogeny.orogeny.domain;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import static java.util.Arrays.stream;

public class UserPrincipal implements UserDetails{

	private User user; // we can pass user to the constructor of this class, we have actual user details
	// in class User and we can pass that value here in spring security
	
	public UserPrincipal(User user) {
		super();
		this.user = user;
	}
	
	//This is an important method -> collection with return any user that extends grantedauthority
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return stream(this.user.getAuthoritires()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}
	
	
	@Override
	public String getPassword() {
		return this.user.getPassword();//Spring security whenever you need password take password from user passed
	}

	@Override
	public String getUsername() {
		return this.user.getUserName();//spring security whenever you need username take User.getUsername
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;// we don't wann'a use this it was auto generated
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return this.user.isNotLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;//after some days it might get expired
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.user.isActive();
	}
	
	

}
