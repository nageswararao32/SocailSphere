package com.socialSphere.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialSphere.demo.entites.Post;
import com.socialSphere.demo.repositories.PostRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	
	private PostRepo repo;

	@Override
	public void createPost(Post post) {
		
		repo.save(post);
	}

	@Override
	public List<Post> getPosts() {
		
		return repo.findAll();
	}

	@Override
	public Post getPost(Long id) {
		
		 Optional<Post> postOptional = repo.findById(id);
		    return postOptional.orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + id));
		}

	@Override
	public void updatePost(Post p) {
		
		repo.save(p);
	}

}
