package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.web.app.Repository.PatientDEO;
import com.web.app.entity.Patient;



@Controller
@SessionAttributes({"patient"})
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
	@RequestMapping("/patientlogout")
	public String patientlogout(Model model) {
		
		Patient patient = new Patient();
		model.addAttribute("patient", patient);
		return "login"; 
		
	}
	
	@GetMapping("/patientreservation")
	public String patientreservation(Model model) {
		
		return "patientreservatien"; 
		
	}

	@RequestMapping("/patienthomee")
	public String patienthome(Model model) {
		
		return "patienthome"; 
		
	}
	
	
	
	@PostMapping("/loginPatient")
    public String loginPatient(Model model,@RequestParam String userP,@RequestParam String passwordP) {
		
		if(pat.loginPatient(userP, passwordP)!=null) {
			model.addAttribute("patient",pat.loginPatient(userP, passwordP));
			return "forward:/patienthomee";
		}else
			
		return "forward:/home";
		
	}
	
	
	@ResponseBody
	@PostMapping("/RegisterPatient")
    public String goo(Patient patient ) {
		pat.savePatient(patient);
		return "saved";
		
	}

}
