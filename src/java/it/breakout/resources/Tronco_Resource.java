package it.breakout.resources;

import java.util.ArrayList;
import java.sql.Connection;

import it.breakout.services.Tronco_Service;
import it.breakout.models.Tronco;
import it.breakout.models.Scala;
import it.breakout.models.Collegamento;

public class Tronco_Resource {
    
    Tronco_Service tronco_service = new Tronco_Service();
    Connection conn = null;
    
    public ArrayList<Tronco> findAllArcs(Integer id_mappa) {
	return tronco_service.findAllArcs(id_mappa);
    }
    
    public ArrayList<Scala> findAllStairs() {
	return tronco_service.findAllStairs();
    }
    
    public ArrayList<Collegamento> findAllLinks() {
	return tronco_service.findAllLinks();
    }
    
}