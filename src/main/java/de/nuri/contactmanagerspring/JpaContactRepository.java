package de.nuri.contactmanagerspring;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaContactRepository extends JpaRepository<Contact, Integer> {
}
