package de.nuri.contactmanagerspring;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ContactMemoryRepository implements ContactRepository {
	private final List<Contact> contacts = new ArrayList<>();
	private       int           nextId   = 1;
	
	@Override
	public Contact save(Contact contact) {
		Contact contactToSave =
				new Contact(nextId, contact.getName(), contact.getEmail(), contact.getPhoneNumber(), contact.isFavorite());
		contacts.add(contactToSave);
		nextId++;
		return contactToSave;
	}
	
	@Override
	public List<Contact> findAll() {
		List<Contact> contacts = new ArrayList<>();
		Contact       contact  = new Contact(1, "Nuri", "nuri@hotmail", "017634299343", false);
		contacts.add(contact);
		Contact secondContact = new Contact(2, "Nuri", "nuri@hotmail", "017634299343", false);
		contacts.add(secondContact);
		return contacts;
	}
	
	@Override
	public Contact findById(int id) {
		for (Contact contact : findAll()) {
			if (contact.getId() == id) {
				return contact;
			}
		}
		return null;
	}
	
	@Override
	public void delete(Contact contact) {
	
	}
	
	@Override
	public void update(Contact contact) {
	
	}
}
