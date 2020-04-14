package com.web.app.Repository;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.web.app.entity.Appointment;
import com.web.app.entity.Patient;


@Service
public class AppointmentDEO {
	
	@Autowired
	AppointmentRepository appointmentRepository;
	
	
	public Appointment saveAppointment(Appointment dnt) {	
		return appointmentRepository.save(dnt);
	}

	
	public List<Appointment> findallAppointments(){
		return appointmentRepository.findAll() ;
	}
	
	public Appointment findAppointmentById(Integer id ) {
		return appointmentRepository.getOne(id);
	}
	
	public void deleteDay(Appointment dnt ) {
		
		appointmentRepository.delete(dnt);
	}
	
	public List<Appointment> findAppointmentByPatient(Patient patient){
		return appointmentRepository.findAppointmentByPatient(patient);
	}

	public List<Appointment> findAppointmentByDate(String date){
		return appointmentRepository.findAppointmentByDate(date);
	}

	
	
	public List<Appointment> findAppointmentByMonth(String date){
		return appointmentRepository.findAppointmentByMonth(date);
	}
}
