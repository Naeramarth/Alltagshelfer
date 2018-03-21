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
    STÜNDLICH, PAUSCHALE;
    public String getLabel() {
        switch (this) {
            case STÜNDLICH:
                return "Stündlich";
            case PAUSCHALE:
                return "Pauschale";
            default:
                return this.toString();
        }
    }
}
