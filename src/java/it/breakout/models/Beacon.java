/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.models;

import it.breakout.resources.NodoResource;

import static it.breakout.utility.EnvVariables.M2PX;

/**
 *
 * @author costantino
 */
public class Beacon {
    
    private Integer ID_beacon;
    private String codice; // Indirizzo del Beacon
    private Double coord_X;
    private Double coord_Y;
    private Double ind_fuoco;
    private Double ind_fumi;
    private Double ind_NDC;
    private Double ind_rischio;
    private Integer ID_pdi;
    private Integer ID_mappa;
    private Integer ID_piano;

    public Beacon() {
    }
    
    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public Integer getID_pdi() {
        return ID_pdi;
    }

    public void setID_pdi(Integer ID_pdi) {
        this.ID_pdi = ID_pdi;
    }

    public Integer getID_beacon() {
        return ID_beacon;
    }

    public void setID_beacon(Integer ID_beacon) {
        this.ID_beacon = ID_beacon;
    }

    public Double getCoord_X() {
        return coord_X;
    }

    public void setCoord_X(Double coord_X) {
        this.coord_X = coord_X;
    }
    
    /* Conversione da metri a pixel e viceversa sulla mappa 345px = 54m */
    public Double getCoord_X_px() {
        Double x_px = this.coord_X * M2PX;
        return x_px;
    }

    public Double getCoord_Y() {
        return coord_Y;
    }

    public void setCoord_Y(Double coord_Y) {
        this.coord_Y = coord_Y;
    }

    public Double getCoord_Y_px() {
        Double y_px = this.coord_Y * M2PX;
        return y_px;
    }
    
    public Double getInd_fuoco() {
        return ind_fuoco;
    }

    public void setInd_fuoco(Double ind_fuoco) {
        this.ind_fuoco = ind_fuoco;
    }

    public Double getInd_fumi() {
        return ind_fumi;
    }

    public void setInd_fumi(Double ind_fumi) {
        this.ind_fumi = ind_fumi;
    }

    public Double getInd_NDC() {
        return ind_NDC;
    }

    public void setInd_NDC(Double ind_NDC) {
        this.ind_NDC = ind_NDC;
    }

    public Double getInd_rischio() {
        return ind_rischio;
    }

    public void setInd_rischio(Double ind_rischio) {
        this.ind_rischio = ind_rischio;
    }
    
    public Integer getID_mappa() {
        return ID_mappa;
    }

    public void setID_mappa(Integer ID_mappa) {
        this.ID_mappa = ID_mappa;
    }
    
    public Integer getID_piano() {
        return ID_piano;
    }

    public void setID_piano(Integer ID_piano) {
        this.ID_piano = ID_piano;
    }
    
    public String getCodicePdi() {
        
        NodoResource nodo_resource = new NodoResource();
        
        return nodo_resource.findPdiByID(this.ID_pdi).getCodice();
    }
}
