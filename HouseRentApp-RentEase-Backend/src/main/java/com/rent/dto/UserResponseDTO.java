package com.rent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.rent.entities.Role;

public class UserResponseDTO extends BaseDTO {
	
	
	@JsonProperty(access = Access.READ_ONLY)
	private String firstName;

	@JsonProperty(access = Access.READ_ONLY)
	private String lastName;
	
	@JsonProperty(access = Access.READ_ONLY)
	private String email;
	
	@JsonProperty(access = Access.READ_ONLY)
	private String phone;
	
	@JsonProperty(access = Access.READ_ONLY)
	private Role role;
	

	public UserResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UserResponseDTO(String firstName, String lastName, String email, String phone, Role role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.role = role;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "UserResponseDTO [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phone="
				+ phone + ", role=" + role + "]";
	}

	
	
	

}
