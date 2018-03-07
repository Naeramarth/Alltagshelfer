package de.alltagshelfer.application.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.alltagshelfer.application.entity.Role;
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
		List<Role> roleList = new ArrayList<>();
		
		roleList.add(new Role(1, RoleName.ROLE_USER));
		roleList.add(new Role(2, RoleName.ROLE_ADMIN));
		roleList.add(new Role(3, RoleName.ROLE_SUPERADMIN));
		
		Iterable<Role> i = roleRepo.findAll();
		List<Role> l = new ArrayList<>();
		i.forEach((role) -> l.add(role));
		if (l.size() != roleList.size()) {
			roleRepo.deleteAll();
			for (Role r : roleList)
				roleRepo.save(r);
		}
	}

}
