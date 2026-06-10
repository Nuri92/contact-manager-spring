package de.nuri.contactmanagerspring;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
	
	private final JpaContactRepository repository;
	
	public ContactService(JpaContactRepository repository) {
		this.repository = repository;
	}
	
	public List<Contact> getContacts() {
		return repository.findAll();
	}
	
	public Contact getContactById(int id) {
		return repository.findById(id)
		                 .orElseThrow(() -> new ContactNotFoundException(id));
	}
	
	public Contact addContact(Contact contact) {
		contact.setFavorite(false);
		return repository.save(contact);
	}
	
	public void deleteContact(int id) {
		Contact contact = getContactById(id);
		repository.delete(contact);
	}
	
	public Contact updateContact(int id, Contact contact) {
		Contact existingContact = getContactById(id);
		
		existingContact.update(
				contact.getName(),
				contact.getEmail(),
				contact.getPhoneNumber()
		);
		
		return repository.save(existingContact);
	}
	
	public Contact toggleFavorite(int id) {
		Contact contact = getContactById(id);
		
		contact.setFavorite(!contact.isFavorite());
		
		return repository.save(contact);
	}
}