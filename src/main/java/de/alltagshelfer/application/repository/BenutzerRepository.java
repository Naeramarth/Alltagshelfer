package de.alltagshelfer.application.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import de.alltagshelfer.application.model.Benutzer;

public interface BenutzerRepository extends CrudRepository<Benutzer, Long> {

	public Optional<Benutzer> findByBenutzername(String benutzername);

}
