package com.web.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.app.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

}
