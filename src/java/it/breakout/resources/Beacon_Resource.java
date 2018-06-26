/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.resources;

import java.util.ArrayList;
import java.sql.Connection;

import it.breakout.models.Beacon;
import it.breakout.services.Beacon_Service;
/**
 *
 * @author Giovanni
 */
public class Beacon_Resource {
    
    Beacon_Service beacon_service = new Beacon_Service();
    Connection conn = null;
    
    public ArrayList<Beacon> findAll() {
	return beacon_service.findAll();
    }
    
    public Beacon findByID(Integer search_id) {
        return beacon_service.findByID(search_id);
    }
    
    public ArrayList<Beacon> findByIDMappa(Integer id_mappa) {
        return beacon_service.findByIDMappa(id_mappa);
    }
    
    public Beacon findByCodice(String search_code) {
        return beacon_service.findByCodice(search_code);
    }
    
    public void insert(Beacon beacon) {
        beacon_service.insert(beacon);
    }
    
    public void update(Beacon beacon, Integer id_beacon) {
        beacon_service.update(beacon, id_beacon);
    }
    
    public void delete(Integer id_beacon) {
        beacon_service.delete(id_beacon);
    }
    
}
