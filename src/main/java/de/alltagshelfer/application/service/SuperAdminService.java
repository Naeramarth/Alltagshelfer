package de.alltagshelfer.application.service;

import de.alltagshelfer.application.model.ErrorModel;

public interface SuperAdminService {

	ErrorModel removeAdmin(String username);

	ErrorModel reset();

	ErrorModel removeUser(String username);

	ErrorModel removeAllUsers();

}
