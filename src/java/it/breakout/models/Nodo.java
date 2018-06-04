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
public class Nodo {

    private long ID;
    private String codice;
    private float larghezza;
    private float coord_X;
    private float coord_Y;
    private ArrayList<Scala> tronchi_stella; // tra i tronchi vanno messe anche le scale e i collegamenti
    private long ID_mappa;
    
    public Nodo() {
    }

    public ArrayList<Scala> getTronchi_stella() {
        return tronchi_stella;
    }

    public void setTronchi_stella(ArrayList<Scala> tronchi_stella) {
        this.tronchi_stella = tronchi_stella;
    }

    public long getID_mappa() {
        return ID_mappa;
    }

    public void setID_mappa(long ID_mappa) {
        this.ID_mappa = ID_mappa;
    }

    public float getLarghezza() {
        return larghezza;
    }

    public void setLarghezza(float larghezza) {
        this.larghezza = larghezza;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
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
