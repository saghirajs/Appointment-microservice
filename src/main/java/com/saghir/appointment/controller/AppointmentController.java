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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saghir.appointment.entities.Appointment;
import com.saghir.appointment.exception.ResourceNotFoundException;
import com.saghir.appointment.repository.AppointmentRepository;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentRepository appointmentRepository;

	// create get all appointments api.
	@GetMapping("/getAllAppointments")
	public List<Appointment> getAllAppointments() {
		return appointmentRepository.findAll();
	}

	// create appointment
	@PostMapping("/addAppointment")
	public Appointment addAppointment(@RequestBody Appointment appointment) {
		return appointmentRepository.save(appointment);
	}

	// get appointment by id
	@GetMapping("/appointments/{id}")
	public ResponseEntity<Appointment> getAppointmentById(@PathVariable("id") Long id) throws ResourceNotFoundException {
		Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Appointment not found for this id : " + id));
		return ResponseEntity.ok().body(appointment);
	}

	// update appointment
	@PutMapping("/updateAppointment/{id}")
	public ResponseEntity<Appointment> updateAppointment(@PathVariable(value = "id") Long id, @RequestBody Appointment appointmentDetails) throws ResourceNotFoundException {
		Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Appointment not found for this id : " + id));

		appointment.setDateAppointement(appointmentDetails.getDateAppointement());
		appointment.setState(appointmentDetails.getState());
		
		final Appointment updatedappointment = appointmentRepository.save(appointment);
		return ResponseEntity.ok(updatedappointment);
	}

	// delete appointment
	@DeleteMapping("/deleteAppointment/{id}")
	public Map<String, Boolean> deleteAppointment(@PathVariable(value = "id") Long id)	throws ResourceNotFoundException {
		Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Appointment not found for this id : " + id));

		appointmentRepository.delete(appointment);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	// create filter by state appointments api.
	@GetMapping("/getAllAppointments/sortByState/{state}")
	public List<Appointment> sortByState(@PathVariable(value = "state") String state) {
		return appointmentRepository.retrieveAllAppointmentByState(state);
	}
	
}
