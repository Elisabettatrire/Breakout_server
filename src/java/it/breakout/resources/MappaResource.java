package it.breakout.resources;

import java.util.ArrayList;
import java.sql.Connection;

import it.breakout.models.Mappa;
import it.breakout.services.MappaService;

public class MappaResource {
    
    MappaService mappa_service = new MappaService();
    Connection conn = null;
    
    public ArrayList<Mappa> findAll() {
	return mappa_service.findAll();
    }
    
    public ArrayList<Mappa> findByIDPiano(int search_id) {
	return mappa_service.findByIDPiano(search_id);
    }
    
    public Mappa findByID(int search_id) {
	return mappa_service.findByID(search_id);
    }
    
    public Mappa findByNome(String search_nome) {
	return mappa_service.findByNome(search_nome);
    }
    
    public void insert(Mappa mappa) {
        mappa_service.insert(mappa);
    }
    
    public void update(Mappa mappa, int id_mappa) {
        mappa_service.update(mappa, id_mappa);
    }
    
    public void delete(int id_mappa) {
        mappa_service.delete(id_mappa);
    }
    
}
