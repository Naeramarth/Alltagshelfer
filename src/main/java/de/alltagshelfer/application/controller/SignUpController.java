package de.alltagshelfer.application.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.alltagshelfer.application.model.FormValues;
import de.alltagshelfer.application.service.SignUpService;

@Controller
public class SignUpController {

	@Autowired
	private SignUpService signUpService;

	@GetMapping("/signup")
	public String signUp() {
		return "signup";
	}

	@PostMapping("/signup")
	public String signUpPost(HttpServletRequest request, Model model, @RequestParam String username,
			@RequestParam String password1, @RequestParam String password2, @RequestParam String name,
			@RequestParam String anschrift, @RequestParam String postleitzahl, @RequestParam String ort,
			@RequestParam String eMail, @RequestParam String telefonnummer) {

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
		FormValues formValues = new FormValues();
		formValues.setValues(request.getParameterMap());
		formValues.setErrors(errors);
		model.addAttribute("signup_form", formValues);
		return "signup";
	}
}
