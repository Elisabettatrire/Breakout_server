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
public class Pdi {
    private long ID_pdi;
    private Set<Beacon> beacon;
    private String tipo;
    private String descrizione;
    private float larghezza;
    private float lunghezza;
    private float coord_X;
    private float coord_Y;

    public Pdi() {
    }

    public long getID_pdi() {
        return ID_pdi;
    }

    public void setID_pdi(long ID_pdi) {
        this.ID_pdi = ID_pdi;
    }

    public Set<Beacon> getBeacon() {
        return beacon;
    }

    public void setBeacon(Set<Beacon> beacon) {
        this.beacon = beacon;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public float getLarghezza() {
        return larghezza;
    }

    public void setLarghezza(float larghezza) {
        this.larghezza = larghezza;
    }

    public float getLunghezza() {
        return lunghezza;
    }

    public void setLunghezza(float lunghezza) {
        this.lunghezza = lunghezza;
    }

    public float getCoord_X() {
        return coord_X;
    }

    public void setCoord_X(float coord_X) {
        this.coord_X = coord_X;
    }

    public float getCoord_Y() {
        return coord_Y;
    }

    public void setCoord_Y(float coord_Y) {
        this.coord_Y = coord_Y;
    }
}
