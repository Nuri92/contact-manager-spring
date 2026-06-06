package de.nuri.contactmanagerspring;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ContactMemoryRepository implements ContactRepository {
	@Override
	public void save(Contact contact) {
	
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
		return null;
	}
	
	@Override
	public void delete(Contact contact) {
	
	}
	
	@Override
	public void update(Contact contact) {
	
	}
}
