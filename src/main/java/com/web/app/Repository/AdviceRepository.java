package com.web.app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.app.entity.Advice;

import com.web.app.entity.Patient;

public interface AdviceRepository extends JpaRepository<Advice, Integer> {
	@Query("select a from Advice a where a.patient = :patient ")
	public List<Advice> findAdviceByPatient(@Param("patient")Patient patient);


}
