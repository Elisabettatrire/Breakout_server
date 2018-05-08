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
public class Piano {
    private long ID_piano;
    private String nome;
    private Set<Mappa> mappe;

    public Piano() {
    }

    public Set<Mappa> getMappe() {
        return mappe;
    }

    public void setMappe(Set<Mappa> mappe) {
        this.mappe = mappe;
    }
    
    public long getID_piano() {
        return ID_piano;
    }

    public void setID_piano(long ID_piano) {
        this.ID_piano = ID_piano;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
