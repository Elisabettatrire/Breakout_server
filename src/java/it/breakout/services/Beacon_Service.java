/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;

import it.breakout.models.Beacon;

/**
 *
 * @author costantino
 */
public class Beacon_Service {
    
    private PreparedStatement st = null;
    private Connection conn = null;
    
    public static final String TBL_NAME = "beacon";
    public static final String FIELD_ID = "id_beacon";
    public static final String FIELD_COORD_X = "coordinata_x";
    public static final String FIELD_COORD_Y = "coordinata_y";
    public static final String FIELD_FUOCO = "fuoco";
    public static final String FIELD_FUMI = "fumi";
    public static final String FIELD_NDC = "los";
    public static final String FIELD_RISCHIO = "rischio";
    public static final String FIELD_ID_PDI = "id_pdi";
    public static final String FIELD_ID_MAPPA = "";
    
    
    private void open() throws SQLException {
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/breakout1", "app", "app");
    }
    
    private void close() {
        try {
            if (conn != null) {
                conn.close();
            }
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
                e.getMessage();
        }
    }

    public ArrayList<Beacon> findAll() {
        
        ResultSet rs = null;
        ArrayList<Beacon> beacons = new ArrayList<>();
        
        try {
            open();
            
            String query = "select * from " + TBL_NAME + " order by " + FIELD_ID;
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            while(rs.next()) {
            	Beacon beacon;
                beacon = new Beacon();
                beacon.setID_beacon(rs.getInt(FIELD_ID));
                beacon.setCoord_X(rs.getFloat(FIELD_COORD_X));
                beacon.setCoord_Y(rs.getFloat(FIELD_COORD_Y));
                beacon.setID_pdi(rs.getInt(FIELD_ID_PDI));
                beacon.setInd_NDC(rs.getFloat(FIELD_NDC));
                beacon.setInd_fumi(rs.getFloat(FIELD_FUMI));
                beacon.setInd_fuoco(rs.getFloat(FIELD_FUOCO));
                beacon.setInd_rischio(rs.getFloat(FIELD_RISCHIO));
                beacons.add(beacon);
            }
        } 
        catch (SQLException e) {
        	e.getMessage();
        }
        finally {
            close();
        }
        
        return beacons;
    }
    
    Beacon findById(Integer id_beacon) { //TODO da implementare 
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates. 
    }
    
    public ArrayList<Beacon> findByIDMappa(Integer id_mappa) {
        
        ResultSet rs = null;
        ArrayList<Beacon> beacons = new ArrayList<>();
        
        try {
            open();
            
            String query = "select * from " + TBL_NAME + " where " + FIELD_ID_MAPPA +
                    "=? order by " + FIELD_ID;
            st = conn.prepareStatement(query);
            st.setInt(1, id_mappa);
            rs = st.executeQuery();
            while(rs.next()) {
            	Beacon beacon;
                beacon = new Beacon();
                beacon.setID_beacon(rs.getInt(FIELD_ID));
                beacon.setCoord_X(rs.getFloat(FIELD_COORD_X));
                beacon.setCoord_Y(rs.getFloat(FIELD_COORD_Y));
                beacon.setID_pdi(rs.getInt(FIELD_ID_PDI));
                beacon.setInd_NDC(rs.getFloat(FIELD_NDC));
                beacon.setInd_fumi(rs.getFloat(FIELD_FUMI));
                beacon.setInd_fuoco(rs.getFloat(FIELD_FUOCO));
                beacon.setInd_rischio(rs.getFloat(FIELD_RISCHIO));
                beacons.add(beacon);
            }
        } 
        catch (SQLException e) {
        	e.getMessage();
        }
        finally {
            close();
        }
        
        return beacons;
    }
    
}
