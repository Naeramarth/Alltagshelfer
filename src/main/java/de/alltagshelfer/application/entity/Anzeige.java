/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.alltagshelfer.application.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import de.alltagshelfer.application.model.ArtDesPreises;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(exclude = { "benutzer", "kategorie" })
public class Anzeige implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	@Size(min = 1, max = 64, message = "Der Titel darf nicht leer sein.")
	@NotNull(message = "Der Titel darf nicht leer sein.")
	private String titel;

	@Lob
	private String beschreibung;

	@NotNull(message = "Das Datum darf nicht leer sein.")
	private LocalDate erstelldatum;

	@NotNull(message = "Das Enddatum darf nicht leer sein.")
	private LocalDate onlineBis;

	@Column(scale = 2)
	private long preisvorstellung;

	@Enumerated(EnumType.STRING)
	@NotNull
	private ArtDesPreises artDesPreises;

	@Lob
	@Column(columnDefinition="blob")
	private byte[] bild;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull(message = "Die Anzeige muss einem Benutzer zugeordnet werden.")
	private Benutzer benutzer;

	@ManyToOne(fetch = FetchType.LAZY)
	private Kategorie kategorie;
}
