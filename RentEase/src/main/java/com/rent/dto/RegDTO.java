package com.rent.dto;

import com.rent.entities.Role;

public class RegDTO {
	private String firstName;
	private String lastName;
	private String gender;
	private String email;
	private String password;
	private String phone;
	private Role role;
	public RegDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RegDTO(String firstName, String lastName, String gender, String email, String password, String phone,
			Role role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.password = password;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
		return "RegDTO [firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", email=" + email
				+ ", password=" + password + ", phone=" + phone + ", role=" + role + "]";
	}
	
	
	
}
