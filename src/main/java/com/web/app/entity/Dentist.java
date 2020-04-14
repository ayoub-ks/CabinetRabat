package com.web.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Dentist {
	@Id
	@GeneratedValue
	@Column(name="id_dentist")
	private Integer id;
	@Column(name="firstname_dentist")
	private String firstname;
	@Column(name="lastname_dentist")
	private String lastname;
	@Column(name="email_dentist")
	private String email;
	@Column(name="password_dentist")
	private String password;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
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
	public Dentist(String firstname, String lastname, String email, String password) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}
	public Dentist() {
		super();
	}
	
	
	
	

}
