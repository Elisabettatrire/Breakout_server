/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.models;

/**
 *
 * @author costantino
 */
public class Utente_Server {
    private long ID_utente;
    private String username;
    private String password;
    private String email;
    private String nome;
    private String cognome;

    public Utente_Server() {
    }

    public long getID_utente() {
        return ID_utente;
    }

    public void setID_utente(long ID_utente) {
        this.ID_utente = ID_utente;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
}
