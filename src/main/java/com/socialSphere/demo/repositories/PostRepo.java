package com.socialSphere.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialSphere.demo.entites.Post;



public interface PostRepo extends JpaRepository<Post, Long>{

}
