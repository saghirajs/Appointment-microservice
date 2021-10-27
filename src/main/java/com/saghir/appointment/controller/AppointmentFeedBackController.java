package com.saghir.appointment.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.saghir.appointment.entities.AppointmentFeedBack;
import com.saghir.appointment.repository.AppointmentFeedBackRepository;

@RestController
public class AppointmentFeedBackController {

	@Autowired
	private AppointmentFeedBackRepository appointmentFeedBackRepository;

	// create get all feedbacks api.
	@GetMapping("/getAllFeedBacks")
	public List<AppointmentFeedBack> getAllFeedBacks() {
		return appointmentFeedBackRepository.findAll();
	}

	// create feedBack
	@PostMapping("/addFeedBack")
	public AppointmentFeedBack addFeedBack(@RequestBody AppointmentFeedBack appointmentFeedBack) {
		return appointmentFeedBackRepository.save(appointmentFeedBack);
	}

	// get feedback by id
	@GetMapping("/findfeedback/{id}")
	public ResponseEntity<AppointmentFeedBack> getFeedBackById(@PathVariable("id") Long id){
		AppointmentFeedBack appointmentFeedBack = appointmentFeedBackRepository.findById(id).get();
		return ResponseEntity.ok().body(appointmentFeedBack);
	}

	// delete feedback
	@DeleteMapping("/deleteFeedBack/{id}")
	public Map<String, Boolean> deleteFeedBack(@PathVariable(value = "id") Long id){
		AppointmentFeedBack appointmentFeedBack = appointmentFeedBackRepository.findById(id).get();

		appointmentFeedBackRepository.delete(appointmentFeedBack);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	// create filter by likes appointments api.
	@GetMapping("/getFeedbacks/filterByLikes")
	public List<AppointmentFeedBack> filterBylikes() {
		return appointmentFeedBackRepository.retrieveAllFeedBacksByLikes();
	}

}
