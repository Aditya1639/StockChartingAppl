package com.project.gateway.application.service.impl;

import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.gateway.application.dto.UserDto;
import com.project.gateway.application.mapper.UserMapper;
import com.project.gateway.application.model.User;
import com.project.gateway.application.repository.UserRepository;
import com.project.gateway.application.service.UserService;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		User user = userRepository.findByUsername(username);
		if(user == null || !user.isConfirmed())
			return null;
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(),
				user.getPassword(),
				Arrays.asList(new SimpleGrantedAuthority(user.getRole())));
	}
	
	@Override
	public UserDto signup(UserDto userDto)  
	{
		if(userRepository.findByUsernameOrEmail(userDto.getUsername(), userDto.getEmail())!=null)
			return null;
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		userDto.setRole("USER");
		userDto.setConfirmationToken(UUID.randomUUID().toString());
		userDto.setConfirmed(false);
		User user = userRepository.save(userMapper.toUser(userDto));		
		return userMapper.toUserDto(user);
	}

}
