/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.services;

import it.breakout.models.Collegamento;
import it.breakout.models.Scala;
import it.breakout.models.Tronco;
import it.breakout.models.Beacon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import static it.breakout.utility.EnvVariables.DB_PSW;
import static it.breakout.utility.EnvVariables.DB_URL;
import static it.breakout.utility.EnvVariables.DB_USR;
import java.sql.Types;
import javafx.animation.KeyValue;

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
        conn = DriverManager.getConnection(DB_URL, DB_USR, DB_PSW);
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
                System.out.println(e.getMessage());
        }
    }

    /* I tronchi sono i segmenti che collegano due nodi della stessa mappa */
    public ArrayList<Tronco> findAllArcs(Integer id_mappa) {
        ResultSet rs = null;
        ArrayList<Tronco> tronchi = new ArrayList<>();
        try {
            open();
            
            String query = "select * from " + TBL_NAME + " where " + FIELD_ID_MAPPA + "=?";

            st = conn.prepareStatement(query);
            st.setInt(1, id_mappa);
            rs = st.executeQuery();
            while(rs.next()) {
                Tronco tronco = new Tronco();
                tronco.setID(rs.getInt(FIELD_ID));
                tronco.setLunghezza(rs.getDouble(FIELD_LUNGHEZZA));
                tronco.setNodiInteger(rs.getInt(FIELD_ID_N1), rs.getInt(FIELD_ID_N2));
                tronco.setID_beacon(rs.getInt(FIELD_ID_BEACON));
                tronco.setID_mappa(rs.getInt(FIELD_ID_MAPPA));
                tronco.setID_piano(rs.getInt(FIELD_ID_PIANO));
                tronco.setCodice();
                tronchi.add(tronco);
            }
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        } finally {
            close();
        }
        
        return tronchi;
    }

    /* Le scale sono i segmenti che collegano i nodi di due piani diversi, pertanto
     * sono i tronchi che nella tabella del DB hanno ID mappa e ID piano settati a 
     * NULL
     */
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
                scala.setID(rs.getInt(FIELD_ID));
                scala.setLunghezza(rs.getDouble(FIELD_LUNGHEZZA));
                scala.setNodiInteger(rs.getInt(FIELD_ID_N1), rs.getInt(FIELD_ID_N2));
                scala.setID_beacon(rs.getInt(FIELD_ID_BEACON));
                scala.setCodice();
                scale.add(scala);
            }
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            close();
        }
        
        return scale;
    }
    
    /* I collegamenti sono i segmenti che collegano i nodi di due mappe diversi,
     * dello stesso piano, pertanto sono i tronchi che nella tabella del DB hanno
     * ID mappa settato a NULL 
     */
    public ArrayList<Collegamento> findAllLinks() {
        ResultSet rs = null;
        ArrayList<Collegamento> collegamenti = new ArrayList<>();
        try {
            
            open();
            
            String query = "select * from " + TBL_NAME + " where " + FIELD_ID_MAPPA + " is null and "
                    + FIELD_ID_PIANO + "=?";
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            while(rs.next()) {
                Collegamento collegamento = new Collegamento();
                collegamento.setID(rs.getInt(FIELD_ID));
                collegamento.setLunghezza(rs.getDouble(FIELD_LUNGHEZZA));
                collegamento.setNodiInteger(rs.getInt(FIELD_ID_N1), rs.getInt(FIELD_ID_N2));
                collegamento.setID_beacon(rs.getInt(FIELD_ID_BEACON));
                collegamento.setID_piano(rs.getInt(FIELD_ID_PIANO));
                collegamento.setCodice();
                collegamenti.add(collegamento);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
        
        return collegamenti;
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
            System.out.println(e.getMessage());
        }
        finally {
            close();
        }
        
        return arcs;
    }
    
    public Tronco findArcByID(Integer search_id) {
        
        ResultSet rs = null;
        Tronco tronco = new Tronco();

        try {

            open();
            
            String query = "select * from " + TBL_NAME + " where "
                    + FIELD_ID + "= ?";
            st = conn.prepareStatement(query);
            st.setInt(1, search_id);
            rs = st.executeQuery();
            
            while(rs.next()) {
                tronco.setID(rs.getInt(FIELD_ID));
                tronco.setNodiInteger(rs.getInt(FIELD_ID_N1), rs.getInt(FIELD_ID_N2));
                tronco.setID_beacon(rs.getInt(FIELD_ID_BEACON));
                tronco.setLunghezza(rs.getDouble(FIELD_LUNGHEZZA));
                tronco.setID_mappa(rs.getInt(FIELD_ID_MAPPA));
                tronco.setCodice();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
        
        return tronco;

    }
    
    public Tronco findArcByNodi(Integer id_nodo_1, Integer id_nodo_2) {
        
        ResultSet rs = null;
        Tronco tronco = new Tronco();

        try {

            open();
            
            String query = "select * from " + TBL_NAME + " where "
                    + FIELD_ID_N1 + "= ? and " + FIELD_ID_N2 + "=?";
            st = conn.prepareStatement(query);
            st.setInt(1, id_nodo_1);
            st.setInt(2, id_nodo_2);
            rs = st.executeQuery();
            
            while(rs.next()) {
                tronco.setID(rs.getInt(FIELD_ID));
                tronco.setNodiInteger(rs.getInt(FIELD_ID_N1), rs.getInt(FIELD_ID_N2));
                tronco.setID_beacon(rs.getInt(FIELD_ID_BEACON));
                tronco.setLunghezza(rs.getDouble(FIELD_LUNGHEZZA));
                tronco.setID_mappa(rs.getInt(FIELD_ID_MAPPA));
                tronco.setCodice();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
        
        return tronco;

    }
    
    private Beacon getBeacon(Integer id_beacon) {
        Beacon_Service beaconSrv = new Beacon_Service();
        return beaconSrv.findByID(id_beacon);
    }

    public Scala findByIDGeneric(Integer id) {

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
            System.out.println(e.getMessage());
        }

        finally {
            close();
        }
        
        return arc;

    }
    
    public void insertTronco(Tronco tronco) {
        
        try {
            
            open();
            
            String query = "insert into " + TBL_NAME
                    + "(" + FIELD_LUNGHEZZA + ","
                    + FIELD_ID_N1 + ","
                    + FIELD_ID_N2 + ","
                    + FIELD_ID_BEACON + ","
                    + FIELD_ID_MAPPA + ","
                    + FIELD_ID_PIANO + ") values (?,?,?,?,?,?)";
            
            Integer[] nodi = tronco.getNodiInteger();
            
            st = conn.prepareStatement(query);
            st.setDouble(1, 999);
            st.setInt(2, nodi[0]);
            st.setInt(3, nodi[1]);
            st.setInt(4, tronco.getID_beacon());
            st.setInt(5, tronco.getID_mappa());
            st.setInt(6, tronco.getID_piano());

            st.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }
    
    public void insertScala(Scala scala) {
        
        try {
            
            open();
            
            String query = "insert into " + TBL_NAME
                    + "(" + FIELD_LUNGHEZZA + ","
                    + FIELD_ID_N1 + ","
                    + FIELD_ID_N2 + ","
                    + FIELD_ID_BEACON + ","
                    + FIELD_ID_MAPPA + ","
                    + FIELD_ID_PIANO + ") values (?,?,?,?,?,?)";
            
            Integer[] nodi = scala.getNodiInteger();
            
            st = conn.prepareStatement(query);
            st.setDouble(1, 999);
            st.setInt(2, nodi[0]);
            st.setInt(3, nodi[1]);
            st.setInt(4, scala.getID_beacon());
            st.setNull(5, Types.INTEGER);
             st.setNull(6, Types.INTEGER);

            st.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }
    
    public void insertCollegamento(Collegamento collegamento) {
        
        try {
            
            open();
            
            String query = "insert into " + TBL_NAME
                    + "(" + FIELD_LUNGHEZZA + ","
                    + FIELD_ID_N1 + ","
                    + FIELD_ID_N2 + ","
                    + FIELD_ID_BEACON + ","
                    + FIELD_ID_MAPPA + ","
                    + FIELD_ID_PIANO + ") values (?,?,?,?,?,?)";
            
            Integer[] nodi = collegamento.getNodiInteger();
            
            st = conn.prepareStatement(query);
            st.setDouble(1, 999);
            st.setInt(2, nodi[0]);
            st.setInt(3, nodi[1]);
            st.setInt(4, collegamento.getID_beacon());
            st.setNull(5, Types.INTEGER);
            st.setInt(6, collegamento.getID_piano());

            st.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }
    
    public void updateTronco(Tronco tronco, Integer id_tronco) {
        
        try {
            
            open();
            
            String query = "update " + TBL_NAME + " set "
                    + FIELD_LUNGHEZZA + "=?, "
                    + FIELD_ID_N1 + "=?, "
                    + FIELD_ID_N2 + "=?, "
                    + FIELD_ID_BEACON + "=? "
                    + "where " + FIELD_ID + "=?";
            
            Integer[] nodi = tronco.getNodiInteger();
            
            st = conn.prepareStatement(query);
            st.setDouble(1, 999);
            st.setInt(2, nodi[0]);
            st.setInt(3, nodi[1]);
            st.setInt(4, tronco.getID_beacon());
            st.setInt(5, id_tronco);            

            st.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }
    
    
    /* Il metodo elimina è identico sia per i tronchi che per le scale
     * che per i collegamenti
     */
    public void delete(Integer id_del) {
        
        try {
            open();
            
            String query = "delete from " + TBL_NAME + " where " + FIELD_ID + "=?";
            st = conn.prepareStatement(query);
            st.setInt(1, id_del);
            st.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }
}
