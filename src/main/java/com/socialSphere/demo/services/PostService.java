package com.socialSphere.demo.services;

import java.util.List;

import com.socialSphere.demo.entites.Post;

public interface PostService {

	void createPost(Post post);

	List<Post> getPosts();

	Post getPost(Long id);

	void updatePost(Post p);

}
