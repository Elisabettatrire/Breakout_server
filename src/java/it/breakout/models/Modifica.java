/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.models;

/**
 *
 * @author Giovanni
 */
public class Modifica {
    
    private Integer ID_modifica;
    private String tipo;
    private String tabella;
    private Integer ID_oggetto;

    public Integer getID_modifica() {
        return ID_modifica;
    }

    public void setID_modifica(Integer ID_modifica) {
        this.ID_modifica = ID_modifica;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTabella() {
        return tabella;
    }

    public void setTabella(String tabella) {
        this.tabella = tabella;
    }

    public Integer getID_oggetto() {
        return ID_oggetto;
    }

    public void setID_oggetto(Integer ID_oggetto) {
        this.ID_oggetto = ID_oggetto;
    }
    
}
