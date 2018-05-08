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
class Grafo {
    private Set<Nodo> nodi;
    private Set<Tronco> tronchi;
    
    public Grafo (){
    }

    public Set<Nodo> getNodi() {
        return nodi;
    }

    public void setNodi(Set<Nodo> nodi) {
        this.nodi = nodi;
    }

    public Set<Tronco> getTronchi() {
        return tronchi;
    }

    public void setTronchi(Set<Tronco> tronchi) {
        this.tronchi = tronchi;
    } 
}
