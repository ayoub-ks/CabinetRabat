package com.web.app.entity;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;




@Entity
public class Period {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer periodID ;
	
	private String time;
	
	private boolean etat ;
	
	@ManyToOne
	@JoinColumn(name="dayid")
	private Day dayID;
	
	
	
	
	public Period(String time) {
		super();
		this.time = time;
	}

	public Period() {
		super();
	}

	public Integer getPeriodID() {
		return periodID;
	}

	public void setPeriodID(String hh) {
		this.time = hh;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	public Day getDayID() {
		return dayID;
	}

	public void setDayID(Day dayID) {
		this.dayID = dayID;
	}



	

}
