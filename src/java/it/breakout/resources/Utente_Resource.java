/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.resources;

import java.util.ArrayList;

import it.breakout.services.Utente_Service;
import it.breakout.models.Utente;

/**
 *
 * @author Giovanni
 */
public class Utente_Resource {
 
    Utente_Service utente_service = new Utente_Service();
    
    public ArrayList<Utente> findAll() {
        return utente_service.findAll();
    }
    
    public void update(String psw, Integer id_utente) {
        utente_service.update(psw, id_utente);
    }
    
    public void delete(Integer id_utente) {
        utente_service.delete(id_utente);
    }
    
}
