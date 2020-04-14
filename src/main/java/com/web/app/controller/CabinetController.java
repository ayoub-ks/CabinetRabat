package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.web.app.Repository.PatientDEO;
import com.web.app.entity.Patient;



@Controller

public class CabinetController {
	@Autowired
	PatientDEO pat;
	
	
		@ModelAttribute("patient")
	public Patient patient() {
		return new Patient();
	}
	
	
	
	@RequestMapping("/home")
	public String go(Model model) {
		
		Patient patient = new Patient();
		model.addAttribute("patient", patient);
		
		return "login"; 
		
	}
	@RequestMapping("/register")
	public String register(Model model) {
		Patient patient = new Patient();
		model.addAttribute("patient", patient);
		
		return "register2"; 
		
	}

	
	
	@PostMapping("/RegisterPatient")
    public String goo(Patient patient ) {
		pat.savePatient(patient);
		return "login";
		
	}

}
