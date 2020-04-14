package com.web.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.app.entity.Dentiste;


public interface DentisteRepository extends JpaRepository<Dentiste,Integer> {

	
	@Query("select a from Dentiste a where a.user_Dentiste = :user_Dentiste and a.password_Dentiste= :password_Dentiste")
	public Dentiste loginDentiste (@Param("user_Dentiste")String user_Dentiste ,@Param("password_Dentiste")String password_Dentiste);

}
