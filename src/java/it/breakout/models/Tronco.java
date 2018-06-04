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
    private long ID_mappa;
    
    
    public Tronco(){
    };
    
    public long getID_mappa() {
        return ID_mappa;
    }

    public void setID_mappa(long ID_mappa) {
        this.ID_mappa = ID_mappa;
    }
}