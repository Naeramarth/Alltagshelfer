package de.alltagshelfer.application.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
			repo.deleteByRoles_RoleNot(RoleName.ROLE_SUPERADMIN);
			anzeigeRepo.deleteAll();
			kategorieRepo.deleteAll();
			em.setMessage("Die Datenbank wurde erfolgreich zurückgesetzt");
		} catch (Exception e) {
			e.printStackTrace();
			em.addError("Beim Zurücksetzen der Datenbank ist ein Fehler passiert");
		}
		return em;
	}

}
