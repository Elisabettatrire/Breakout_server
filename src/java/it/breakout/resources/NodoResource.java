package it.breakout.resources;

import java.util.ArrayList;

import it.breakout.services.NodoService;
import it.breakout.models.Nodo;
import it.breakout.models.Pdi;

public class NodoResource {
    
    NodoService nodo_service = new NodoService();
    
    public ArrayList<Nodo> findAll() {
	return nodo_service.findAll();
    }
    
    public Nodo findByID(Integer search_id) {
        return nodo_service.findByID(search_id);
    }
    
    public Pdi findPdiByID(Integer search_id) {
        return nodo_service.findPdiByID(search_id);
    }
    
    public Nodo findByCodice(String search_code) {
        return nodo_service.findByCodice(search_code);
    }
    
    public ArrayList<Pdi> findAllPdi() {
	return nodo_service.findAllPdi();
    }
    
    public ArrayList<Pdi> findPdiByIDMappa(Integer search_id) {
	return nodo_service.findPdiByIDMappa(search_id);
    }
    
    public ArrayList<Nodo> findByIDPiano(Integer search_id) {
        return nodo_service.findByIDPiano(search_id);
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
    
    public void updatePdi(Pdi pdi, Integer id_pdi) {
        nodo_service.updatePdi(pdi, id_pdi);
    }
       
}
