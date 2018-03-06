package de.alltagshelfer.application.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.alltagshelfer.application.model.Role;
import de.alltagshelfer.application.model.RoleName;
import de.alltagshelfer.application.repository.RoleRepository;

@Component
public class DatabaseInitializer {

	private RoleRepository roleRepo;

	@Autowired
	public DatabaseInitializer(RoleRepository roleRepo) {
		this.roleRepo = roleRepo;
		LoadUsers();
	}

	private void LoadUsers() {
		Iterable<Role> i = roleRepo.findAll();
		List<Role> l = new ArrayList<>();
		i.forEach((role) -> l.add(role));
		if (l.isEmpty()) {
			roleRepo.save(new Role(RoleName.ROLE_USER));
			roleRepo.save(new Role(RoleName.ROLE_ADMIN));
		}
	}

}
