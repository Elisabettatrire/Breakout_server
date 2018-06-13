package it.breakout.resources;

import java.util.ArrayList;

import it.breakout.services.Nodo_Service;
import it.breakout.models.Nodo;
import it.breakout.models.Pdi;

public class Nodo_Resource {
    
    Nodo_Service nodo_service = new Nodo_Service();
    
    public ArrayList<Nodo> findAllNodes() {
	return nodo_service.findAllNodes();
    }
    
    public ArrayList<Pdi> findPoisByIDMappa(int search_id) {
	return nodo_service.findPoisByIDMappa(search_id);
    }
    
    public ArrayList<Nodo> findByIDMappa(int search_id) {
        return nodo_service.findByIDMappa(search_id);
    }
       
}
