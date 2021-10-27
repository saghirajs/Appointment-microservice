package com.saghir.appointment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class AppointmentApplication {
	
	@RequestMapping(value = "/")
	public String index() {
		return "<h1>Hello world</h1>";
	}

	public static void main(String[] args) {
		SpringApplication.run(AppointmentApplication.class, args);
	}

}
