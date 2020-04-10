package com.web.app.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.web.app.Repository.AppointmentDEO;
import com.web.app.Repository.DayDEO;
import com.web.app.Repository.PatientDEO;
import com.web.app.Repository.PeriodDEO;
import com.web.app.entity.Appointment;
import com.web.app.entity.Day;
import com.web.app.entity.Patient;
import com.web.app.entity.Period;






@Controller
@SessionAttributes({"patient"})
public class PatientController {
	
	@Autowired
	PatientDEO pat;
	
	@Autowired
	DayDEO dayR;
	
	@Autowired
	PeriodDEO per;
	
	@Autowired
	AppointmentDEO appo;
	
	@ModelAttribute("patient")
	public Patient patient() {
		return new Patient();
	}
	
	
	
	
	
	@PostMapping("/loginPatient")
    public String loginPatient(Model model,@RequestParam String userP,@RequestParam String passwordP) {
		
		if(pat.loginPatient(userP, passwordP)!=null) {
			model.addAttribute("patient",pat.loginPatient(userP, passwordP));
			return "forward:/patienthomee";
		}else
			
		return "forward:/home";
		
	}
	
	
	
	@RequestMapping("/patienthomee")
	public String patienthome(Model model) {
		
		return "patienthome"; 
		
	}
	
	
	
	
	@GetMapping("/patientreservation")
	public String patientreservation(Model model) {
		Day day=new Day();
		List<Period> newper=new ArrayList<Period>();
		model.addAttribute("periods", newper);
		model.addAttribute("day",day);
		return "patientreservatien"; 
		
	}

	@RequestMapping("/rechercherDay")
	public String recherche(Model model, @RequestParam(value = "date") String date) {
        
		
		Day hh = dayR.FindDayByDate(date);
		List<Period> newper=new ArrayList<Period>();
		
		if (hh == null) {
			
			Day newDay = new Day();
			
			newDay.setDate(date);
			
			
			
			newDay.setPeriods(Arrays.asList(new Period("From 8:00 To 9:00"),new Period("From 9:00 To 10:00"),new Period("From 10:00 To 11:00"),new Period("From 11:00 To 12:00")));
			dayR.saveDay(newDay);
			hh=newDay;
			newper=hh.getPeriods();
		}else {
		 	
			newper=per.findFreePeriodInDay(hh);
			
		}

		model.addAttribute("day", hh);
		model.addAttribute("periods", newper);

		return "patientreservatien";
	}
	
	
	

	
	
	@PostMapping("/reserver")
    public String DEL(Model model,@ModelAttribute("patient") Patient patient,@RequestParam String traitment, @RequestParam Integer periodid ) {
		
		
		Appointment newAPP=new Appointment();
		
		newAPP.setPatient(patient);
		
		Period period=per.findPeriodById(periodid);
		period.setEtat(true);
	
		newAPP.setPeriod(period);
		
		newAPP.setTraitment(traitment);
		
		appo.saveAppointment(newAPP);
	
		
		
	    model.addAttribute("appointment",newAPP);
		return "reservSucces";
			
		
		
	}
	
	//Get a Profile page for patientS
	@RequestMapping("/profilepatient")
	public String profilepatient(Model model) {
		return "patientprofile";
	}
	@GetMapping("/patientreservationList")
	public String patientreservationList(Model model,@ModelAttribute("patient") Patient patient) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		String ddtf=dtf.format(localDate);
		
		List<Appointment> sched = appo.findAppointmentByPatient(patient);
		List<Appointment> com=new ArrayList<Appointment>();
		List<Appointment> old=new ArrayList<Appointment>();
		
		
		for(Appointment appo : sched) {
			 int comp=ddtf.compareTo(appo.getPeriod().getDayID().getDate());
			if (comp<=0) {
				com.add(appo);
			}
			else {
				old.add(appo);
			}	
		}
		
		model.addAttribute("Newappointments", com);
		model.addAttribute("Oldappointments", old);
		

		return "patientListRes"; 
		
	}
	
	
	

	
	@RequestMapping("/patientlogout")
	public String patientlogout(SessionStatus status) {
		
		status.setComplete();
		return "redirect:home"; 
		
	}
	


}
