package de.alltagshelfer.application.service;

import java.util.List;

import de.alltagshelfer.application.entity.Kategorie;
import de.alltagshelfer.application.model.ErrorModel;

public interface AdminService {

	ErrorModel addAdmin(String username);

	ErrorModel removeUser(String username);

	List<Kategorie> findAllCategories();

	List<String> createCategory(String name);

}
