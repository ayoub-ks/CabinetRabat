package com.web.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.app.entity.Day;

public interface DayRepository extends JpaRepository<Day,Integer>{
	
	@Query("select a from Day a where a.date = :date ")
	public Day FindDayByDate(@Param("date")String date);

}
