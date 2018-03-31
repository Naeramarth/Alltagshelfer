package de.alltagshelfer.application.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import de.alltagshelfer.application.config.DatabaseInitializer;
import de.alltagshelfer.application.entity.Benutzer;
import de.alltagshelfer.application.entity.Kategorie;
import de.alltagshelfer.application.entity.Role;
import de.alltagshelfer.application.model.AdminData;
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
	private KategorieRepository catRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private Validator validator;

	@Autowired
	private AdminData adminData;

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
			catRepo.deleteAll();
			DatabaseInitializer.initialize(roleRepo, repo, catRepo, passwordEncoder, adminData);
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
			DatabaseInitializer.initialize(roleRepo, repo, catRepo, passwordEncoder, adminData);
			em.setMessage("Alle Benutzer wurden erfolgreich gelöscht");
		} catch (Exception e) {
			e.printStackTrace();
			em.addError("Beim Löschen aller Benutzer ist ein Fehler passiert");
		}
		return em;
	}

	@Override
	public List<Kategorie> findAllCategories() {
		return catRepo.findAllByOrderByNameAsc();
	}

	@Override
	public List<String> createCategory(String name) {
		List<String> errors = new ArrayList<>();
		if (name == null || name.equals(""))
			errors.add("Neuer Kategoriename darf nicht leer sein.");
		Kategorie cat = new Kategorie(name);
		Set<ConstraintViolation<Kategorie>> set = validator.validate(cat);
		set.forEach((ConstraintViolation<Kategorie> violation) -> {
			errors.add(violation.getMessage());
		});
		if (catRepo.findByName(name).isPresent())
			errors.add("Es existiert bereits eine Kategorie mit diesem Namen.");
		if (errors.isEmpty())
			catRepo.save(cat);
		return errors;
	}

	@Override
	public List<String> deleteCategory(long[] ids) {
		List<String> errors = new ArrayList<>();
		if (ids == null) {
			errors.add("Wählen Sie eine zu löschende Kategorie aus.");
			ids = new long[0];
		}
		for (long id : ids) {
			Optional<Kategorie> oCat = catRepo.findById(id);
			if (!oCat.isPresent())
				continue;
			Kategorie cat;
			if (!(cat = oCat.get()).getAnzeigen().isEmpty()) {
				errors.add("Der Kategorie " + cat.getName() + " sind noch Anzeigen zugeordnet.");
				continue;
			}
			catRepo.delete(cat);
		}
		return errors;
	}

}
