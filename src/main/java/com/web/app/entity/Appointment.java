package com.web.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Appointment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer IdAppointment;
	
	
	private String traitment ;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Period period;
	
	@ManyToOne
	private Patient patient;
	

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Integer getIdAppointment() {
		return IdAppointment;
	}

	public void setIdAppointment(Integer idAppointment) {
		IdAppointment = idAppointment;
	}

	public String getTraitment() {
		return traitment;
	}

	public void setTraitment(String traitment) {
		this.traitment = traitment;
	}
	

}
