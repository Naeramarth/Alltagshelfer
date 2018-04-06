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
		List<String> messages = new ArrayList<>();
		set.forEach((ConstraintViolation<Benutzer> violation) -> {
			messages.add(violation.getMessage());
		});
		Optional<Benutzer> op = repo.findByBenutzername(username);
		if (op.isPresent())
			messages.add("Ein Benutzer mit diesem Benutzernamen existiert bereits");
		op = repo.findByEmail(eMail);
		if (op.isPresent())
			messages.add("Ein Benutzer mit dieser E-Mail existiert bereits");
		if (messages.isEmpty())
			repo.save(benutzer);
		return messages;
	}

	@Override
	public List<String> edit(String benutzername, String oldPassword, String password1, String password2,
			String vorname, String nachname, String strasse, String hausnummer, String postleitzahl, String ort,
			String eMail, String telefonnummer) {
		List<String> errors = new ArrayList<>();
		Optional<Benutzer> o = repo.findByBenutzername(benutzername);
		Benutzer benutzer = o.get();
		if (!passwordEncoder.matches(oldPassword, benutzer.getPassword()))
			errors.add("Das eingegebene alte Passwort ist nicht korrekt.");
		benutzer.setEmail(eMail);
		benutzer.setVorname(vorname);
		benutzer.setNachname(nachname);
		benutzer.setStrasse(strasse);
		benutzer.setHausnummer(hausnummer);
		benutzer.setPostleitzahl(postleitzahl);
		benutzer.setOrt(ort);
		benutzer.setTelefonnummer(telefonnummer);
		if (!password1.equals(""))
			benutzer.setPassword(passwordEncoder.encode(password1));
		Set<ConstraintViolation<Benutzer>> set = validator.validate(benutzer);
		set.forEach((ConstraintViolation<Benutzer> violation) -> {
			errors.add(violation.getMessage());
		});
		if (errors.isEmpty())
			repo.save(benutzer);
		return errors;
	}

	@Override
	public Benutzer getBenutzer(String name) {
		return repo.findByBenutzername(name).get();
	}

}
