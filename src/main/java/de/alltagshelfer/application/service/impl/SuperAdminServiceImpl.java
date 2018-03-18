package de.alltagshelfer.application.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import de.alltagshelfer.application.config.DatabaseInitializer;
import de.alltagshelfer.application.entity.Benutzer;
import de.alltagshelfer.application.entity.Role;
import de.alltagshelfer.application.model.ErrorModel;
import de.alltagshelfer.application.model.RoleName;
import de.alltagshelfer.application.repository.AnzeigeRepository;
import de.alltagshelfer.application.repository.BenutzerRepository;
import de.alltagshelfer.application.repository.KategorieRepository;
import de.alltagshelfer.application.repository.RoleRepository;
import de.alltagshelfer.application.service.SuperAdminService;

@Service
public class SuperAdminServiceImpl implements SuperAdminService {

	@Autowired
	private BenutzerRepository repo;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private AnzeigeRepository anzeigeRepo;

	@Autowired
	private KategorieRepository kategorieRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public ErrorModel removeAdmin(String username) {
		ErrorModel em = new ErrorModel();
		try {
			Optional<Benutzer> user = repo.findByBenutzername(username);
			if (!user.isPresent())
				em.addError("Der angegebene Benutzer existiert nicht.");
			else {
				Benutzer benutzer = user.get();
				Set<Role> roles = benutzer.getRoles();
				Set<RoleName> rn = new HashSet<>();
				roles.forEach((r) -> rn.add(r.getRole()));
				if (rn.contains(RoleName.ROLE_SUPERADMIN))
					em.addError("Dieser Benutzer ist Super Administrator und muss daher Administrator bleiben.");
				else if (!rn.contains(RoleName.ROLE_ADMIN))
					em.addError("Dieser Benutzer ist kein Administrator.");
				else {
					roles.remove((roleRepo.findByRole(RoleName.ROLE_ADMIN)));
					em.setMessage(benutzer.getBenutzername());
					repo.save(benutzer);
				}
			}
		} catch (Exception e) {
			em.addError("Ein Fehler ist bei der Rollenänderung passiert.");
		}
		return em;
	}

	@Override
	public ErrorModel reset() {
		ErrorModel em = new ErrorModel();
		try {
			repo.deleteAll();
			anzeigeRepo.deleteAll();
			kategorieRepo.deleteAll();
			DatabaseInitializer.initialize(roleRepo, repo, passwordEncoder);
			em.setMessage("Die Datenbank wurde erfolgreich zurückgesetzt");
		} catch (Exception e) {
			e.printStackTrace();
			em.addError("Beim Zurücksetzen der Datenbank ist ein Fehler passiert");
		}
		return em;
	}


	@Override
	public ErrorModel removeUser(String username) {
		ErrorModel em = new ErrorModel();
		try {
			Optional<Benutzer> user = repo.findByBenutzername(username);
			if (!user.isPresent())
				em.addError("Der angegebene Benutzer existiert nicht.");
			else {
				Benutzer benutzer = user.get();
				Set<Role> roles = benutzer.getRoles();
				Set<RoleName> rn = new HashSet<>();
				roles.forEach((r) -> rn.add(r.getRole()));
				if (rn.contains(RoleName.ROLE_SUPERADMIN))
					em.addError("Dieser Benutzer ist ein Super Admin.");
				else {
					String name = benutzer.getBenutzername();
					repo.deleteByBenutzername(name);
					em.setMessage("Benutzer " + name + " wurde gelöscht.");
				}
			}
		} catch (Exception e) {
			em.addError("Ein Fehler ist beim löschen passiert.");
		}
		return em;
	}

	@Override
	public ErrorModel removeAllUsers() {
		ErrorModel em = new ErrorModel();
		try {
			repo.deleteAll();
			DatabaseInitializer.initialize(roleRepo, repo, passwordEncoder);
			em.setMessage("Alle Benutzer wurden erfolgreich gelöscht");
		} catch (Exception e) {
			e.printStackTrace();
			em.addError("Beim Löschen aller Benutzer ist ein Fehler passiert");
		}
		return em;
	}

}
