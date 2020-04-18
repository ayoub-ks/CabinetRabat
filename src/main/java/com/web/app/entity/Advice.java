package com.web.app.entity;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Advice {
	@Id
	@GeneratedValue
	private Integer id_adv;
	@Column(name="Advice")
	private String adv;
	@Column(name="DateAdvice")
	private String advD;
	
	
	
	
	@ManyToOne
	private Patient patient;
	
	@OneToMany(mappedBy="advice")
	private List<Answer> answer;
	
	
	public Integer getId_adv() {
		return id_adv;
	}
	public void setId_adv(Integer id_adv) {
		this.id_adv = id_adv;
	}
	public String getAdv() {
		return adv;
	}
	public void setAdv(String adv) {
		this.adv = adv;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	
	
	
	
	
	
	
	public String getAdvD() {
		return advD;
	}
	public void setAdvD(String advD) {
		this.advD = advD;
	}
	public Advice() {
		super();
	}
	public Advice(String adv,Patient patient) {
		super();
		this.adv = adv;
		this.patient = patient;
	}
	public Advice(String adv, String advD, Patient patient) {
		super();
		this.adv = adv;
		this.advD = advD;
		this.patient = patient;
	}
	
	
	
	
	

}
