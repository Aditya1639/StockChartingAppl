package com.project.gateway.application.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.gateway.application.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>
{
	public User findByUsername(String username);
	public User findByUsernameOrEmail(String username, String email);
	public User findByConfirmationToken(String token);
}
