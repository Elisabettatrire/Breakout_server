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
public class Piano {
    private Integer ID_piano;
    private String quota;
    private ArrayList<Mappa> mappe;

    public Piano() {
    }

    public ArrayList<Mappa> getMappe() {
        return mappe;
    }

    public void setMappe(ArrayList<Mappa> mappe) {
        this.mappe = mappe;
    }
    
    public Integer getID_piano() {
        return ID_piano;
    }

    public void setID_piano(Integer ID_piano) {
        this.ID_piano = ID_piano;
    }

    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }
}
