/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.models;


import java.sql.Timestamp;
import java.util.ArrayList;
/**
 *
 * @author costantino
 */
public class Mappa {
    private Integer ID_mappa;
    private Integer ID_piano;
//    private Grafo grafo;
    private String nome;
    private String urlImmagine;
    private Timestamp ultima_modifica;
    private Double scala_img;
    
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
    
    public Double getScala_img() {
        return scala_img;
    }

    public void setScala_img(Double scala_img) {
        this.scala_img = scala_img;
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
}

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