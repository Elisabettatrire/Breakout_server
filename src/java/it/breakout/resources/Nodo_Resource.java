package it.breakout.resources;

import java.util.ArrayList;

import it.breakout.services.Nodo_Service;
import it.breakout.models.Nodo;
import it.breakout.models.Pdi;

public class Nodo_Resource {
    
    Nodo_Service nodo_service = new Nodo_Service();
    
    public ArrayList<Nodo> findAll() {
	return nodo_service.findAll();
    }
    
    public Nodo findByID(Integer search_id) {
        return nodo_service.findByID(search_id);
    }
    
    public ArrayList<Pdi> findAllPois() {
	return nodo_service.findAllPois();
    }
    
    public ArrayList<Pdi> findPoisByIDMappa(Integer search_id) {
	return nodo_service.findPoisByIDMappa(search_id);
    }
    
    public ArrayList<Nodo> findByIDMappa(Integer search_id) {
        return nodo_service.findByIDMappa(search_id);
    }
       
}
