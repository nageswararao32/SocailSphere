package com.socialSphere.demo.controllers;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.socialSphere.demo.entites.LogData;
import com.socialSphere.demo.entites.Post;
import com.socialSphere.demo.entites.User;
import com.socialSphere.demo.services.PostService;
import com.socialSphere.demo.services.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin("*")
@RestController
public class UserController {

	@Autowired
	
	private UserService service;
	
	@Autowired
	
	private PostService postService;
		
	@PostMapping("/signUp")

	public String addUser(@RequestBody User user){
		
		String username = user.getUsername();
		
		String email = user.getEmail();
		
		boolean status = service.userExists(username, email);
		
		if(status == false) {
			
			service.addUser(user);
			
			return "User Added";
		}else {
			
			return "User Exists";
			
		}
		
	}
	
	@PostMapping("/login")
	
	public String login(@RequestBody LogData data, Model model, HttpSession session) {
	
		String username = data.getUsername();
		
		String password = data.getPassword();
		
		String status = service.validateUser(username, password);
		
		if(status.equals("true")) {
			
			session.setAttribute("Username", username);
			
			model.addAttribute("session",session);
			
			List<Post> allPosts = postService.getPosts();
			
			model.addAttribute("allPosts", allPosts);
			
			model.addAttribute("page", "home");
			
			return "Login Success";
			
		}else if(status.equals("User Not Found")){
			
			return "Login Fail and User Not Found";
		}else {
			
			return "Invalid Credentials";
		}
	}
	
	@PostMapping("/updateProfile")
	public String updateProfile(@RequestParam String Username,@RequestParam String username, @RequestParam String email, @RequestParam String password,@RequestParam String dob,@RequestParam String gender,
			@RequestParam String currentCity, @RequestParam String bio,
			@RequestParam String college, @RequestParam String linkdIn,
			@RequestParam String gitHub, @RequestParam MultipartFile profilePic
			, HttpSession session,
			Model model) {
				
		//fetch user object using Username
		User user = service.getUserDetails(Username);
		//update and save object
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		user.setDob(dob);
		user.setGender(gender);
		user.setCurrentCity(currentCity);
		user.setBio(bio);
		user.setCollege(college);
		user.setLinkdIn(linkdIn);
		user.setGitHub(gitHub);
		try {						
			user.setProfilePic(profilePic.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.updateUser(user);
		
		model.addAttribute("user", user);
		
		return "Profile Updated";
	}
	
	@PostMapping("/getUserDetails")
	
	public User getUserDetails(@RequestBody LogData data) {
		
		String username = data.getUsername();
		
		User user = service.getUserDetails(username);
		
		return user;
		
	}
	
	@GetMapping("/getVisitorProfileDetails")
	
	public User getDetails(@RequestParam String username) {
		
		User user = service.getUserDetails(username);
		
		return user;
		
	}
	
	@PostMapping("/updatePassword/{email}/{password}")
	
	public String updatePassword(@PathVariable String email, @PathVariable String password) {
		
		User user = service.findUserByEmail(email);
		
		System.out.println(email+" "+password);
		
		if(user != null) {
			
			user.setPassword(password);
			
			service.updateUser(user);
			
			return "Password Updated Successfully";
		}else {
			return "User Not In our DataBase.";
		}
	}
	
	@GetMapping("/logout")
	
	public String logout(HttpSession session) {

		session.invalidate();
		
		return "index";
	}
		
}
