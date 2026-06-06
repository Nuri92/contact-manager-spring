package de.nuri.contactmanagerspring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {
	private final ContactService contactService;
	
	public HelloController(ContactService contactService) {
		this.contactService = contactService;
	}
	
	@GetMapping("/hello")
	public String greeting() {
		return "Hello Spring Boot";
	}
	
	@GetMapping("/contacts")
	public List<Contact> getContact() {
		return contactService.getContacts();
	}
}
