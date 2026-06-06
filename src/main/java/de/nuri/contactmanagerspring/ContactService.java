package de.nuri.contactmanagerspring;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
	
	private final ContactRepository repository;
	
	public ContactService(ContactRepository contactRepository) {
		this.repository = contactRepository;
	}
	
	public List<Contact> getContacts() {
		return repository.findAll();
	}
	
	public Contact getContactById(int id) {
		return repository.findById(id);
	}
}
