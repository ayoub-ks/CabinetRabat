package com.web.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.app.entity.Dentist;

public interface DentistRepository extends JpaRepository<Dentist, Integer> {
	
	public Dentist findByEmailAndPassword(String email, String password);

}
