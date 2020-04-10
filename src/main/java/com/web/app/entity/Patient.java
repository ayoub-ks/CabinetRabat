package com.web.app.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Patient {
	@Id
	@GeneratedValue
	private Integer Id_Patient ;
	@Column(name="user_Patient")
	private String userP;
	private String Name_Patient;
	private String Lastname_Patient;
	@Column(name="password_Patient")
    private String passwordP;
    private String email_Patient;
    private int age_Patient ;
    
    @OneToMany(mappedBy="patient")
    private List<Appointment> appointments;
    
    
    
	public Patient() {
		super();
	}
	
	public Patient(String user_Patient, String password_Patient ) {
		super();
		this.userP = user_Patient;
		this.passwordP = password_Patient;
	}
	
	public Patient(String user_Patient, String name_Patient, String lastname_Patient, String password_Patient,
			String email_Patient, int age_Patient) {
		super();
		this.userP = user_Patient;
		this.Name_Patient = name_Patient;
		this.setLastname_Patient(lastname_Patient);
		this.passwordP = password_Patient;
		this.email_Patient = email_Patient;
		this.age_Patient = age_Patient;
	}

	public Integer getId_Patient() {
		return Id_Patient;
	}
	public void setId_Patient(Integer id_Patient) {
		Id_Patient = id_Patient;
	}
	public String getUser_Patient() {
		return userP;
	}
	public void setUser_Patient(String user_Patient) {
		this.userP = user_Patient;
	}
	public String getPassword_Patient() {
		return passwordP;
	}
	public void setPassword_Patient(String password_Patient) {
		this.passwordP = password_Patient;
	}

	public String getEmail_Patient() {
		return email_Patient;
	}

	public void setEmail_Patient(String email_Patient) {
		this.email_Patient = email_Patient;
	}

	public int getAge_Patient() {
		return age_Patient;
	}

	public void setAge_Patient(int age_Patient) {
		this.age_Patient = age_Patient;
	}

	public String getName_Patient() {
		return Name_Patient;
	}

	public void setName_Patient(String name_Patient) {
		Name_Patient = name_Patient;
	}

	public String getLastname_Patient() {
		return Lastname_Patient;
	}

	public void setLastname_Patient(String lastname_Patient) {
		Lastname_Patient = lastname_Patient;
	}

	
    
    
}
