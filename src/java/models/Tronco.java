/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Set;

/**
 *
 * @author costantino
 */
public class Tronco {
    private long ID_tronco;
    private long ID_mappa;
    private double larghezza;
    private double lunghezza;
    private Set<Nodo> nodi;
    private Beacon beacon;
    private float costo_totale_normalizzato;
    
    public Tronco(){
    };
    
    public long getID_mappa() {
        return ID_mappa;
    }

    public void setID_mappa(long ID_mappa) {
        this.ID_mappa = ID_mappa;
    }

    public long getID_tronco() {
        return ID_tronco;
    }

    public void setID_tronco(long ID_tronco) {
        this.ID_tronco = ID_tronco;
    }

    public double getLarghezza() {
        return larghezza;
    }

    public void setLarghezza(double larghezza) {
        this.larghezza = larghezza;
    }

    public double getLunghezza() {
        return lunghezza;
    }

    public void setLunghezza(double lunghezza) {
        this.lunghezza = lunghezza;
    }

    public Beacon getBeacon() {
        return beacon;
    }

    public void setBeacon(Beacon beacon) {
        this.beacon = beacon;
    }
    
    
}