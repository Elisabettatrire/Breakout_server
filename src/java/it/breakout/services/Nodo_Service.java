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

import static it.breakout.utility.EnvVariables.DB_PSW;
import static it.breakout.utility.EnvVariables.DB_URL;
import static it.breakout.utility.EnvVariables.DB_USR;

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
    public static final String FIELD_IS_PDI = "is_pdi";
    public static final String FIELD_TIPO = "tipo";
    public static final String FIELD_COORD_X = "coordinata_x";
    public static final String FIELD_COORD_Y = "coordinata_y";
    public static final String FIELD_WIDTH = "larghezza";
    public static final String FIELD_LENGTH = "lunghezza";
    public static final String FIELD_ID_MAPPA = "id_mappa";
    
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
    
    public ArrayList<Nodo> findAll() {
        
        ResultSet rs = null;
        ArrayList<Nodo> nodi = new ArrayList<>();
        
        try {
            open();
            
            String query = "select * from " + TBL_NAME + " where " + FIELD_IS_PDI + "=false "
                    + "order by " + FIELD_CODICE;
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            while(rs.next()) {
                Nodo nodo = new Nodo();
                nodo.setID(rs.getInt(FIELD_ID));
                nodo.setCodice(rs.getString(FIELD_CODICE));
                nodo.setCoord_X(rs.getDouble(FIELD_COORD_X));
                nodo.setCoord_Y(rs.getDouble(FIELD_COORD_Y));
                nodo.setLarghezza(rs.getDouble(FIELD_WIDTH));
                nodo.setID_mappa(rs.getInt(FIELD_ID_MAPPA));
                nodi.add(nodo);
            }
        } 
        catch (SQLException e) {
        	System.out.println(e.getMessage());
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
            
            String query = "select * from " + TBL_NAME + " where " + FIELD_IS_PDI + "=true"
                    + "order by " + FIELD_CODICE;
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            while(rs.next()) {
                Pdi pdi = new Pdi();
                pdi.setID(rs.getInt(FIELD_ID));
                pdi.setCodice(rs.getString(FIELD_CODICE));
                pdi.setTipo(rs.getString(FIELD_TIPO));
                pdi.setCoord_X(rs.getDouble(FIELD_COORD_X));
                pdi.setCoord_Y(rs.getDouble(FIELD_COORD_Y));
                pdi.setLarghezza(rs.getDouble(FIELD_WIDTH));
                pdi.setLunghezza(rs.getDouble(FIELD_LENGTH));
                pdi.setID_mappa(rs.getInt(FIELD_ID_MAPPA));
                pdis.add(pdi);
            }
        } 
        catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        finally {
            close();
        }
        
        return pdis;
    }
    
    private Integer[] getStar_Integer(Integer id_nodo) {
        Tronco_Service troncoSrv = new Tronco_Service();
        return troncoSrv.getArcsByNode_Integer(id_nodo);
    }

    public Nodo findByID(Integer search_id){

        ResultSet rs = null;
        Nodo nodo = new Nodo();

        try {
            open();
            
            String query = "select * from " + TBL_NAME + " where " + FIELD_ID + "= ?";
            st = conn.prepareStatement(query);
            st.setInt(1, search_id);
            rs = st.executeQuery();

            while(rs.next()) {
                nodo.setID(search_id);
                nodo.setCodice(rs.getString(FIELD_CODICE));
                nodo.setCoord_X(rs.getDouble(FIELD_COORD_X));
                nodo.setCoord_Y(rs.getDouble(FIELD_COORD_Y));
                nodo.setID_mappa(rs.getInt(FIELD_ID_MAPPA));
                nodo.setTronchi_stella_int(getStar_Integer(search_id));
                nodo.setLarghezza(rs.getDouble(FIELD_WIDTH));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
        
        return nodo;
    }
    
    public Pdi findPoiByID(Integer search_id){

        ResultSet rs = null;
        Pdi pdi = new Pdi();

        try {
            open();
            
            String query = "select * from " + TBL_NAME + " where " + FIELD_ID + "= ?";
            st = conn.prepareStatement(query);
            st.setInt(1, search_id);
            rs = st.executeQuery();

            while(rs.next()) {
                pdi.setID(search_id);
                pdi.setCodice(rs.getString(FIELD_CODICE));
                pdi.setTipo(rs.getString(FIELD_TIPO));
                pdi.setCoord_X(rs.getDouble(FIELD_COORD_X));
                pdi.setCoord_Y(rs.getDouble(FIELD_COORD_Y));
                pdi.setID_mappa(rs.getInt(FIELD_ID_MAPPA));
                pdi.setTronchi_stella_int(getStar_Integer(search_id));
                pdi.setLarghezza(rs.getDouble(FIELD_WIDTH));
                pdi.setLunghezza(rs.getDouble(FIELD_LENGTH));
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
        
        return pdi;
    }
    
    public Nodo findByCodice(String search_code){

        ResultSet rs = null;
        Nodo nodo = new Nodo();

        try {
            open();
            
            String query = "select * from " + TBL_NAME + " where " + FIELD_CODICE + "= ?";
            st = conn.prepareStatement(query);
            st.setString(1, search_code);
            rs = st.executeQuery();

            while(rs.next()) {
                nodo.setID(rs.getInt(FIELD_ID));
                nodo.setCodice(search_code);
                nodo.setCoord_X(rs.getDouble(FIELD_COORD_X));
                nodo.setCoord_Y(rs.getDouble(FIELD_COORD_Y));
                nodo.setID_mappa(rs.getInt(FIELD_ID_MAPPA));
                nodo.setTronchi_stella_int(getStar_Integer(rs.getInt(FIELD_ID)));
                nodo.setLarghezza(rs.getDouble(FIELD_WIDTH));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
        
        return nodo;
    }
    
    public ArrayList<Nodo> findByIDMappa(Integer search_id) {
        ResultSet rs = null;
        ArrayList<Nodo> nodi = new ArrayList<>();
        try {
            open();
            
            String query = "select * from " + TBL_NAME + " where " + FIELD_ID_MAPPA + "=? AND "
                    + FIELD_IS_PDI + "=false order by " + FIELD_CODICE;
            st = conn.prepareStatement(query);
            st.setInt(1, search_id);
            rs = st.executeQuery();
            while(rs.next()) {
                Nodo nodo = new Nodo();
                nodo.setID(rs.getInt(FIELD_ID));
                nodo.setCodice(rs.getString(FIELD_CODICE));
                nodo.setCoord_X(rs.getDouble(FIELD_COORD_X));
                nodo.setCoord_Y(rs.getDouble(FIELD_COORD_Y));
                nodo.setLarghezza(rs.getDouble(FIELD_WIDTH));
                nodo.setID_mappa(search_id);
                nodi.add(nodo);
            }
        } 
        catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        finally {
            close();
        }
        
        return nodi;
    }
    
    public ArrayList<Pdi> findPoisByIDMappa(Integer search_id) {
        ResultSet rs = null;
        ArrayList<Pdi> pdis = new ArrayList<>();
        try {
            open();
            
            String query = "select * from " + TBL_NAME + " where " + FIELD_ID_MAPPA + "=? AND "
                    + FIELD_IS_PDI + "=true order by " + FIELD_CODICE;
            st = conn.prepareStatement(query);
            st.setInt(1, search_id);
            rs = st.executeQuery();
            while(rs.next()) {
                Pdi pdi = new Pdi();
                pdi.setID(rs.getInt(FIELD_ID));
                pdi.setCodice(rs.getString(FIELD_CODICE));
                pdi.setTipo(rs.getString(FIELD_TIPO));
                pdi.setCoord_X(rs.getDouble(FIELD_COORD_X));
                pdi.setCoord_Y(rs.getDouble(FIELD_COORD_Y));
                pdi.setLarghezza(rs.getDouble(FIELD_WIDTH));
                pdi.setLunghezza(rs.getDouble(FIELD_LENGTH));
                pdi.setID_mappa(search_id);
                pdis.add(pdi);
            }
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        } finally {
            close();
        }
        
        return pdis;
    }
    
    public void insertNodo(Nodo nodo) {
        
        try {
            open();
            
            String query = "insert into " + TBL_NAME
                    + " (" + FIELD_CODICE + ","
                    + FIELD_COORD_X + ","
                    + FIELD_COORD_Y + ","
                    + FIELD_WIDTH + ","
                    + FIELD_ID_MAPPA + ") "
                    + "values(?,?,?,?,?)";
            st = conn.prepareStatement(query);
            st.setString(1, nodo.getCodice());
            st.setDouble(2, nodo.getCoord_X());
            st.setDouble(3, nodo.getCoord_Y());
            st.setDouble(4, nodo.getLarghezza());
            st.setInt(5, nodo.getID_mappa());
            st.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }
    
    public void updateNodo(Nodo nodo, Integer id_nodo) {
        try {
            open();
            
            String query = "update " + TBL_NAME + " set "
                    + FIELD_CODICE + "=?, "
                    + FIELD_COORD_X + "=?, "
                    + FIELD_COORD_Y + "=?, "
                    + FIELD_WIDTH + "=? "
                    + "where " + FIELD_ID + "=?";
            st = conn.prepareStatement(query);
            st.setString(1, nodo.getCodice());
            st.setDouble(2, nodo.getCoord_X());
            st.setDouble(3, nodo.getCoord_Y());
            st.setDouble(4, nodo.getLarghezza());
            st.setInt(5, id_nodo);
            st.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }
    
    public void deleteNodo(Integer id_nodo) {
        
        try {
            open();
            
            String query = "delete from " + TBL_NAME + " where " + FIELD_ID + "=?";
            st = conn.prepareStatement(query);
            st.setInt(1, id_nodo);
            st.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }
    
    public void insertPdi(Pdi pdi) {
        
        try {
            open();
            
            String query = "insert into " + TBL_NAME
                    + " (" + FIELD_CODICE + ","
                    + FIELD_IS_PDI + ","
                    + FIELD_TIPO + ","
                    + FIELD_COORD_X + ","
                    + FIELD_COORD_Y + ","
                    + FIELD_WIDTH + ","
                    + FIELD_LENGTH + ","
                    + FIELD_ID_MAPPA + ") values(?,?,?,?,?,?,?,?)";
            st = conn.prepareStatement(query);
            st.setString(1, pdi.getCodice());
            st.setBoolean(2, true);
            st.setString(3, pdi.getTipo());
            st.setDouble(4, pdi.getCoord_X());
            st.setDouble(5, pdi.getCoord_Y());
            st.setDouble(6, pdi.getLarghezza());
            st.setDouble(7, pdi.getLunghezza());
            st.setInt(8,pdi.getID_mappa());
            st.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }
    
    public void updatePoi(Pdi pdi, Integer id_pdi) {
        try {
            open();
            
            String query = "update " + TBL_NAME + " set "
                    + FIELD_CODICE + "=?, "
                    + FIELD_TIPO + "=?, "
                    + FIELD_COORD_X + "=?, "
                    + FIELD_COORD_Y + "=?, "
                    + FIELD_WIDTH + "=?, "
                    + FIELD_LENGTH + "=? "
                    + "where " + FIELD_ID + "=?";
            st = conn.prepareStatement(query);
            st.setString(1, pdi.getCodice());
            st.setString(2, pdi.getTipo());
            st.setDouble(3, pdi.getCoord_X());
            st.setDouble(4, pdi.getCoord_Y());
            st.setDouble(5, pdi.getLarghezza());
            st.setDouble(6, pdi.getLunghezza());
            st.setInt(7, id_pdi);
            st.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }
}
