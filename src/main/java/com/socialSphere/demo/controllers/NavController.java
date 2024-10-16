package com.socialSphere.demo.controllers;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.socialSphere.demo.entites.Post;
import com.socialSphere.demo.entites.User;
import com.socialSphere.demo.services.PostService;
import com.socialSphere.demo.services.UserService;

import jakarta.servlet.http.HttpSession;



@Controller
public class NavController {
	
	@Autowired
	
	private UserService service;
	
	@Autowired 
	
	private PostService postService;
	
	@GetMapping("/")
	
	public String homePage(Model model) {
		
		model.addAttribute("page","index");
		
		return "index";
	}
	
	@GetMapping("/openSignUpPage")
	
	public String openSignUpPage(Model model) {
		
		model.addAttribute("page", "openSignUpPage");
		
		return "signup";
	}
		
	@GetMapping("/createNewPost")
	
	public String createPost(Model model, HttpSession session) {
		
		String username = (String)session.getAttribute("Username");
		
		User user = service.getUserDetails(username);
		
		model.addAttribute("user", user);
		
		model.addAttribute("page","createNewPost");
		
		if(username != null) {
		
		return "createNewPost";
		
		}else {
			return "index";
		}
	}
		
		
	@GetMapping("/editProfile")
	
	public String myProfile(Model model, HttpSession session) {
		
		String username = (String) session.getAttribute("Username");
		
		User user  = service.getUserDetails(username);
		
		List<Post> allPosts = user.getPosts();
		
		model.addAttribute("userPosts",allPosts);
		
		model.addAttribute("user",user);
		
		model.addAttribute("page","editProfile");
		if(session.getAttribute("Username") != null) {
			return "myProfile";
		}else {
		return "index";
		}
	}	
	@GetMapping("/openEditProfile")
	
	public String openEditProfile(HttpSession session, Model model) {
		
		String username = (String) session.getAttribute("Username");
		
		User user  = service.getUserDetails(username);
		
		model.addAttribute("user",user);
		
		model.addAttribute("page","openEditProfile");
		if(session.getAttribute("Username") != null) {
			return "EditProfile";
		}else {
		return "index";
		}
	}
	
	@GetMapping("/home")
	public String home(HttpSession session, Model model) {
		
		List<Post>allPosts = postService.getPosts();
		
		model.addAttribute("allPosts", allPosts);
		
		model.addAttribute("page","home");
		
		if(session.getAttribute("Username") != null) {
			return "home";
		}
		else {
		return "index";
		}
	}
	
	@PostMapping("/visitProfile")
	
	public String visitProfile(@RequestParam String profileName, Model model) {
		
		User user = service.getUserDetails(profileName);
		
		model.addAttribute("user", user);
		
		List<Post>myPosts = user.getPosts();
		
		model.addAttribute("myPosts",myPosts);
		
		return "showUserProfile";
		
	}
	
	@GetMapping("/openResetPassword")
	
	public String resetPwd() {
		return "resetPwd";
	}
	
	@GetMapping("/sendResetPwdLink")
	
	public String sendUrl() {
		return "forgotPassword";
	}
	
	@GetMapping("/successMessage")
	
	public String sendUrlSuccess() {
		return "success";
	}
	
	
}
