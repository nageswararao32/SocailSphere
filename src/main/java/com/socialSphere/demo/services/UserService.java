package com.socialSphere.demo.services;

import com.socialSphere.demo.entites.User;

public interface UserService {

	boolean userExists(String username, String email);

	void addUser(User user);

	String validateUser(String username, String password);

	User getUserDetails(String username);

	void updateUser(User user);

	User findUserByEmail(String email);

}
