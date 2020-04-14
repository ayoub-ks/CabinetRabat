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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.web.app.Repository.AppointmentRepository;
import com.web.app.Repository.DentisteRepository;
import com.web.app.entity.Appointment;
import com.web.app.entity.Dentiste;


@Controller
@SessionAttributes({"Dentiste"})
public class DentisteController {
	
	@Autowired
	DentisteRepository den;
	@Autowired
	AppointmentRepository appo;
	
	@ModelAttribute("Dentiste")
	public Dentiste Dentiste() {
		return new Dentiste();
	}
	
	
	@PostMapping("/loginDentiste")
    public String logindentiste(Model model,@RequestParam String userD,@RequestParam String passwordD) {
		
		if(den.loginDentiste(userD, passwordD)!=null) {
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.now();
			String ddtf = dtf.format(localDate);
			
			
			List<Appointment> sched=appo.findAppointmentByDate(ddtf);
			model.addAttribute("appNb",sched.size());
			model.addAttribute("Dentiste",den.loginDentiste(userD, passwordD));
			return "/dentisteHome";
		}else
			
		return "forward:/home";
		
	}
	
	@RequestMapping("/dentistelist")
	
	public String afficherlist(Model model) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		String ddtf = dtf.format(localDate);
		
		List<Appointment> sched=appo.findAppointmentByDate(ddtf);
		
		model.addAttribute("appointments",sched);
		
		return "/dentistlist";
				
	}
	
	@RequestMapping("/dentistelistR")
	
	public String afficherlist2(Model model,@RequestParam(value="tt") String tt) {
		
		if (tt.equals("1")) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		String ddtf = dtf.format(localDate);
		
		List<Appointment> sched=appo.findAppointmentByDate(ddtf);
		
		model.addAttribute("appointments",sched);
		
		System.out.println(tt);
		
		return "/dentistlist"; }
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
		       
		       
		       
		       return "/dentistlist";
		      }
		      else {if (tt.equals("3")) {
		    	   
		    	  
		    	  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM");
		  		  LocalDate localDate = LocalDate.now();
		  		  String ddtf = dtf.format(localDate);
		  		  
		  		  List<Appointment> sched=appo.findAppointmentByMonth(ddtf);
		  		  
		  	      model.addAttribute("appointments", sched);
		    	  
		    	  return "/dentistlist";
		          }else {
		        	  
		        	  
		          return "/dentistlist";
		          }
		      }
		}		
	}
}
