package com.web.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.app.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient,Integer>{
	
	@Query("select a from Patient a where a.userP = :userP and a.passwordP= :passwordP")
	public Patient loginPatient (@Param("userP")String userP ,@Param("passwordP")String passwordP);

}
