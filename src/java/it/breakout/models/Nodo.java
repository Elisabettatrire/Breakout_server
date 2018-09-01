/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.models;

import static it.breakout.utility.EnvVariables.M2PX;
import java.util.ArrayList;

/**
 *
 * @author costantino
 */
public class Nodo {

    private Integer ID;
    private String codice;
    private Double larghezza;
    private Double coord_X;
    private Double coord_Y;
    private Integer[] tronchi_stella_int;
    private ArrayList<Scala> tronchi_stella; // tra i tronchi vanno messe anche le scale e i collegamenti
    private Integer ID_mappa;
    private Integer ID_piano;
    private boolean isPdi;

    public Nodo() {
    }

    public boolean isIsPdi() {
        return isPdi;
    }

    public void setIsPdi(boolean isPdi) {
        this.isPdi = isPdi;
    }

    public Integer[] getTronchi_stella_int() {
        return tronchi_stella_int;
    }

    public void setTronchi_stella_int(Integer[] tronchi_stella_int) {
        this.tronchi_stella_int = tronchi_stella_int;
    }
    
    public ArrayList<Scala> getTronchi_stella() {
        return tronchi_stella;
    }

    public void setTronchi_stella(ArrayList<Scala> tronchi_stella) {
        this.tronchi_stella = tronchi_stella;
    }

    public Integer getID_mappa() {
        return ID_mappa;
    }

    public void setID_mappa(Integer ID_mappa) {
        this.ID_mappa = ID_mappa;
    }

    public Double getLarghezza() {
        return larghezza;
    }

    public void setLarghezza(Double larghezza) {
        this.larghezza = larghezza;
    }
    
    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }    

    public void setCoord_X(Double coord_X) {
        this.coord_X = coord_X;
    }
    
    public void setCoord_Y(Double coord_Y) {
        this.coord_Y = coord_Y;
    }
    
    public Double getCoord_X() {
        return coord_X;
    }
    
    public Double getCoord_Y() {
        return coord_Y;
    }
    
    /* Conversione da metri a pixel e viceversa sulla mappa 345px = 54m */
    public Double getCoord_X_px() {
        Double x_px = this.coord_X * M2PX;
        return x_px;
    }
    
    public Double getCoord_Y_px() {
        Double y_px = this.coord_Y * M2PX;
        return y_px;
    }
    
    public Integer getID_piano() {
        return ID_piano;
    }

    public void setID_piano(Integer ID_piano) {
        this.ID_piano = ID_piano;
    }
    
}
