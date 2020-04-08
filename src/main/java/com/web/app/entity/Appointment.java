package com.web.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Appointment {
	
	@Id
	@GeneratedValue
	private Integer IdAppointment;
	
	
	private String traitment ;
	
	@OneToOne
	private Period period;
	
	@ManyToOne
	private Patient patient;
	

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
