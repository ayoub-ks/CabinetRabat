package com.web.app.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentDEO {
	
	@Autowired
	AppointmentRepository appointmentRepository;

}
