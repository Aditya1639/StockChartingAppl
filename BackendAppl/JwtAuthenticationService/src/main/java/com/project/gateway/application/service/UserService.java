package com.project.gateway.application.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.project.gateway.application.dto.UserDto;

public interface UserService extends UserDetailsService
{
	public UserDetails loadUserByUsername(String username);
	public UserDto signup(UserDto userDto);
}
