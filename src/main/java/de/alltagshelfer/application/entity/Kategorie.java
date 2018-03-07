/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.alltagshelfer.application.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author FSche
 */
@Entity
@Data
@EqualsAndHashCode(exclude = "anzeigen")
public class Kategorie {

    public Kategorie() {
    }

    public Kategorie(String name) {
        this.name = name;
    }

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToMany(mappedBy = "kategorie", fetch = FetchType.LAZY)
    private List<Anzeige> anzeigen;

}
