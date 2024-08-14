package com.rent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class UserResponseDTO extends BaseDTO {
	
	
	@JsonProperty(access = Access.READ_ONLY)
	private String firstName;

	@JsonProperty(access = Access.READ_ONLY)
	private String lastName;
	
	@JsonProperty(access = Access.READ_ONLY)
	private String email;
	
	@JsonProperty(access = Access.READ_ONLY)
	private String phone;

	public UserResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserResponseDTO(String firstName, String lastName, String email, String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
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

	@Override
	public String toString() {
		return "UserResponseDTO [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phone="
				+ phone + "]";
	}
	
	

}
