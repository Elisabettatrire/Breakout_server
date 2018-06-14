/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.models;

/**
 *
 * @author costantino
 */
public class Collegamento extends Scala {
    
    private Integer ID_piano;

    public void setID_piano(Integer ID_piano) {
        this.ID_piano = ID_piano;
    }

    public Integer getID_piano() {
        return ID_piano;
    }
    
    @Override
    public void setCodice() {
        codice = "C" + ID;
    }
}