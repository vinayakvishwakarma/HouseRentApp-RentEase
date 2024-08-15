package com.rent.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity extends BaseEntity {
	@Column(length=30,name="first_name",nullable = false)
	private String firstName;
	@Column(length=30,name="last_name",nullable = false)
	private String lastName;
	@Column(length=30,name="gender",nullable = false)
	private String gender;
	@Column(length=30,name="email", unique=true)
	private String email;
	@Column(length=30,name="password",nullable = false)
	private String password;
	@Column(length=10,name="phone",nullable = false)
	private String phone;
	@Enumerated(EnumType.STRING)
	@Column(name="role",nullable = false)
	private Role role;
	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserEntity(String firstName, String lastName, String gender, String email, String password, String phone,
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
		return "UserEntity [firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", email="
				+ email + ", password=" + password + ", phone=" + phone + ", role=" + role + "]";
	}
	
	
	
	
	
	
	

}
