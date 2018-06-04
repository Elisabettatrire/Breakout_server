package it.breakout.resources;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.breakout.models.Piano;
import it.breakout.services.Piano_Service;

public class Piano_Resource {
    
    Piano_Service piano_service = new Piano_Service();
    Connection conn = null;
    
    public ArrayList<Piano> findAll() {
	return piano_service.findAll();
    }
    
    public Piano findById(String search_id) {
	return piano_service.findById(search_id);
    }
    
}
