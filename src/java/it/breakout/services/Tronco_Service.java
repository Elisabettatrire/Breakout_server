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
                e.printStackTrace();
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
                tronco.setNodiLong(rs.getLong("id_nodo1"), rs.getLong("id_nodo2"));
                tronchi.add(tronco);
            }
        } 
        catch (SQLException e) {
        	e.printStackTrace();
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
                scala.setLunghezza(rs.getDouble("lunghezza"));
                scala.setNodiLong(rs.getLong("id_nodo1"), rs.getLong("id_nodo2"));
                scale.add(scala);
            }
        } 
        catch (SQLException e) {
        	e.printStackTrace();
        }
        finally {
            close();
        }
        
        return scale;
    }
    
    public ArrayList<Collegamento> findAllLinks() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
