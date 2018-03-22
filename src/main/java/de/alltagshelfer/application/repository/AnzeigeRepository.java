package de.alltagshelfer.application.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.alltagshelfer.application.entity.Anzeige;
import de.alltagshelfer.application.entity.Benutzer;
import de.alltagshelfer.application.entity.Kategorie;

public interface AnzeigeRepository extends CrudRepository<Anzeige, Long> {

	public List<Anzeige> findByTitelContaining(String titel);

	public List<Anzeige> findByTitelContainingAndKategorie(String titel, Kategorie kategorie);

	public List<Anzeige> findByTitelContainingAndKategorieAndBenutzer(String text, Kategorie kategorie,
			Benutzer benutzer);

	public List<Anzeige> findByTitelContainingAndBenutzer(String text, Benutzer benutzer);
}
