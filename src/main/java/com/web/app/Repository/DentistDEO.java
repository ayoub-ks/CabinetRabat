package com.web.app.Repository;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.entity.Dentist;

@Service
public class DentistDEO {
	
	@Autowired
	DentistRepository dentistRepository;
	
	public Dentist logindentist(String email, String password) {
		 return dentistRepository.findByEmailAndPassword(email, password);
		
	}
	
	
	

}
