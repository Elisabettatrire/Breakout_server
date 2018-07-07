package it.breakout.resources;

import java.util.ArrayList;
import java.sql.Connection;

import it.breakout.services.TroncoService;
import it.breakout.models.Tronco;
import it.breakout.models.Scala;
import it.breakout.models.Collegamento;

public class TroncoResource {
    
    TroncoService tronco_service = new TroncoService();
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
    
    public Scala findStairByID(Integer search_id) {
        return tronco_service.findStairByID(search_id);
    }
    
    public Collegamento findLinkByID(Integer search_id) {
        return tronco_service.findLinkByID(search_id);
    }
    
    public Tronco findByNodi(Integer codice_nodo_1, Integer codice_nodo_2) {
        return tronco_service.findByNodi(codice_nodo_1, codice_nodo_2);
    }
    
    public void insertTronco(Tronco tronco) {
        tronco_service.insertTronco(tronco);
    }
    
    public void insertScala(Scala scala) {
        tronco_service.insertScala(scala);
    }
    
    public void insertCollegamento(Collegamento collegamento) {
        tronco_service.insertCollegamento(collegamento);
    }
    
    public void updateTronco(Tronco tronco, Integer id_tronco) {
        tronco_service.updateTronco(tronco, id_tronco);
    }
    
    public void updateScala(Scala scala, Integer id_scala) {
        tronco_service.updateScala(scala, id_scala);
    }
    
    public void updateCollegamento(Collegamento collegamento, Integer id_collegamento) {
        tronco_service.updateCollegamento(collegamento, id_collegamento);
    }
    
    public void delete(Integer id_del) {
        tronco_service.delete(id_del);
    }
    
}
