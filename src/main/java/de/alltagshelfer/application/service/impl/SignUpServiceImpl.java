package de.alltagshelfer.application.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import de.alltagshelfer.application.entity.Benutzer;
import de.alltagshelfer.application.entity.Role;
import de.alltagshelfer.application.model.RoleName;
import de.alltagshelfer.application.repository.BenutzerRepository;
import de.alltagshelfer.application.repository.RoleRepository;
import de.alltagshelfer.application.service.SignUpService;

@Service
@DependsOn("passwordEncoder")
public class SignUpServiceImpl implements SignUpService {

	@Autowired
	private BenutzerRepository repo;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private Validator validator;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<String> signUp(String username, String password1, String password2, String vorname, String nachname,
			String strasse, String hausnummer, String postleitzahl, String ort, String eMail, String telefonnummer) {
		Benutzer benutzer = new Benutzer(username, vorname, nachname, strasse, hausnummer, postleitzahl, ort, eMail,
				telefonnummer);
		benutzer.setPassword(passwordEncoder.encode(password1));
		Set<Role> s = new HashSet<>();
		s.add(roleRepo.findByRole(RoleName.ROLE_USER));
		benutzer.setRoles(s);
		Set<ConstraintViolation<Benutzer>> set = validator.validate(benutzer);
		List<String> messages = new ArrayList<String>();
		set.forEach((ConstraintViolation<Benutzer> violation) -> {
			messages.add(violation.getMessage());
		});
		Optional<Benutzer> op = repo.findByBenutzername(username);
		if (op.isPresent())
			messages.add("Ein Benutzer mit diesem Benutzernamen existiert bereits");
		if (messages.isEmpty())
			repo.save(benutzer);
		return messages;
	}

}
