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
		Contact contact = repository.findById(id);
		if (contact == null) {
			throw new ContactNotFoundException(id);
		}
		return contact;
	}
	
	public Contact addContact(Contact contact) {
		return repository.save(contact);
	}
	
	public void deleteContact(int id) {
	 	Contact contact = repository.findById(id);
	 	if (contact == null) {
	 		throw new ContactNotFoundException(id);
	    }
	 	repository.deleteById(id);
	}
}
