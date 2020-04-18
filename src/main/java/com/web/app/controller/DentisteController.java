package com.web.app.controller;

import java.time.DayOfWeek;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.web.app.Repository.AdviceDEO;
import com.web.app.Repository.AnswerDEO;
import com.web.app.Repository.AppointmentRepository;

import com.web.app.Repository.DentisteDEO;
import com.web.app.Repository.DentisteRepository;
import com.web.app.Repository.PatientDEO;
import com.web.app.entity.Advice;
import com.web.app.entity.Answer;
import com.web.app.entity.Appointment;
import com.web.app.entity.Dentiste;
import com.web.app.entity.Patient;


@Controller
@SessionAttributes({"Dentiste"})
public class DentisteController {
	
	@Autowired
	DentisteRepository den;
	
	@Autowired
	AppointmentRepository appo;
	
	@Autowired
	DentisteDEO dentistDEO;
	
	@Autowired
	PatientDEO patientDEO;
	
	@Autowired
	AdviceDEO adviceDEO;
	
	@Autowired
	AnswerDEO answerDEO;
	
	@ModelAttribute("patient")
	public Patient patient() {
		return new Patient();
	}

	
	@ModelAttribute("Dentiste")
	public Dentiste Dentiste() {
		return new Dentiste();
	}
	
	@ModelAttribute("advice")
	public Advice advice() {
		return new Advice();
	}
	
	
	@PostMapping("/loginDentist")
	public String loginDentist(Model model, @RequestParam String email, @RequestParam String password) {

		if (dentistDEO.logindentist(email, password) != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.now();
			String ddtf = dtf.format(localDate);
			
			
			List<Appointment> sched=appo.findAppointmentByDate(ddtf);
			model.addAttribute("appNb",sched.size());
			model.addAttribute("Dentiste", dentistDEO.logindentist(email, password));
			return "forward:/dentisthomee";
		} else

			return "forward:/home";
	}
	
	//Home page for dentist
	@RequestMapping("/dentisthomee")
	public String dentisthome(Model model) {

		return "dentisthome";

	}
	
	//Page Patients list
	@RequestMapping("/AllPat")
	public String pageallpatients() {
		return "listpatients";
	}
	
	//Patients list
	@RequestMapping(value="/AllPat", method=RequestMethod.GET)
	public String allPatients(Model model) {
		model.addAttribute("listpatients", this.patientDEO.findallPatients());
		return "listpatients";
	}
	
	//delete patient method 
	@RequestMapping(value="/DeletePatient", method=RequestMethod.GET)
	public String deletepatient(Model model, @RequestParam Integer id) {
		this.patientDEO.deletePatient(id);
		model.addAttribute("listpatients", this.patientDEO.findallPatients());
		return "listpatients";
	}
	
	
	
	
	//Logout from dentist page
	@RequestMapping("/dentistlogout")
	public String dentistlogout(SessionStatus status) {
		
		status.setComplete();
		return "redirect:home"; 
	}
	
	
	//go back to home
	@RequestMapping("/back")
	public String Back() {
		return "dentisthome";
	}
	
	
	
	//Appointments page for dentist
	@RequestMapping(value="/AppList",method=RequestMethod.GET)
	public String afficherlist(Model model) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		String ddtf = dtf.format(localDate);
		
		List<Appointment> sched=appo.findAppointmentByDate(ddtf);
		
		model.addAttribute("appointments",sched);
		
		return "dentistlistapointments";
				
	}
	
	
	
	@RequestMapping(value="/dentistelistR",method=RequestMethod.GET)
	public String afficherlist2(Model model,@RequestParam(value="tt") String tt) {
		
		if (tt.equals("1")) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		String ddtf = dtf.format(localDate);
		
		List<Appointment> sched=appo.findAppointmentByDate(ddtf);
		
		model.addAttribute("appointments",sched);
		
		System.out.println(tt);
		
		return "dentistlistapointments"; }
		else { if (tt.equals("2")) {
			
			
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM");
			  LocalDate localDate = LocalDate.now();
	  		  String ddtf = dtf.format(localDate);
	  		  
	  		  List<Appointment> sched=appo.findAppointmentByMonth(ddtf);
			  List<Appointment> schedd=new ArrayList<Appointment>();
		       
		       
			   WeekFields weekFields = WeekFields.of(Locale.getDefault());
		       
		       LocalDate monday = localDate;
		       while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
		         monday = monday.minusDays(1);
		       }
		       LocalDate sunday = localDate;
		       while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
		         sunday = sunday.plusDays(1);
		       }
		         
		       String mondayR=monday.toString();
               String sundayR=sunday.toString();
		       
               for(Appointment app:sched) {
            	   if((app.getPeriod().getDayID().getDate().compareTo(mondayR)>=0)&&(app.getPeriod().getDayID().getDate().compareTo(sundayR)<0)) 
            		   schedd.add(app);
            	   
            	   
               }
		 
		  		  
		  	      model.addAttribute("appointments", schedd);
		       
		       
		       
		       return "dentistlistapointments";
		      }
		      else {if (tt.equals("3")) {
		    	   
		    	  
		    	  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM");
		  		  LocalDate localDate = LocalDate.now();
		  		  String ddtf = dtf.format(localDate);
		  		  
		  		  List<Appointment> sched=appo.findAppointmentByMonth(ddtf);
		  		  
		  	      model.addAttribute("appointments", sched);
		    	  
		    	  return "dentistlistapointments";
		          }else {
		        	  
		        	  
		          return "dentistlistapointments";
		          }
		      }
		}		
	}
	
	//go back to homee
		@RequestMapping("/back1")
		public String Back1(Model model) {
			model.addAttribute("listadvices", this.adviceDEO.findAllAdvices());
			model.addAttribute("listanswer", this.answerDEO.findAllAnswer());
			return "dentistadvices";
		}
	
	//go to advice page for dentist user
	@RequestMapping(value="/dentistadv")
	public String advpage() {
		return "dentistadvices";
	}
	
	//show to dentist an advice list
	@RequestMapping(value="/dentistadv", method=RequestMethod.GET)
	public String listadvice(Model model) {
		model.addAttribute("listadvices", this.adviceDEO.findAllAdvices());
		model.addAttribute("listanswer", this.answerDEO.findAllAnswer());
		return "dentistadvices";
	}
	
	//go to next page to response
	@RequestMapping(value="/answeradv")
	public String advanswerpage(Model model,@RequestParam Integer id) {
		model.addAttribute("advice", this.adviceDEO.findAdviceById(id));
		return "dentistansweradvices";
	}
	
	//update advice, add answer to it
	@RequestMapping(value="/updateadv",method=RequestMethod.POST)
	public String sendresponsetopatient(Model model,Answer ans) {
		model.addAttribute("advice");
		this.answerDEO.saveAnswer(ans);
		model.addAttribute("listanswer", this.answerDEO.findAllAnswer());
		return "dentistansweradvices";
	}
	
	//delete an advice
		@RequestMapping(value="/deleteAdvd",method=RequestMethod.GET)
		public String deleteadv(Model model,@RequestParam Integer id,@ModelAttribute("patient") Patient patient) {
			this.adviceDEO.deleteAdvice(id);
			model.addAttribute("listadvices", this.adviceDEO.findAllAdvices());
			return "dentistadvices";
			
		}
		//delete an answer
		@RequestMapping(value="/deleteAnswer",method=RequestMethod.GET)
		public String deleteans(Model model,@RequestParam Integer id,@ModelAttribute("patient") Patient patient) {
			this.answerDEO.deleteAnswer(id);
			model.addAttribute("listadvices", this.adviceDEO.findAllAdvices());
			model.addAttribute("listanswer", this.answerDEO.findAllAnswer());
			return "dentistadvices";
			
		}
	
}
