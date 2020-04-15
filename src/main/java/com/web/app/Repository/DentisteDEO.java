package com.web.app.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.entity.Dentiste;

@Service
public class DentisteDEO {
	@Autowired
	DentisteRepository dentisteRepository;
	
	public Dentiste saveDentiste(Dentiste dnt) {
		return dentisteRepository.save(dnt);
	}

	
	public List<Dentiste> findallDentistes(){
		return dentisteRepository.findAll() ;
	}
	
	public Dentiste findDentisteById(Integer id ) {
		return dentisteRepository.getOne(id);
	}
	
	public void deleteDentiste(Dentiste dnt ) {
		
		dentisteRepository.delete(dnt);
	}
	
	
	
	public Dentiste logindentist(String email, String password) {
		 return dentisteRepository.findByEmailAndPassword(email, password);
		
	}
	
}
