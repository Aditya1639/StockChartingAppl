package com.project.gateway.application.controller;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.gateway.application.config.JwtTokenUtil;
import com.project.gateway.application.dto.SigninRequest;
import com.project.gateway.application.dto.UserDto;
import com.project.gateway.application.exception.UserAlreadyExistsException;
import com.project.gateway.application.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
public class UserController 
{
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserService userService;
	
	@PostMapping(path = "/login")
	public ResponseEntity<?> login(@RequestBody SigninRequest signinRequest)
	{
		final UserDetails userDetails = userService.loadUserByUsername(signinRequest.getUsername());
		if(userDetails == null || !passwordEncoder.matches(signinRequest.getPassword(), userDetails.getPassword())) {
			throw new UsernameNotFoundException("User not found");
		}
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(Collections.singletonMap("token", token));
	}
	
	@PostMapping(path = "/signup")
	public ResponseEntity<?> signup(@RequestBody UserDto userDto)
			throws UserAlreadyExistsException
	{
		userDto = userService.signup(userDto);
		if(userDto == null) {
			throw new UserAlreadyExistsException("User already exists with this credentials");
		}
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(userDto);
	}
	
}
