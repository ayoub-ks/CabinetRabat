package com.web.app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.app.entity.Advice;
import com.web.app.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
	@Query("select a from Answer a where a.advice =: advice")
	public List<Answer> findAnswerByAdvice(@Param("advice")Advice advice);
	
}
