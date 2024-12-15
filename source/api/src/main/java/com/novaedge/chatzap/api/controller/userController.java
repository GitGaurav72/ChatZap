package com.novaedge.chatzap.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novaedge.chatzap.api.entity.userEntity;
import com.novaedge.chatzap.api.model.AuthRequest;
import com.novaedge.chatzap.api.model.AuthResponse;
import com.novaedge.chatzap.api.services.UserService;
import com.novaedge.chatzap.api.util.JwtUtil;

@RestController
@RequestMapping("/api/users")
public class userController {

	
	@Autowired
	private UserService service;
	
//	@Autowired
//	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@PostMapping(value = "/register",  consumes = "application/json", produces = "application/json")
	public userEntity addUser(@RequestBody userEntity user) {
	    return service.addUserEntiry(user);
	}
	
	@GetMapping("/")
	String msg() {
		return "Hello spring";
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
	    try {
	        authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
	        );
	    } catch (Exception ex) {
	        throw new Exception("Invalid Username/Password");
	    }

	    // Generate the JWT token
	    JwtUtil jwtUtil = new JwtUtil();
	    String token = jwtUtil.generateToken(authRequest.getUsername());
	    userEntity usr = service.getUsrByUsrNm(authRequest.getUsername());

	    // Return the token wrapped in an AuthResponse object as JSON
	    return ResponseEntity.ok(new AuthResponse(token, usr.getId(), usr.getFirstname(),usr.getLastname(), usr.getUsername(), usr.getEmail(), usr.getProfilePicture(), usr.getStatus()));
	}

	
	@GetMapping("/getall")
	public List<userEntity> getall(){
		return service.getallUser();
	}
}
