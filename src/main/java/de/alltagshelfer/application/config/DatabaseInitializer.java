package de.alltagshelfer.application.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import de.alltagshelfer.application.entity.Benutzer;
import de.alltagshelfer.application.entity.Role;
import de.alltagshelfer.application.model.RoleName;
import de.alltagshelfer.application.repository.BenutzerRepository;
import de.alltagshelfer.application.repository.RoleRepository;

@Component
public class DatabaseInitializer {

	@Autowired
	public DatabaseInitializer(RoleRepository roleRepo, BenutzerRepository repo, PasswordEncoder passwordEncoder) {
		initialize(roleRepo, repo,  passwordEncoder);
	}

	public static void initialize(RoleRepository roleRepo, BenutzerRepository repo, PasswordEncoder passwordEncoder) {
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
		
		Benutzer benutzer = new Benutzer("Admin", "Super", "Admin", "Adminstr.", "1", "11111", "Karlsruhe", "admin@admin.de",
				"0123456789");
		benutzer.setPassword(passwordEncoder.encode("123456"));
		Set<Role> roles = new HashSet<>();
		roles.addAll(roleList);
		benutzer.setRoles(roles);
		if(!repo.findByBenutzername("Admin").isPresent()) 
			repo.save(benutzer);

	}

}
