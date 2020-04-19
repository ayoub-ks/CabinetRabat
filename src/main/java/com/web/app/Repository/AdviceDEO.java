package com.web.app.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.entity.Advice;
import com.web.app.entity.Patient;

@Service
public class AdviceDEO {
	
	@Autowired
	AdviceRepository adviceRepository;
	
	public Advice saveAdvice(Advice adv) {	
		return adviceRepository.save(adv);
	}
	
	public List<Advice> findAllAdvices(){
		return adviceRepository.findAll();
	}
	
	public void deleteAdvice(Integer id) {
		adviceRepository.deleteById(id);
	}
	
	public List<Advice> findAdviceByPatient(Patient patient){
		return adviceRepository.findAdviceByPatient(patient);
	}
	
	public Advice findAdviceById(Integer id ) {
		return adviceRepository.getOne(id);
	}
	
	


}
