package com.socialSphere.demo.entites;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long id;
	
	private String caption;
	
	private int likes;
	
	private List<String> comment;
	
	@ManyToOne
	
	private User user;
	
	@Lob
	
	@Basic(fetch = FetchType.LAZY)
	
	@Column(columnDefinition = "LONGBLOB")
	
	private byte[] photo;
	
	public String getPhotoBase64() {
		
		if(photo == null) {
			return null;
		}
		return Base64.getEncoder().encodeToString(photo);
	}
	
	public Post() {
		
		super();
	}

	public Post(Long id, String caption, int likes, List<String> comment, User user, byte[] photo) {
		super();
		this.id = id;
		this.caption = caption;
		this.likes = likes;
		this.comment = comment;
		this.user = user;
		this.photo = photo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public List<String> getComments() {
		return comment;
	}

	public void setComments(List<String> comment) {
		this.comment = comment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", caption=" + caption + ", likes=" + likes + ", comments=" + comment  + "]";
	}

		
}
