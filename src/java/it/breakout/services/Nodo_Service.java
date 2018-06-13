/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.services;

import it.breakout.models.Nodo;
import it.breakout.models.Pdi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author costantino
 */
public class Nodo_Service {
    
    private PreparedStatement st = null;
    private Connection conn = null;
    
    public static final String TBL_NAME = "nodo";
    public static final String FIELD_ID = "id_nodo";
    public static final String FIELD_CODICE = "codice";
    public static final String FIELD_ID_MAPPA = "id_mappa";
    public static final String FIELD_IS_PDI = "is_pdi";
    
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
                e.printStackTrace();
        }
    }
    
    public ArrayList<Nodo> findAllNodes() {
        ResultSet rs = null;
        ArrayList<Nodo> nodi = new ArrayList<>();
        try {
            open();
            
            String query = "select * from " + TBL_NAME + " where " + FIELD_IS_PDI + "=false order by codice";
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            while(rs.next()) {
                Nodo nodo = new Nodo();
                nodo.setCodice(rs.getString("codice"));
                nodo.setCoord_X(rs.getFloat("coordinata_x"));
                nodo.setCoord_Y(rs.getFloat("coordinata_y"));
                nodo.setLarghezza(rs.getFloat("larghezza"));
                nodi.add(nodo);
            }
        } 
        catch (SQLException e) {
        	e.printStackTrace();
        }
        finally {
            close();
        }
        
        return nodi;
    }

    public ArrayList<Pdi> findAllPois() {
        ResultSet rs = null;
        ArrayList<Pdi> pdis = new ArrayList<>();
        try {
            open();
            
            String query = "select * from " + TBL_NAME + " where " + FIELD_IS_PDI + "=true order by codice";
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            while(rs.next()) {
                Pdi pdi = new Pdi();
                pdi.setCodice(rs.getString("codice"));
                pdi.setCoord_X(rs.getFloat("coordinata_x"));
                pdi.setCoord_Y(rs.getFloat("coordinata_y"));
                pdi.setLarghezza(rs.getFloat("larghezza"));
                pdi.setTipo(rs.getString("tipo"));
                pdis.add(pdi);
            }
        } 
        catch (SQLException e) {
        	e.printStackTrace();
        }
        finally {
            close();
        }
        
        return pdis;
    }
    
    public ArrayList<Nodo> findByIDMappa(int search_id) {
        ResultSet rs = null;
        ArrayList<Nodo> nodi = new ArrayList<>();
        try {
            open();
            
            String query = "select * from " + TBL_NAME + " where " + FIELD_ID_MAPPA + "=? AND "
                    + FIELD_IS_PDI + "=false order by codice";
            st = conn.prepareStatement(query);
            st.setInt(1, search_id);
            rs = st.executeQuery();
            while(rs.next()) {
                Nodo nodo = new Nodo();
                nodo.setCodice(rs.getString("codice"));
                nodo.setCoord_X(rs.getFloat("coordinata_x"));
                nodo.setCoord_Y(rs.getFloat("coordinata_y"));
                nodo.setLarghezza(rs.getFloat("larghezza"));
                nodi.add(nodo);
            }
        } 
        catch (SQLException e) {
        	e.printStackTrace();
        }
        finally {
            close();
        }
        
        return nodi;
    }
    
    public ArrayList<Pdi> findPoisByIDMappa(int search_id) {
        ResultSet rs = null;
        ArrayList<Pdi> pdis = new ArrayList<>();
        try {
            open();
            
            String query = "select * from " + TBL_NAME + " where " + FIELD_ID_MAPPA + "=? AND "
                    + FIELD_IS_PDI + "=true order by codice";
            st = conn.prepareStatement(query);
            st.setInt(1, search_id);
            rs = st.executeQuery();
            while(rs.next()) {
                Pdi pdi = new Pdi();
                pdi.setCodice(rs.getString("codice"));
                pdi.setCoord_X(rs.getFloat("coordinata_x"));
                pdi.setCoord_Y(rs.getFloat("coordinata_y"));
                pdi.setLarghezza(rs.getFloat("larghezza"));
                pdis.add(pdi);
            }
        } 
        catch (SQLException e) {
        	e.printStackTrace();
        }
        finally {
            close();
        }
        
        return pdis;
    }
}
