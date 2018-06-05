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
    
    private long ID_Piano;
    
    @Override
    public void setCodice() {
        StringBuilder sb = new StringBuilder();
        sb.append("C");
        sb.append(ID);
        codice = sb.toString();
    }
}