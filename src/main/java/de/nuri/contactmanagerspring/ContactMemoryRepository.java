package de.nuri.contactmanagerspring;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ContactMemoryRepository implements ContactRepository {
	
	private final List<Contact> contacts = new ArrayList<>();
	private       int           nextId   = 1;
	
	public ContactMemoryRepository() {
		save(new Contact("Nuri", "nuri@hotmail", "017634299343"));
		save(new Contact("Max", "max@test.de", "0123456789"));
	}
	
	@Override
	public Contact save(Contact contact) {
		Contact contactToSave = new Contact(
				nextId,
				contact.getName(),
				contact.getEmail(),
				contact.getPhoneNumber(),
				contact.isFavorite()
		);
		
		contacts.add(contactToSave);
		nextId++;
		
		return contactToSave;
	}
	
	@Override
	public List<Contact> findAll() {
		return contacts;
	}
	
	@Override
	public Contact findById(int id) {
		for (Contact contact : contacts) {
			if (contact.getId() == id) {
				return contact;
			}
		}
		
		return null;
	}
	
	@Override
	public void deleteById(int id) {
		for (Contact contact : contacts) {
			if (contact.getId() == id) {
				contacts.remove(contact);
				return;
			}
		}
	}
	
	@Override
	public Contact update(int id, Contact contact) {
		for (Contact currentContact : contacts) {
			if (currentContact.getId() == id) {
				currentContact.update(contact.getName(), contact.getEmail(), contact.getPhoneNumber());
				return currentContact;
			}
		}
		return null;
	}
}