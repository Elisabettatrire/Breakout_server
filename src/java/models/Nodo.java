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
public class Nodo {

    private long ID_nodo;
    private float coord_X;
    private float coord_Y;
    private Set<Tronco> tronchi_stella;
    
    public Nodo() {
    }

    public Set<Tronco> getTronchi_stella() {
        return tronchi_stella;
    }

    public void setTronchi_stella(Set<Tronco> tronchi_stella) {
        this.tronchi_stella = tronchi_stella;
    }

    public long getID_nodo() {
        return ID_nodo;
    }

    public void setID_nodo(long ID_nodo) {
        this.ID_nodo = ID_nodo;
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
