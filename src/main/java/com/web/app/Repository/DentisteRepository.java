package com.web.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;



import com.web.app.entity.Dentiste;


public interface DentisteRepository extends JpaRepository<Dentiste,Integer> {

	
	public Dentiste findByEmailAndPassword(String email, String password);

}
