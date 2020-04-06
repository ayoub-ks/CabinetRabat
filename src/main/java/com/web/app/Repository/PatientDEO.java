package com.web.app.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.entity.Patient;

@Service
public class PatientDEO {
	@Autowired
 	PatientRepository patientRepository ;
	
	public Patient savePatient(Patient dnt) {	
		return patientRepository.save(dnt);
	}

	
	public List<Patient> findallPatients(){
		return patientRepository.findAll() ;
	}
	
	public Patient findPatientById(Integer id ) {
		return patientRepository.getOne(id);
	}
	
	public void deletePatient(Patient dnt ) {
		
		patientRepository.delete(dnt);
	}
	
	public Patient loginPatient (String userP,String passwordP) {
		
		return patientRepository.loginPatient(userP, passwordP);
		
	}
}
