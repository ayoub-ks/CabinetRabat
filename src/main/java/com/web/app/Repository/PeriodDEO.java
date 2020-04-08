package com.web.app.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.web.app.entity.Period;

@Service
public class PeriodDEO {

	@Autowired
	PeriodRepository periodRepository;
	
	
	public Period savePeriod(Period dnt) {	
		return periodRepository.save(dnt);
	}

	
	public List<Period> findallPeriods(){
		return periodRepository.findAll() ;
	}
	
	public Period findPeriodById(Integer id ) {
		return periodRepository.getOne(id);
	}
	
	public void deleteDay(Period dnt ) {
		
		periodRepository.delete(dnt);
	}
	
	public List<Period> findFreePeriodInDay(Integer date){
		return periodRepository.findFreePeriodInDay(date);
	}
}
