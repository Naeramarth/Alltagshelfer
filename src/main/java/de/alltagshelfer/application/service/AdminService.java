package de.alltagshelfer.application.service;

import de.alltagshelfer.application.model.ErrorModel;

public interface AdminService {

	ErrorModel addAdmin(String username);

	ErrorModel removeAllUsers();

}
