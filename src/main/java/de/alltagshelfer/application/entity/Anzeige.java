/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.alltagshelfer.application.entity;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.alltagshelfer.application.model.ArtDerAnzeige;
import de.alltagshelfer.application.model.ArtDesPreises;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author FSche
 */
@Entity
@Data
@EqualsAndHashCode(exclude = {"benutzer", "list_benutzer", "kategorie"})
public class Anzeige implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ArtDerAnzeige art;
    
    @Size(min = 1, max = 64, message = "Der Titel darf nicht leer sein.")
    @NotNull(message = "Der Titel darf nicht leer sein.")
    private String titel;
    
    @Lob
    private String beschreibung;
    
    @NotNull(message = "Das Datum darf nicht leer sein.")
    private Date einstellungsdatum;
    
    @NotNull(message = "Die Zeit darf nicht leer sein.")
    private Time einstellungszeit;
    
    @Column(scale = 2)
    private long preisvorstellung;
    
    @Enumerated(EnumType.STRING)
    @NotNull
    private ArtDesPreises artDesPreises;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Die Anzeige muss einem Benutzer zugeordnet werden.")
    private Benutzer benutzer;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Kategorie kategorie;
}
