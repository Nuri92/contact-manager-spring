package de.nuri.contactmanagerspring;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "contacts")
public class Contact {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int     id;
	@NotBlank(message = "Name darf nicht leer sein")
	private String  name;
	
	@NotBlank(message = "E-Mail darf nicht leer sein")
	@Email(message = "Ungültige E-Mail-Adresse")
	private String  email;
	
	@Pattern(regexp = "^[0-9]+$", message = "Telefonnummer darf nur Zahlen enthalten")
	private String  phoneNumber;
	private boolean favorite;
	
	public Contact() {
	}
	
	public Contact(int id, String name, String email, String phoneNumber, boolean favorite) {
		this.id          = id;
		this.name        = name;
		this.email       = email;
		this.phoneNumber = phoneNumber;
		this.favorite    = favorite;
	}
	
	public Contact(String name, String email, String phoneNumber) {
		this.name        = name;
		this.email       = email;
		this.phoneNumber = phoneNumber;
		this.favorite    = false;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public boolean isFavorite() {
		return favorite;
	}
	
	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}
	
	public void update(String name, String email, String phoneNumber) {
		this.name        = name;
		this.email       = email;
		this.phoneNumber = phoneNumber;
	}
}
