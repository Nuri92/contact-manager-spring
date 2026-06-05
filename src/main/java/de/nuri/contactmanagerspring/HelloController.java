package de.nuri.contactmanagerspring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public String greeting() {
		return "Hello Spring Boot";
	}
}
