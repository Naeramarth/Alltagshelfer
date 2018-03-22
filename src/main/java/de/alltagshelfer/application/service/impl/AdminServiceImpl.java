package de.alltagshelfer.application.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.alltagshelfer.application.entity.Benutzer;
import de.alltagshelfer.application.entity.Kategorie;
import de.alltagshelfer.application.entity.Role;
import de.alltagshelfer.application.model.ErrorModel;
import de.alltagshelfer.application.model.RoleName;
import de.alltagshelfer.application.repository.BenutzerRepository;
import de.alltagshelfer.application.repository.KategorieRepository;
import de.alltagshelfer.application.repository.RoleRepository;
import de.alltagshelfer.application.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private BenutzerRepository repo;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private KategorieRepository catRepo;

	@Autowired
	private Validator validator;

	@Override
	public ErrorModel addAdmin(String username) {
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
				if (rn.contains(RoleName.ROLE_ADMIN))
					em.addError("Dieser Benutzer ist bereits Administrator.");
				else {
					roles.add(roleRepo.findByRole(RoleName.ROLE_ADMIN));
					em.setMessage("Benutzer " + benutzer.getBenutzername() + " ist jetzt ein Administrator.");
					repo.save(benutzer);
				}
			}
		} catch (Exception e) {
			em.addError("Ein Fehler ist bei der Rollenänderung passiert.");
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
				if (rn.contains(RoleName.ROLE_ADMIN))
					em.addError("Dieser Benutzer ist ein Administrator.");
				else {
					String name = benutzer.getBenutzername();
					repo.deleteByBenutzername(name);
					em.setMessage("Benutzer " + name + " wurde erfolgreich gelöscht.");
				}
			}
		} catch (Exception e) {
			em.addError("Ein Fehler ist beim löschen passiert.");
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
}
