package de.alltagshelfer.application.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import de.alltagshelfer.application.entity.Kategorie;

public interface KategorieRepository extends CrudRepository<Kategorie, Long> {

	public List<Kategorie> findAllByOrderByNameAsc();

	public Optional<Kategorie> findByName(String name);
}
