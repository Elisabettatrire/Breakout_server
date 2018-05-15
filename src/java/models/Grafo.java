/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;

/**
 *
 * @author costantino
 */
class Grafo {
    private ArrayList<Nodo> nodi;
    private ArrayList<Scala> tronchi;
    
    public Grafo (){
    }

    public ArrayList<Nodo> getNodi() {
        return nodi;
    }

    public void setNodi(ArrayList<Nodo> nodi) {
        this.nodi = nodi;
    }

    public ArrayList<Scala> getTronchi() {
        return tronchi;
    }

    public void setTronchi(ArrayList<Scala> tronchi) {
        this.tronchi = tronchi;
    }
}
