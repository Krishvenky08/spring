package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.exceptions.NoSuchUserException;
import com.capgemini.jwt.JwtUtil;
import com.capgemini.jwt.MyUserDetailsService;
import com.capgemini.jwt.Request;
import com.capgemini.jwt.Response;
import com.capgemini.model.UserModel;
import com.capgemini.service.UserService;

@RestController
@RequestMapping(path = "user")
@CrossOrigin
public class UserController {
	@Autowired
	private UserService service;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	// http://localhost:9091/student-api/user/login.do
	@PostMapping(path = "login.do")
	public ResponseEntity<?> authenticateUserAndGenerateToken(@RequestBody Request request) throws Exception {
		try {
			System.out.println("Inside authenticateUserAndGenerateToken method before calling authenticate method");
			authManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			System.out.println("Inside authenticateUserAndGenerateToken method after calling authenticate method");
			final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

			// if credential are correct will perform following step or else
			// BadCredentialException will be raised by authenticate method
			final String jwt = jwtTokenUtil.generateToken(userDetails);
			return ResponseEntity.ok(new Response(jwt));

		} 
		catch (BadCredentialsException e) {
			throw new NoSuchUserException("Invalid username or password");
		}
	}

	// http://localhost:9091/student-api/user
	@PostMapping
	public ResponseEntity<?> addUser(@RequestBody UserModel user) {
		service.addUser(user);
		return ResponseEntity.ok("User added.");
	}

	// http://localhost:9091/student-api/user
	@GetMapping
	public ResponseEntity<?> getAllUser() {
		List<UserModel> list = service.findAll();
		return ResponseEntity.ok(list);
	}

	// http://localhost:9091/student-api/user/email
	@GetMapping("{email}")
	public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email) {
		UserModel user = service.findUserByEmail(email);
		return ResponseEntity.ok(user);
	}

}