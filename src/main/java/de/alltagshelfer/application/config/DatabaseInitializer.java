package de.alltagshelfer.application.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import de.alltagshelfer.application.entity.Benutzer;
import de.alltagshelfer.application.entity.Kategorie;
import de.alltagshelfer.application.entity.Role;
import de.alltagshelfer.application.model.AdminData;
import de.alltagshelfer.application.model.RoleName;
import de.alltagshelfer.application.repository.BenutzerRepository;
import de.alltagshelfer.application.repository.KategorieRepository;
import de.alltagshelfer.application.repository.RoleRepository;

@Component
public class DatabaseInitializer {

	@Autowired
	public DatabaseInitializer(RoleRepository roleRepo, BenutzerRepository repo, KategorieRepository catRepo,
			PasswordEncoder passwordEncoder, AdminData adminData) {
		initialize(roleRepo, repo, catRepo, passwordEncoder, adminData);
	}

	public static void initialize(RoleRepository roleRepo, BenutzerRepository repo, KategorieRepository catRepo,
			PasswordEncoder passwordEncoder, AdminData adminData) {
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

		String username;
		Benutzer benutzer = new Benutzer(username = adminData.getUsername(), "Super", "Admin", "Adminstr.", "1",
				"11111", "Karlsruhe", "admin@admin.de", "0123456789");
		benutzer.setPassword(passwordEncoder.encode(adminData.getPassword()));
		Set<Role> roles = new HashSet<>();
		roles.addAll(roleList);
		benutzer.setRoles(roles);
		if (!repo.findByBenutzername(username).isPresent())
			repo.save(benutzer);

		Iterable<Kategorie> icats = catRepo.findAll();
		ArrayList<Kategorie> cats = new ArrayList<>();
		icats.forEach(cat -> {
			cats.add(cat);
		});
		if (cats.size() < 3) {
			catRepo.deleteAll();
			catRepo.save(new Kategorie("Computer"));
			catRepo.save(new Kategorie("Handwerk"));
			catRepo.save(new Kategorie("Haushalt"));
		}

	}

}
