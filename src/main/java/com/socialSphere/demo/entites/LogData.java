package com.socialSphere.demo.entites;

public class LogData {
	
	private String username;
	
	private String password;
	
	public LogData() {
		super();
	}

	public LogData(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LogData [username=" + username + ", password=" + password + "]";
	}
	
	

}
