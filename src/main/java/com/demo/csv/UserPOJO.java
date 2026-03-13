package com.demo.csv;

public class UserPOJO {
	private String username;
	
	public UserPOJO() {
		
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

	@Override
	public String toString() {
		return "UserPOJO [username=" + username + ", password=" + password + "]";
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String password;

	public UserPOJO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

}
