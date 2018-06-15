package it.breakout.resources;

import java.util.ArrayList;
import java.sql.Connection;

import it.breakout.models.Piano;
import it.breakout.services.Piano_Service;

public class Piano_Resource {
    
    Piano_Service piano_service = new Piano_Service();
    Connection conn = null;
    
    /* Funzioni di ricerca */
    public ArrayList<Piano> findAll() {
	return piano_service.findAll();
    }
    
    public Piano findById(int search_id) {
        return piano_service.findById(search_id);
    }
    
    public Piano findByQuota(String quota) {
        return piano_service.findByQuota(quota);
    }
    
    /* Funzioni di inserimento-modifica-cancellazione */
    public void insert(String quota) {
        piano_service.insert(quota);
    }
    
    public void update(String quota, int id_piano) {
        piano_service.update(quota, id_piano);
    }
    
    public void delete(int id_piano) {
        piano_service.delete(id_piano);
    }
}