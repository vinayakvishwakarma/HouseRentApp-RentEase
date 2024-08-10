package com.rent.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class AuthDTO {

	@NotEmpty(message = "Email can't be blank")
	@Email(message = "Invalid email format")
	private String email;
	
	@NotEmpty(message = "Password can't be blank")
	@Length(min = 3,max=20,message = "Invalid password length")
	private String password;
	public AuthDTO() {
		super();
		// TODO Auto-generated constructor stub
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
	@Override
	public String toString() {
		return "AuthDTO [email=" + email + ", password=" + password + "]";
	}
	
	
}
