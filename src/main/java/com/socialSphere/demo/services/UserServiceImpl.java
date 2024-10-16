package com.socialSphere.demo.services;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialSphere.demo.entites.User;
import com.socialSphere.demo.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;



@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	
	private UserRepository repo;
	
	@Override
	public boolean userExists(String username, String email) {
		
		Optional<User> u1 = repo.findByEmail(email);
		
		User u2 = repo.findByUsername(username);
		
		if(u1 != null || u2 != null) {
			
			return true;
		}
		
		return false;
	}

	@Override
	public void addUser(User user) {
		
		repo.save(user);
	}

	@Override
	public String validateUser(String username, String password) {
		
		User u = repo.findByUsername(username);
		
		if(u == null) {
			
			return "User Not Found";
			
		}
		
		if(username.equals(u.getUsername()) && password.equals(u.getPassword())) {
			
			return "true";
		}
		
		return "false";
	}

	@Override
	public User getUserDetails(String username) {
		
		User user = repo.findByUsername(username);
		
		return user;
	}

	@Override
	public void updateUser(User user) {
		
		repo.save(user);
	}

	@Override
	public User findUserByEmail(String email) {
		
		Optional<User>user = repo.findByEmail(email);
		
		return user.orElseThrow(()->new EntityNotFoundException("User not Found in our DataBase"));
	}

}
