package com.springSecurityForOrogeny.orogeny.resourceController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springSecurityForOrogeny.orogeny.domain.User;

@RestController
@RequestMapping(value = "/user")
public class UserResource {
	
	@GetMapping("/home")
	public String showUser() {
		return "application works";//this is gonna return some empty user
	}

}
