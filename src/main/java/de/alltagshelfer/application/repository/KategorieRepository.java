package de.alltagshelfer.application.repository;

import org.springframework.data.repository.CrudRepository;

import de.alltagshelfer.application.entity.Kategorie;

public interface KategorieRepository extends CrudRepository<Kategorie, Long> {

}
