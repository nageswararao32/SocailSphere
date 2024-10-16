package com.socialSphere.demo.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.socialSphere.demo.entites.LogData;
import com.socialSphere.demo.entites.User;

@CrossOrigin("*")
@RestController
public class DemoController {
	
	@GetMapping("/hello")
	
	public String sayHello() {
		
		System.out.println("Hello React");
		
		return "This is response from SpringBott";
	}
	
	@PostMapping("/postData")
	
	public String recevingData(@RequestBody User user) {
		
		System.out.println(user);
		
		return "Data Posted into DataBase Successfully!";
		
	}
	
	
}
