package com.project.gateway.application.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.project.gateway.application.model.User;
import com.project.gateway.application.repository.UserRepository;

@Component
public class UserServiceApplicationInitializer implements CommandLineRunner
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception 
	{
		userRepository.deleteAll();
		User admin = new User("admin", passwordEncoder.encode("password"), "tejas@gmail.com", "9999999999");
		admin.setRole("ADMIN");
		admin.setConfirmed(true);
		userRepository.save(admin);
	}
}
