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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.web.app.Repository.AdviceDEO;
import com.web.app.Repository.AnswerDEO;
import com.web.app.Repository.AppointmentDEO;
import com.web.app.Repository.DayDEO;
import com.web.app.Repository.PatientDEO;
import com.web.app.Repository.PeriodDEO;
import com.web.app.entity.Advice;
import com.web.app.entity.Answer;
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
	
	@Autowired
	AdviceDEO adviceDEO;
	
	@Autowired
	AnswerDEO answerDEO;
	
	
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
 
		Day hh = new Day();
		List<Period> newper = new ArrayList<Period>();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		String ddtf = dtf.format(localDate);

		int comp = ddtf.compareTo(date);

		if (comp <= 0) {

			    hh = dayR.FindDayByDate(date);
		      	

		    	if (hh == null) {

		   		    Day newDay = new Day();

			     	newDay.setDate(date);

			    	newDay.setPeriods(Arrays.asList(new Period("From 8:00 To 9:00"), new Period("From 9:00 To 10:00"),
						new Period("From 10:00 To 11:00"), new Period("From 11:00 To 12:00")));
		     		dayR.saveDay(newDay);
		    		hh = newDay;
			    	newper = hh.getPeriods();
			    } 
		       else {

				newper = per.findFreePeriodInDay(hh);

			      }

	     
		} 
		
		else {
			hh.setDate(date);
		}
		model.addAttribute("periods", newper);
		model.addAttribute("day", hh);

		return "patientreservatien";
	}
	
	
	

	
	
	@PostMapping("/reserver")
    public String DEL(Model model,@ModelAttribute("patient") Patient patient,@RequestParam String traitment, @RequestParam Integer periodid ) {
		
		Appointment newAPP=new Appointment();
		Period period=per.findPeriodById(periodid);
		
		newAPP.setPatient(patient);
		
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
	
	

	@RequestMapping("/DeletReservation")
	public String DeletReservation(@RequestParam Integer id) {
		
		Appointment deleted=appo.findAppointmentById(id);
		
		Period period=deleted.getPeriod();
		period.setEtat(false);
	
		appo.deleteDay(deleted);
		
		per.savePeriod(period);
		
		return "redirect:patientreservationList"; 
		
	}
	

	//logout patient
	@RequestMapping("/patientlogout")
	public String patientlogout(SessionStatus status) {
		
		status.setComplete();
		return "redirect:home"; 
		
	}
	
	//Advice page for patient
	@RequestMapping("/advicepage")
	public String advicepage(Model model,@ModelAttribute("patient") Patient patient) {
		model.addAttribute("AllAdv", this.adviceDEO.findAdviceByPatient(patient));
		return "patientadvices";
	}
	
	//Save an advice
	@RequestMapping(value="/sendadvice",method=RequestMethod.POST)
	public String sendadv(Model model,Advice adv,@ModelAttribute("patient") Patient patient ) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		String ddtf=dtf.format(localDate);
		adv.setAdvD(ddtf);
		
		model.addAttribute("sendadv", this.adviceDEO.saveAdvice(adv));
		model.addAttribute("AllAdv", this.adviceDEO.findAdviceByPatient(patient));
		return "patientadvices";
	}
	
	//delete an advice
	@RequestMapping(value="/deleteAdv",method=RequestMethod.GET)
	public String deleteadv(Model model,@RequestParam Integer id,@ModelAttribute("patient") Patient patient) {
		this.adviceDEO.deleteAdvice(id);
		model.addAttribute("AllAdv", this.adviceDEO.findAdviceByPatient(patient));
		return "patientadvices";
		
	}
	
	
	//go to answer list for patient
	@RequestMapping(value="/pageanswer", method=RequestMethod.GET)
	public String answerlist(Model model,@ModelAttribute("patient") Patient patient) {
		List<Advice> adv=this.adviceDEO.findAdviceByPatient(patient);
		List<Answer> ans=this.answerDEO.findAllAnswer();
		List<Answer> ab= new ArrayList<Answer>();
		for(Advice a:adv) {
			for(Answer b:ans) {
				if(a.getId_adv().equals(b.getAdvice().getId_adv())) {
						ab.add(b);	
				}
			}
		}
		model.addAttribute("listanswer", ab);
		return "patientansweradvices";
	}
	
	//delete answer with its advice
	@RequestMapping(value="/deleteanswer")
	public String deleteans(Model model,@RequestParam Integer id,@ModelAttribute("patient") Patient patient) {
		this.answerDEO.deleteAnswer(id);
		
		List<Advice> adv=this.adviceDEO.findAdviceByPatient(patient);
		List<Answer> ans=this.answerDEO.findAllAnswer();
		List<Answer> ab= new ArrayList<Answer>();
		for(Advice a:adv) {
			for(Answer b:ans) {
				if(a.getId_adv().equals(b.getAdvice().getId_adv())) {
						ab.add(b);	
				}
			}
		}
		model.addAttribute("listanswer", ab);
		return "patientansweradvices";
	}


}
