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
    
    public Pdi findPoiByID(Integer search_id) {
        return nodo_service.findPoiByID(search_id);
    }
    
    public Nodo findByCodice(String search_code) {
        return nodo_service.findByCodice(search_code);
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
    
    public void insertNodo(Nodo nodo) {
        nodo_service.insertNodo(nodo);
    }
    
    public void updateNodo(Nodo nodo, Integer id_nodo) {
        nodo_service.updateNodo(nodo, id_nodo);
    }
    
    public void deleteNodo(Integer id_nodo) {
        nodo_service.deleteNodo(id_nodo);
    }
    
    public void insertPdi(Pdi pdi) {
        nodo_service.insertPdi(pdi);
    }
    
    public void updatePoi(Pdi pdi, Integer id_pdi) {
        nodo_service.updatePoi(pdi, id_pdi);
    }
       
}
