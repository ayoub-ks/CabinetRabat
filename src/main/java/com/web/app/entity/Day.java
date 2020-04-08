package com.web.app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Day {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer dayID;
	
	private String date;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="dayID" ,referencedColumnName="dayID")
	private List<Period> periods;

	
	
	public List<Period> getPeriods() {
		return periods;
	}

	public void setPeriods(List<Period> periods) {
		this.periods = periods;
	}

	public Integer getDayID() {
		return dayID;
	}

	public void setDayID(Integer dayID) {
		this.dayID = dayID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	
	public Day() {
		
	}

	
	
}
