package com.web.app.controller;



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

import com.web.app.Repository.AppointmentDEO;
import com.web.app.Repository.DentistDEO;
import com.web.app.Repository.PatientDEO;
import com.web.app.entity.Dentist;
import com.web.app.entity.Patient;

@Controller
@SessionAttributes({ "dentist" })
public class DentistController {

	@Autowired
	DentistDEO dentistDEO;

	@Autowired
	PatientDEO patientDEO;
	
	@Autowired
	AppointmentDEO aapointmentDEO;

	@ModelAttribute("patient")
	public Patient patient() {
		return new Patient();
	}

	@ModelAttribute("dentist")
	public Dentist dentist() {
		return new Dentist();
	}
	


	@PostMapping("/loginDentist")
	public String loginDentist(Model model, @RequestParam String email, @RequestParam String password) {

		if (dentistDEO.logindentist(email, password) != null) {
			model.addAttribute("dentist", dentistDEO.logindentist(email, password));
			return "forward:/dentisthomee";
		} else

			return "forward:/home";
	}

	@RequestMapping("/dentisthomee")
	public String dentisthome(Model model) {

		return "dentisthome";

	}

	@RequestMapping("/AllPat")
	public String pageallpatients() {
		return "listpatients";
	}
	

	@RequestMapping(value="/AllPat", method=RequestMethod.GET)
	public String allPatients(Model model) {
		model.addAttribute("listpatients", this.patientDEO.findallPatients());
		return "listpatients";
	}
	
	
	@RequestMapping(value="/DeletePatient", method=RequestMethod.GET)
	public String deletepatient(Model model, @RequestParam Integer id) {
		this.patientDEO.deletePatient(id);
		model.addAttribute("listpatients", this.patientDEO.findallPatients());
		return "listpatients";
	}
	
	@RequestMapping(value="/Appointements")
	public String pageappoinetment() {
		return "Appointments";
	}
	

	
	@RequestMapping("/dentistlogout")
	public String dentistlogout(SessionStatus status) {
		
		status.setComplete();
		return "redirect:home"; 
	}
	
	@RequestMapping("/back")
	public String Back() {
		return "dentisthome";
	}


}
