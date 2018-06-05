/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.models;

import java.util.ArrayList;

/**
 *
 * @author costantino
 */
public class Scala {
    protected long ID;
    private double larghezza_media;
    private double lunghezza;
    private Long[] nodi_long;
    private ArrayList<Nodo> nodi;
    private Beacon beacon;
    private float costo_totale_normalizzato;
    protected String codice;

    public Scala() {
        nodi_long = new Long[2];
    }
    
    public Nodo otherNode (Nodo node){
        Nodo other = null;
        ArrayList<Nodo> i = getNodi();
        
        for(Nodo f:i){
            if (f.equals(node)){ 
            } else {
                other = (Nodo) f;
            }  
        }
    return other;
    }

    public Long[] getNodiLong() {
        return nodi_long;
    }

    public void setNodiLong(long nodo_1, long nodo_2) {
        nodi_long[0] = nodo_1;
        nodi_long[1] = nodo_2;
    }

    public ArrayList<Nodo> getNodi() {
        return nodi;
    }

    public void setNodi(ArrayList<Nodo> nodi) {
        this.nodi = nodi;
    }
    
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public double getLarghezza_media() {
        return larghezza_media;
    }

    public void setLarghezza_media(double larghezza_media) {
        this.larghezza_media = larghezza_media;
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

    public float getCosto_totale_normalizzato() {
        return costo_totale_normalizzato;
    }

    public void setCosto_totale_normalizzato(float costo_totale_normalizzato) {
        this.costo_totale_normalizzato = costo_totale_normalizzato;
    }
    
    public String getCodice() {
        return codice;
    }
    
    public void setCodice() {
        StringBuilder sb = new StringBuilder();
        sb.append("S");
        sb.append(ID);
        codice = sb.toString();
    }
}
