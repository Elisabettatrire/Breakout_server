package it.breakout.resources;

import java.util.ArrayList;
import java.sql.Connection;

import it.breakout.services.Nodo_Service;
import it.breakout.models.Nodo;
import it.breakout.models.Pdi;

public class Nodo_Resource {
    
    Nodo_Service nodo_service = new Nodo_Service();
    Connection conn = null;
    
    public ArrayList<Nodo> findAllNodes() {
	return nodo_service.findAllNodes();
    }
    
    public ArrayList<Pdi> findAllPois() {
	return nodo_service.findAllPois();
    }
       
}
