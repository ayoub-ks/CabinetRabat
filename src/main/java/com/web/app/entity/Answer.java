package com.web.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Answer {
	@Id
	@GeneratedValue
	private Integer id_answer;
	@Column(name="answer")
	private String ans;
	@ManyToOne
	private Advice advice;
	
	
	public Integer getId_answer() {
		return id_answer;
	}
	public void setId_answer(Integer id_answer) {
		this.id_answer = id_answer;
	}
	public String getAns() {
		return ans;
	}
	public void setAns(String ans) {
		this.ans = ans;
	}
	public Advice getAdvice() {
		return advice;
	}
	public void setAdvice(Advice advice) {
		this.advice = advice;
	}
	
	public Answer(String ans, Advice advice) {
		super();
		this.ans = ans;
		this.advice = advice;
	}
	public Answer() {
		super();
	}
	
	

}
