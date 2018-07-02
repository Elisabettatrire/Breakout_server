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
    
    public Tronco findArcByID(Integer search_id) {
        return tronco_service.findArcByID(search_id);
    }
    
    public Tronco findArcByNodi(Integer codice_nodo_1, Integer codice_nodo_2) {
        return tronco_service.findArcByNodi(codice_nodo_1, codice_nodo_2);
    }
    
    public void insert(Tronco tronco) {
        tronco_service.insert(tronco);
    }
    
    public void update(Tronco tronco, Integer id_tronco) {
        tronco_service.update(tronco, id_tronco);
    }
    
    public void delete(Integer id_tronco) {
        tronco_service.delete(id_tronco);
    }
    
}
