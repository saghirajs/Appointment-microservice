package com.saghir.appointment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.saghir.appointment.entities.AppointmentFeedBack;

@Repository
public interface AppointmentFeedBackRepository extends JpaRepository<AppointmentFeedBack, Long> {
	@Query("SELECT a FROM AppointmentFeedBack a WHERE likes=1 ")
	List<AppointmentFeedBack> retrieveAllFeedBacksByLikes();
}

