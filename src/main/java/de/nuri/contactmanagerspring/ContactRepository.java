package de.nuri.contactmanagerspring;

import java.util.List;

public interface ContactRepository {
	Contact save(Contact contact);
	
	List<Contact> findAll();
	
	Contact findById(int id);
	
	void deleteById(int id);
	
	Contact update(int id, Contact contact);
}
