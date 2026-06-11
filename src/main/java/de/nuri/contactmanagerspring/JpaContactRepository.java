package de.nuri.contactmanagerspring;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaContactRepository extends JpaRepository<Contact, Integer> {
	List<Contact> findAllByOrderByIdAsc();
}
