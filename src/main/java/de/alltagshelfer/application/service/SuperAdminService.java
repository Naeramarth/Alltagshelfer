package de.alltagshelfer.application.service;

import java.util.List;

import de.alltagshelfer.application.entity.Kategorie;
import de.alltagshelfer.application.model.ErrorModel;

public interface SuperAdminService {

	ErrorModel removeAdmin(String username);

	ErrorModel reset();

	ErrorModel removeUser(String username);

	ErrorModel removeAllUsers();

	List<String> createCategory(String name);

	List<String> deleteCategory(long[] ids);

}
