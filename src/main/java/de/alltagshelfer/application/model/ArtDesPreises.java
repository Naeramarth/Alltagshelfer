/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.alltagshelfer.application.model;

/**
 *
 * @author FSche
 */
public enum ArtDesPreises {
    FESTPREIS, VERHANDLUNGSBASIS;
    public String getLabel() {
        switch (this) {
            case FESTPREIS:
                return "Festpreis";
            case VERHANDLUNGSBASIS:
                return "Verhandlungsbasis";
            default:
                return this.toString();
        }
    }
}
