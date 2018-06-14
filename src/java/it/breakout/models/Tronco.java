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
public class Tronco extends Collegamento{
    
    private Integer ID_mappa;
    
    
    public Tronco(){
    };
    
    public Integer getID_mappa() {
        return ID_mappa;
    }

    public void setID_mappa(Integer ID_mappa) {
        this.ID_mappa = ID_mappa;
    }
    
    @Override
    public void setCodice() {
        StringBuilder sb = new StringBuilder();
        sb.append("T");
        sb.append(ID);
        codice = sb.toString();
    }
}