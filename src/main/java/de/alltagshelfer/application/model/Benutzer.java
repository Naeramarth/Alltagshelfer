/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.alltagshelfer.application.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author FSche
 */
@Entity
@EqualsAndHashCode(exclude = { "anzeige", "anzeigen" })
@Data
public class Benutzer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Size(min = 5, max = 64, message = "Der Benutzername muss zwischen fünf und 64 Zeichen lang sein.")
	@NotNull(message = "Der Benutzername darf nicht leer sein.")
	private String benutzername;

	@Size(min = 2, max = 64, message = "Der Vorname muss zwischen zwei und 64 Zeichen lang sein.")
	@NotNull(message = "Der Vorname darf nicht leer sein.")
	private String vorname;

	@Size(min = 2, max = 64, message = "Der Nachname muss zwischen zwei und 64 Zeichen lang sein.")
	@NotNull(message = "Der Nachname darf nicht leer sein.")
	private String nachname;

	@Size(min = 2, max = 64, message = "Die Straße muss zwischen zwei und 64 Zeichen lang sein.")
	@NotNull(message = "Die Straße darf nicht leer sein.")
	private String strasse;

	@NotNull(message = "Die Hausnummer darf nicht leer sein.")
	private String hausnummer; // String weil zb 2a auch mgl ist

	@Size(min = 3, max = 10, message = "Die Postleitzahl muss zwischen drei und 10 Zeichen lang sein.")
	@NotNull(message = "Die Postleitzahl darf nicht leer sein.")
	private String postleitzahl;

	@Size(min = 2, max = 64, message = "Der Ort muss zwischen zwei und 64 Zeichen lang sein.")
	@NotNull(message = "Der Ort darf nicht leer sein.")
	private String ort;

	@Size(min = 5, max = 64, message = "Die E-Mail muss zwischen fünf und 64 Zeichen lang sein.")
	@NotNull(message = "Die E-Mail darf nicht leer sein.")
	private String email;

	@Size(min = 5, max = 64, message = "Die Telefonnummer muss zwischen fünf und 64 Zeichen lang sein.")
	@NotNull(message = "Die Telefonnummer darf nicht leer sein.")
	private String telefonnummer;

	@Size(min = 6, max = 64, message = "Das Passwort muss zwischen sechs und 64 Zeichen lang sein.")
	private String password;

	@OneToMany(mappedBy = "benutzer", fetch = FetchType.LAZY)
	private List<Anzeige> anzeige;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Role> roles;

	public Benutzer() {
	}

	public Benutzer(Benutzer benutzer) {
		this.benutzername = benutzer.getBenutzername();
		this.password = benutzer.getPassword();
		this.vorname = benutzer.getVorname();
		this.nachname = benutzer.getNachname();
		this.strasse = benutzer.getStrasse();
		this.hausnummer = benutzer.getHausnummer();
		this.postleitzahl = benutzer.getPostleitzahl();
		this.ort = benutzer.getOrt();
		this.email = benutzer.getEmail();
		this.telefonnummer = benutzer.getTelefonnummer();
		this.password = benutzer.getPassword();
		this.anzeige = benutzer.getAnzeige();
		this.roles = benutzer.getRoles();
	}

	public Benutzer(String benutzername, String vorname, String nachname, String strasse, String hausnummer,
			String postleitzahl, String ort, String eMail, String telefonnummer) {
		this.benutzername = benutzername;
		this.vorname = vorname;
		this.nachname = nachname;
		this.strasse = strasse;
		this.hausnummer = hausnummer;
		this.postleitzahl = postleitzahl;
		this.ort = ort;
		this.email = eMail;
		this.telefonnummer = telefonnummer;
	}

	@Override
	public String toString() {
		return "Benutzer [id=" + id + ", benutzername=" + benutzername + ", vorname=" + vorname + ", nachname="
				+ nachname + ", strasse=" + strasse + ", hausnummer=" + hausnummer + ", postleitzahl=" + postleitzahl
				+ ", ort=" + ort + ", email=" + email + ", telefonnummer=" + telefonnummer + ", password=" + password
				+ "]";
	}
	
	

}
