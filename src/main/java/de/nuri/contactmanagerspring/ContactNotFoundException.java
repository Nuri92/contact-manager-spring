package de.nuri.contactmanagerspring;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContactNotFoundException extends RuntimeException {
	public ContactNotFoundException(int id) {
		super("Kontakt mit ID " + id + " wurde nicht gefunden.");
	}
}
