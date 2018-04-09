package de.alltagshelfer.application.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.alltagshelfer.application.entity.Benutzer;
import de.alltagshelfer.application.entity.Kategorie;
import de.alltagshelfer.application.service.KategorieService;
import de.alltagshelfer.application.service.SignUpService;

@Controller
public class SignUpController {

	@Autowired
	private SignUpService signUpService;
	
	@Autowired
	private KategorieService catService;

	@GetMapping("/signup")
	public String signUp(Model model) {
		model.addAttribute("edit", false);
		return "signup";
	}

	@PostMapping("/signup")
	public String signUpPost(HttpServletRequest request, Model model, @RequestParam String username,
			@RequestParam String password1, @RequestParam String password2, @RequestParam String name,
			@RequestParam String anschrift, @RequestParam String postleitzahl, @RequestParam String ort,
			@RequestParam String eMail, @RequestParam String telefonnummer) {
		model.addAttribute("edit", false);
		List<String> error = new ArrayList<>();
		username = username.trim();
		password1 = password1.trim();
		password2 = password2.trim();
		name = name.trim();
		anschrift = anschrift.trim();
		postleitzahl = postleitzahl.trim();
		ort = ort.trim();
		eMail = eMail.trim();
		telefonnummer = telefonnummer.trim();

		if (!password1.equals(password2))
			error.add("Die Passwörter dürfen nicht unterschiedlich sein.");

		String[] name_array = name.split(" ");
		String vorname = "";
		String nachname = "";
		if (name_array.length < 2) {
			error.add("Vor- und Nachname müssen mit einem Leerzeichen getrennt werden.");
		} else {
			vorname = name_array[0];
			for (int i = 1; i < name_array.length - 1; i++) {
				vorname += " " + name_array[i];
			}
			nachname = name_array[name_array.length - 1];
		}
		vorname = vorname.trim();
		nachname = nachname.trim();

		String[] anschrift_array = anschrift.split(" ");
		String strasse = "";
		String hausnummer = "";
		if (anschrift_array.length < 2) {
			error.add("Straße und Hausnummer müssen mit einem Leerzeichen getrennt werden.");
		} else {
			strasse = anschrift_array[0];
			for (int i = 1; i < anschrift_array.length - 1; i++) {
				strasse += " " + anschrift_array[i];
			}
			hausnummer = anschrift_array[anschrift_array.length - 1];
		}
		strasse = strasse.trim();
		hausnummer = hausnummer.trim();

		if (!new EmailValidator().isValid(eMail, null)) {
			error.add("Die angegebene E-Mail Adresse ist nicht gültig");
		}
		List<String> errors = signUpService.signUp(username, password1, password2, vorname, nachname, strasse,
				hausnummer, postleitzahl, ort, eMail, telefonnummer);
		errors.addAll(error);
		if (errors.isEmpty()) {
			try {
				request.login(username, password1);
				return "redirect:/adverts";
			} catch (ServletException e) {
				errors.add(e.getMessage());
				System.out.println(e);
			}
		}
		model.addAttribute("username", username);
		model.addAttribute("name", name);
		model.addAttribute("anschrift", anschrift);
		model.addAttribute("postleitzahl", postleitzahl);
		model.addAttribute("ort", ort);
		model.addAttribute("eMail", eMail);
		model.addAttribute("telefonnummer", telefonnummer);
		model.addAttribute("errors", errors);
		return "signup";
	}

	@GetMapping("/user")
	public String edit(Model model) {
		model.addAttribute("edit", true);
		Benutzer benutzer = signUpService.getBenutzer(SecurityContextHolder.getContext().getAuthentication().getName());

		model.addAttribute("username", benutzer.getBenutzername());
		model.addAttribute("name", benutzer.getVorname() + " " + benutzer.getNachname());
		model.addAttribute("anschrift", benutzer.getStrasse() + " " + benutzer.getHausnummer());
		model.addAttribute("postleitzahl", benutzer.getPostleitzahl());
		model.addAttribute("ort", benutzer.getOrt());
		model.addAttribute("eMail", benutzer.getEmail());
		model.addAttribute("telefonnummer", benutzer.getTelefonnummer());
		return "signup";
	}

	@PostMapping("/user")
	public String editPost(HttpServletRequest request, Model model, @RequestParam String oldPassword,
			@RequestParam(required = false) String password1, @RequestParam(required = false) String password2,
			@RequestParam String name, @RequestParam String anschrift, @RequestParam String postleitzahl,
			@RequestParam String ort, @RequestParam String eMail, @RequestParam String telefonnummer) {

		oldPassword = oldPassword.trim();
		password1 = password1.trim();
		password2 = password2.trim();
		name = name.trim();
		anschrift = anschrift.trim();
		postleitzahl = postleitzahl.trim();
		ort = ort.trim();
		eMail = eMail.trim();
		telefonnummer = telefonnummer.trim();

		List<String> error = new ArrayList<>();
		if (!password1.equals(password2))
			error.add("Die Passwörter dürfen nicht unterschiedlich sein.");

		String[] name_array = name.split(" ");
		String vorname = "";
		String nachname = "";
		if (name_array.length < 2) {
			error.add("Vor- und Nachname müssen mit einem Leerzeichen getrennt werden.");
		} else {
			vorname = name_array[0];
			for (int i = 1; i < name_array.length - 1; i++) {
				vorname += " " + name_array[i];
			}
			nachname = name_array[name_array.length - 1];
		}
		vorname = vorname.trim();
		nachname = nachname.trim();

		String[] anschrift_array = anschrift.split(" ");
		String strasse = "";
		String hausnummer = "";
		if (anschrift_array.length < 2) {
			error.add("Straße und Hausnummer müssen mit einem Leerzeichen getrennt werden.");
		} else {
			strasse = anschrift_array[0];
			for (int i = 1; i < anschrift_array.length - 1; i++) {
				strasse += " " + anschrift_array[i];
			}
			hausnummer = anschrift_array[anschrift_array.length - 1];
		}
		strasse = strasse.trim();
		hausnummer = hausnummer.trim();

		if (!new EmailValidator().isValid(eMail, null)) {
			error.add("Die angegebene E-Mail Adresse ist nicht gültig");
		}
		String username;
		List<String> errors = signUpService.edit(
				username = SecurityContextHolder.getContext().getAuthentication().getName(), oldPassword, password1,
				password2, vorname, nachname, strasse, hausnummer, postleitzahl, ort, eMail, telefonnummer);
		errors.addAll(error);
		if (errors.isEmpty()) {
			return "redirect:/adverts";
		}
		model.addAttribute("edit", true);
		model.addAttribute("username", username);
		model.addAttribute("name", name);
		model.addAttribute("anschrift", anschrift);
		model.addAttribute("postleitzahl", postleitzahl);
		model.addAttribute("ort", ort);
		model.addAttribute("eMail", eMail);
		model.addAttribute("telefonnummer", telefonnummer);
		model.addAttribute("errors", errors);
		return "signup";
	}
	
	@ModelAttribute("categories")
	public List<Kategorie> getCategories() {
	    return catService.getCategories();
	}

}
