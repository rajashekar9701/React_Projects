package com.persistent.employeeportal.entity;



import java.io.Serializable;

public class LoginDto implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	
	private String email;
	private String password;
	
	//default constructor for JSON Parsing
	public LoginDto()
	{
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}