package com.web.app.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.entity.Advice;
import com.web.app.entity.Answer;

@Service
public class AnswerDEO {
	
	@Autowired
	AnswerRepository answerRepository;
	
	public Answer saveAnswer(Answer ans) {	
		return answerRepository.save(ans);
	}
	
	public List<Answer> findAllAnswer(){
		return answerRepository.findAll();
	}
	
	public void deleteAnswer(Integer id) {
		answerRepository.deleteById(id);
	}
	
	public Answer findAnswerById(Integer id) {
		return answerRepository.getOne(id);
	}
	public List<Answer> findAnswerByAdvice(Advice advice){
		return answerRepository.findAnswerByAdvice(advice);
	}
	

}
