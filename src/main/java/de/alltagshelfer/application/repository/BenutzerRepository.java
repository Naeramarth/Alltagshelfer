package de.alltagshelfer.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import de.alltagshelfer.application.entity.Benutzer;

public interface BenutzerRepository extends CrudRepository<Benutzer, Long> {

	public Optional<Benutzer> findByBenutzername(String benutzername);

	@Modifying
	@Transactional
	public void deleteByBenutzername(String benutzername);

}
