/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


import java.sql.Timestamp;
/**
 *
 * @author costantino
 */
public class Mappa {
    private long ID_mappa;
    private long ID_piano;
//    private Grafo grafo;
    private String nome;
    private String urlImmagine;
    private Timestamp ultima_modifica;
    
    public Mappa() {
    }

//    public Grafo getGrafo() {
//        return grafo;
//    }
//
//    public void setGrafo(Grafo grafo) {
//        this.grafo = grafo;
//    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlImmagine() {
        return urlImmagine;
    }

    public void setUrlImmagine(String urlImmagine) {
        this.urlImmagine = urlImmagine;
    }

    public Timestamp getUltima_modifica() {
        return ultima_modifica;
    }

    public void setUltima_modifica(Timestamp ultima_modifica) {
        this.ultima_modifica = ultima_modifica;
    }

    public long getID_mappa() {
        return ID_mappa;
    }

    public void setID_mappa(long ID_mappa) {
        this.ID_mappa = ID_mappa;
    }

    public long getID_piano() {
        return ID_piano;
    }

    public void setID_piano(long ID_piano) {
        this.ID_piano = ID_piano;
    }    
}
