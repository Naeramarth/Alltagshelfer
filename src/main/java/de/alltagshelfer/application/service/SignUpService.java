package de.alltagshelfer.application.service;

import java.util.List;

import de.alltagshelfer.application.entity.Benutzer;

public interface SignUpService {

	public List<String> signUp(String username, String password1, String password2, String vorname, String nachname,
			String strasse, String hausnummer, String postleitzahl, String ort, String eMail, String telefonnummer);

	public List<String> edit(String benutzername, String oldPassword, String password1, String password2, String vorname,
			String nachname, String strasse, String hausnummer, String postleitzahl, String ort, String eMail,
			String telefonnummer);

	public Benutzer getBenutzer(String name);
}
