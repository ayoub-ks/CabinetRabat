package com.web.app.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.entity.Day;


@Service
public class DayDEO  {

	@Autowired
	DayRepository dayRepository;
	
	
	public Day saveDay(Day dnt) {	
		return dayRepository.save(dnt);
	}
    
	
	
	public List<Day> findallDays(){
		return dayRepository.findAll() ;
	}
	
	public Day findDayById(Integer id ) {
		return dayRepository.getOne(id);
	}
	
	public void deleteDay(Day dnt ) {
		
		dayRepository.delete(dnt);
	}
	
	public Day FindDayByDate(String date) {
		
		return dayRepository.FindDayByDate(date);
	}

	
}
