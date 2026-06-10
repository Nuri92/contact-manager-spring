package de.nuri.contactmanagerspring;

import org.springframework.web.bind.annotation.*;

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
	
	@GetMapping("/contacts/{id}")
	public Contact getContactById(@PathVariable int id) {
		return contactService.getContactById(id);
	}
	
	@PostMapping("/contacts")
	public Contact addContact(@RequestBody Contact contact) {
		return contactService.addContact(contact);
	}
	
	@DeleteMapping("/contacts/{id}")
	public void deleteContact(@PathVariable int id) {
		contactService.deleteContact(id);
	}
	
	@PutMapping("/contacts/{id}")
	public Contact updateContact(@PathVariable int  id, @RequestBody Contact contact) {
		return contactService.updateContact(id, contact);
	}
	
	@PatchMapping("/contacts/{id}/favorite")
	public Contact toggleFavorite(@PathVariable int id) {
		return contactService.toggleFavorite(id);
	}
}
