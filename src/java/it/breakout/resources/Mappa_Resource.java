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

import it.breakout.models.Mappa;
import it.breakout.services.Mappa_Service;

public class Mappa_Resource {
    
    Mappa_Service mappa_service = new Mappa_Service();
    Connection conn = null;
    
    public Set<Mappa> findAll() {
	return mappa_service.findAll();
    }
    
}
