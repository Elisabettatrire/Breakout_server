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
    
    public Beacon findByID(Integer id_search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates. 
    }
    
}
