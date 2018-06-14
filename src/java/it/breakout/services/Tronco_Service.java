/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.services;

import it.breakout.models.Collegamento;
import it.breakout.models.Scala;
import it.breakout.models.Tronco;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import it.breakout.models.Beacon;

/**
 *
 * @author costantino
 */
public class Tronco_Service {
    
    private PreparedStatement st = null;
    private Connection conn = null;
    
    public static final String TBL_NAME = "tronco";
    public static final String FIELD_ID = "id_tronco";
    public static final String FIELD_LUNGHEZZA = "lunghezza";
    public static final String FIELD_ID_N1 = "id_nodo1";
    public static final String FIELD_ID_N2 = "id_nodo2";
    public static final String FIELD_ID_BEACON = "id_beacon";
    public static final String FIELD_ID_MAPPA = "id_mappa";
    public static final String FIELD_ID_PIANO = "id_piano";
    
    
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

    public ArrayList<Tronco> findAllArcs() {
        ResultSet rs = null;
        ArrayList<Tronco> tronchi = new ArrayList<>();
        try {
            open();
            
            String query = "select * from " + TBL_NAME + " order by id_tronco";
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            while(rs.next()) {
                Tronco tronco = new Tronco();
                tronco.setID(rs.getInt("id_tronco"));
                tronco.setLunghezza(rs.getDouble("lunghezza"));
                tronco.setCodice();
                tronco.setNodiInteger(rs.getInt("id_nodo1"), rs.getInt("id_nodo2"));
                tronchi.add(tronco);
            }
        } 
        catch (SQLException e) {
        	e.getMessage();
        }
        finally {
            close();
        }
        
        return tronchi;
    }

    public ArrayList<Scala> findAllStairs() {
        ResultSet rs = null;
        ArrayList<Scala> scale = new ArrayList<>();
        try {
            open();
            
            String query = "select * from " + TBL_NAME + " where " + FIELD_ID_MAPPA + " is null and "
                    + FIELD_ID_PIANO + " is null";
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            while(rs.next()) {
                Scala scala = new Scala();
                scala.setID(rs.getInt("id_tronco"));
                scala.setCodice();
                scala.setLunghezza(rs.getDouble("lunghezza"));
                scala.setNodiInteger(rs.getInt("id_nodo1"), rs.getInt("id_nodo2"));
                scale.add(scala);
            }
        } 
        catch (SQLException e) {
        	e.getMessage();
        }
        finally {
            close();
        }
        
        return scale;
    }
    
    public ArrayList<Collegamento> findAllLinks() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Integer[] getArcsByNode_Integer(Integer id_nodo) {

        ResultSet rs = null;
        Integer[] arcs = null;

        try {
            open();
            
            String query = "select * from " + TBL_NAME + " where " + FIELD_ID_N1 + " = ? OR "
                    + FIELD_ID_N2 + " = ? ";
            st = conn.prepareStatement(query);
            st.setInt(1, id_nodo);
            st.setInt(2, id_nodo);
            rs = st.executeQuery();
            int i = 0;
            
            while(rs.next()) {
                arcs[i] = rs.getInt(FIELD_ID);
                i++;
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        finally {
            close();
        }
        
        return arcs;
    }
    
    private Beacon getBeacon(Integer id_beacon) {
        Beacon_Service beaconSrv = new Beacon_Service();
        return beaconSrv.findById(id_beacon);
    }

    public Scala findByIdGeneric(Integer id) {

        ResultSet rs = null;
        Scala arc = new Scala();

        try {

            open();
            
            String query = "select * from " + TBL_NAME + " where " + FIELD_ID + "= ?";
            st = conn.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();
            
            while(rs.next()) {
                arc.setID(id);
                Integer[] nodes = null;
                nodes[0] = rs.getInt(FIELD_ID_N1);
                nodes[1] = rs.getInt(FIELD_ID_N2);
                arc.setNodiInteger(nodes[0], nodes[1]);
                arc.setLarghezza_media();
                arc.setBeacon(getBeacon(rs.getInt(FIELD_ID_BEACON)));
                arc.setCosto_totale();
            }
        }

        catch (SQLException e) {
            e.getMessage();
        }

        finally {
            close();
        }
        
        return arc;

    }
}
