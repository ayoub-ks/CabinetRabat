package com.web.app.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.web.app.Repository.DayDEO;
import com.web.app.Repository.PatientDEO;
import com.web.app.Repository.PeriodDEO;
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
		 	
			newper=per.findFreePeriodInDay(hh.getDayID());
			
		}

		model.addAttribute("day", hh);
		model.addAttribute("periods", newper);

		return "patientreservatien";
	}
	
	
	

	
	@ResponseBody
	@RequestMapping("/reserver")
    public String DEL(Model model,@RequestParam Integer id) {
		
		Period red=per.findPeriodById(id);
		 red.setEtat(true);
		 per.savePeriod(red) ;   
		 
		return "period reserved";
			
		
		
	}
	
	
	
	
	
	
	
	
	@RequestMapping("/patientlogout")
	public String patientlogout(SessionStatus status) {
		
		status.setComplete();
		return "redirect:home"; 
		
	}

}
