package com.socialSphere.demo.entites;

import java.util.Base64;

public class PostData {
	
	private String username;
	
	private String caption;
	
	private byte[] post;
	
	public String getPhotoBase64() {
		
		if(post == null) {
			return null;
		}
		return Base64.getEncoder().encodeToString(post);
	}
	
	public PostData() {
		
		super();
	}	

}
