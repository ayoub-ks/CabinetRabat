package com.web.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Dentiste {
    @Id
    @GeneratedValue
	private Integer Id_Dentiste ;
	private String user_Dentiste;
    private String password_Dentiste;
    
    
    
	public Dentiste() {
		super();
	}


	public Dentiste(String user_Dentiste, String password_Dentiste) {
		super();
		this.user_Dentiste = user_Dentiste;
		this.password_Dentiste = password_Dentiste;
	}
	
	
	public Integer getId_Dentiste() {
		return Id_Dentiste;
	}
	public void setId_Dentiste(Integer id_Dentiste) {
		Id_Dentiste = id_Dentiste;
	}
	public String getUser_Dentiste() {
		return user_Dentiste;
	}
	public void setUser_Dentiste(String user_Dentiste) {
		this.user_Dentiste = user_Dentiste;
	}
	public String getPassword_Dentiste() {
		return password_Dentiste;
	}
	public void setPassword_Dentiste(String password_Dentiste) {
		this.password_Dentiste = password_Dentiste;
	}
    
    
}
