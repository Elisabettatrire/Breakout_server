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
public class Scala {
    private long ID_scala;
    private double larghezza_media;
    private double lunghezza;
    private Set<Nodo> nodi;
    private Beacon beacon;
    private float costo_totale_normalizzato;

    public Scala() {
    }

    public long getID_scala() {
        return ID_scala;
    }

    public void setID_scala(long ID_scala) {
        this.ID_scala = ID_scala;
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

    public Set<Nodo> getNodi() {
        return nodi;
    }

    public void setNodi(Set<Nodo> nodi) {
        this.nodi = nodi;
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
}
