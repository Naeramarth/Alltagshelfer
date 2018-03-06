package de.alltagshelfer.application.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.alltagshelfer.application.model.FormValues;
import de.alltagshelfer.application.service.SignUpService;

@Controller
public class SignUpController {

	@Autowired
	private SignUpService signUpService;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signUp() {
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signUpPost(HttpServletRequest request, Model model) {
		List<String> error = new ArrayList<>();

		String username = request.getParameter("signup_username");
		String password1 = request.getParameter("signup_password1");
		String password2 = request.getParameter("signup_password2");
		if(!password1.equals(password2))
			error.add("Die Passwörter dürfen nicht unterschiedlich sein.");
		String name = request.getParameter("signup_name");
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

		String anschrift = request.getParameter("signup_strasse");
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
		String postleitzahl = request.getParameter("signup_postleitzahl");
		String ort = request.getParameter("signup_ort");
		String land = request.getParameter("signup_land");
		String eMail = request.getParameter("signup_eMail");
		if (!new EmailValidator().isValid(eMail, null)) {
			error.add("Die angegebene E-Mail Adresse ist nicht gültig");
		}
		String telefonnummer = request.getParameter("signup_telefonnummer");
		List<String> errors = signUpService.signUp(username, password1, password2, vorname, nachname, strasse,
				hausnummer, postleitzahl, ort, eMail, telefonnummer);
		errors.addAll(error);
		if (errors.isEmpty()) {
			try {
				request.login(username, password1);
				return "redirect:/secured/anzeigen";
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
