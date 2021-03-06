package com.web.app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.app.entity.Appointment;
import com.web.app.entity.Patient;


public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
	
	@Query("select a from Appointment a where a.patient = :patient ")
	public List<Appointment> findAppointmentByPatient(@Param("patient")Patient patient);
	
	
	@Query("select a from Appointment a where a.period.dayID.date = :date ")
	public List<Appointment> findAppointmentByDate(@Param("date")String date);
	
	
	@Query("select a from Appointment a where a.period.dayID.date LIKE :date% ")
	public List<Appointment> findAppointmentByMonth(@Param("date")String date);
	
}
