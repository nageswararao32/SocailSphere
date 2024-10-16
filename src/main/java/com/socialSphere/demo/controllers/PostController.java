package com.socialSphere.demo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.socialSphere.demo.entites.Post;
import com.socialSphere.demo.entites.User;
import com.socialSphere.demo.services.PostService;
import com.socialSphere.demo.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;

@CrossOrigin("*")
@RestController
public class PostController {

	@Autowired

	private PostService service;

	@Autowired

	private UserService userService;

	@PostMapping("/createPost")

	public String createPost(@RequestParam("username") String username,
            @RequestParam("caption") String caption,
            @RequestParam("post") MultipartFile file,
			Model model, HttpSession session) {
		
		User user = userService.getUserDetails(username);

		Post post = new Post();

		// Updating Post with the corresponding user's name

		post.setUser(user);

		post.setCaption(caption);

		try {
			post.setPhoto(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		service.createPost(post);

		// Updating User when user post a post then printing the posts in user's home

		List<Post> posts = user.getPosts();

		if (posts == null) {
			posts = new ArrayList<Post>();
		}

		posts.add(post);

		user.setPosts(posts);
		
		userService.updateUser(user);

		List<Post> allPosts = user.getPosts();

		model.addAttribute("allPosts", allPosts);
		
		model.addAttribute("user",user);
		
		return "A new post is Created Successfully!";
	}

	@GetMapping("/getPosts/{username}")

	public List<Post> showPosts(@PathVariable String username) {
			
		User user = userService.getUserDetails(username);
		
		List<Post> allPosts = user.getPosts();

		return allPosts;
	}
	
	@GetMapping("/getPosts")
	
	public List<Post> getPosts() {
		
		List<Post>posts = service.getPosts();
		
		return posts;
	}
	
	
	@GetMapping("/getVisitorPosts")
	
	public List<Post>getVisitorPosts(@RequestParam String username){
		
		User user = userService.getUserDetails(username);
		
		List<Post>posts = user.getPosts();
		
		return posts;
	}
	
	
	@GetMapping("/likePost/{id}/{likedProfileName}")

	public String likePost(@PathVariable Long id, @PathVariable String likedProfileName) {
		
		Post p = service.getPost(id);
				
		p.setLikes(p.getLikes() + 1);
		
		service.updatePost(p);
		
		User user = p.getUser();
		
		System.out.println("Hello "+user.getUsername()+" "+likedProfileName+" like your Post!");
				
		return "Hello "+user.getUsername()+" "+likedProfileName+" like your Post!";

	}

	@PostMapping("/addComment/{id}/{commentedProfileName}/{comment}")

	public String addComment(@PathVariable Long id, @PathVariable String commentedProfileName, @PathVariable String comment) {
		
		Post p = service.getPost(id);

		List<String> comments = p.getComments();

		if (comments == null) {

			comments = new ArrayList<String>();
		}

		comments.add(comment);

		p.setComments(comments);

		service.updatePost(p);
		
		User user = p.getUser();
		
		System.out.println("Hello "+user.getUsername()+" "+commentedProfileName+" Commented your Post! and the comment is: "+comment);
		
		return "Hello "+user.getUsername()+" "+commentedProfileName+" Commented your Post!";
		
	}

}
