package it.breakout.resources;

import java.util.ArrayList;
import java.sql.Connection;

import it.breakout.models.Mappa;
import it.breakout.services.Mappa_Service;

public class Mappa_Resource {
    
    Mappa_Service mappa_service = new Mappa_Service();
    Connection conn = null;
    
    public ArrayList<Mappa> findAll() {
	return mappa_service.findAll();
    }
    
    public ArrayList<Mappa> findByIDPiano(int search_id) {
	return mappa_service.findByIDPiano(search_id);
    }
    
    public Mappa findByNome(String search_nome) {
	return mappa_service.findByNome(search_nome);
    }
    
}
