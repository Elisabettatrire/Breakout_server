/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;

import it.breakout.models.Beacon;
import it.breakout.models.Modifica;
import it.breakout.services.ModificaService;

import static it.breakout.utility.EnvVariables.DB_PSW;
import static it.breakout.utility.EnvVariables.DB_URL;
import static it.breakout.utility.EnvVariables.DB_USR;

/**
 *
 * @author costantino
 */
public class BeaconService {
    
    private PreparedStatement st = null;
    private Connection conn = null;
    
    public static final String TBL_NAME = "beacon";
    public static final String FIELD_ID = "id_beacon";
    public static final String FIELD_ADDR = "address";
    public static final String FIELD_COORD_X = "coordinata_x";
    public static final String FIELD_COORD_Y = "coordinata_y";
    public static final String FIELD_FUOCO = "fuoco";
    public static final String FIELD_FUMI = "fumi";
    public static final String FIELD_NCD = "ncd";
    public static final String FIELD_RISCHIO = "rischio";
    public static final String FIELD_ID_PDI = "id_pdi";
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
                beacon.setAddress(rs.getString(FIELD_ADDR));
                beacon.setCoord_X(rs.getDouble(FIELD_COORD_X));
                beacon.setCoord_Y(rs.getDouble(FIELD_COORD_Y));
                beacon.setID_pdi(rs.getInt(FIELD_ID_PDI));
                if(rs.wasNull()){
                    beacon.setID_pdi(null);
                }
                beacon.setInd_NCD(rs.getDouble(FIELD_NCD));
                beacon.setInd_fumi(rs.getDouble(FIELD_FUMI));
                beacon.setInd_fuoco(rs.getDouble(FIELD_FUOCO));
                beacon.setInd_rischio(rs.getDouble(FIELD_RISCHIO));
                beacon.setID_mappa(rs.getInt(FIELD_ID_MAPPA));
                beacon.setID_piano(rs.getInt(FIELD_ID_PIANO));
                beacons.add(beacon);
            }
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            close();
        }
        
        return beacons;
    }
    
    public Beacon findByID(Integer search_id) {
        ResultSet rs = null;
        Beacon beacon = new Beacon();

        try {
            open();
            
            String query = "select * from " + TBL_NAME + " where " + FIELD_ID + "= ?";
            st = conn.prepareStatement(query);
            st.setInt(1, search_id);
            rs = st.executeQuery();

            while(rs.next()) {
                beacon.setID_beacon(search_id);
                beacon.setAddress(rs.getString(FIELD_ADDR));
                beacon.setCoord_X(rs.getDouble(FIELD_COORD_X));
                beacon.setCoord_Y(rs.getDouble(FIELD_COORD_Y));
                beacon.setID_pdi(rs.getInt(FIELD_ID_PDI));
                if(rs.wasNull()){
                    beacon.setID_pdi(null);
                }
                beacon.setInd_NCD(rs.getDouble(FIELD_NCD));
                beacon.setInd_fumi(rs.getDouble(FIELD_FUMI));
                beacon.setInd_fuoco(rs.getDouble(FIELD_FUOCO));
                beacon.setInd_rischio(rs.getDouble(FIELD_RISCHIO));
                beacon.setID_mappa(rs.getInt(FIELD_ID_MAPPA));
                beacon.setID_piano(rs.getInt(FIELD_ID_PIANO));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
        
        return beacon;
    }
    
    public Beacon findByCodice(String search_code){

        ResultSet rs = null;
        Beacon beacon = new Beacon();

        try {
            open();
            
            String query = "select * from " + TBL_NAME + " where " + FIELD_ADDR + "= ?";
            st = conn.prepareStatement(query);
            st.setString(1, search_code);
            rs = st.executeQuery();

            while(rs.next()) {
                beacon.setID_beacon(rs.getInt(FIELD_ID));
                beacon.setAddress(search_code);
                beacon.setCoord_X(rs.getDouble(FIELD_COORD_X));
                beacon.setCoord_Y(rs.getDouble(FIELD_COORD_Y));
                beacon.setID_pdi(rs.getInt(FIELD_ID_PDI));
                if(rs.wasNull()){
                    beacon.setID_pdi(null);
                }
                beacon.setInd_NCD(rs.getDouble(FIELD_NCD));
                beacon.setInd_fumi(rs.getDouble(FIELD_FUMI));
                beacon.setInd_fuoco(rs.getDouble(FIELD_FUOCO));
                beacon.setInd_rischio(rs.getDouble(FIELD_RISCHIO));
                beacon.setID_mappa(rs.getInt(FIELD_ID_MAPPA));
                beacon.setID_piano(rs.getInt(FIELD_ID_PIANO));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
        
        return beacon;
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
                beacon.setAddress(rs.getString(FIELD_ADDR));
                beacon.setCoord_X(rs.getDouble(FIELD_COORD_X));
                beacon.setCoord_Y(rs.getDouble(FIELD_COORD_Y));
                beacon.setID_pdi(rs.getInt(FIELD_ID_PDI));
                if(rs.wasNull()){
                    beacon.setID_pdi(null);
                }
                beacon.setInd_NCD(rs.getDouble(FIELD_NCD));
                beacon.setInd_fumi(rs.getDouble(FIELD_FUMI));
                beacon.setInd_fuoco(rs.getDouble(FIELD_FUOCO));
                beacon.setInd_rischio(rs.getDouble(FIELD_RISCHIO));
                beacon.setID_mappa(id_mappa);
                beacon.setID_piano(rs.getInt(FIELD_ID_PIANO));
                beacons.add(beacon);
            }
        } 
        catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        finally {
            close();
        }
        
        return beacons;
    }
    
    public ArrayList<Beacon> findByIDPiano(Integer search_id) {
        
        ResultSet rs = null;
        ArrayList<Beacon> beacons = new ArrayList<>();
        
        try {
            open();
            
            String query = "select * from " + TBL_NAME + " where " + FIELD_ID_PIANO +
                    "=? order by " + FIELD_ID;
            st = conn.prepareStatement(query);
            st.setInt(1, search_id);
            rs = st.executeQuery();
            while(rs.next()) {
            	Beacon beacon;
                beacon = new Beacon();
                beacon.setID_beacon(rs.getInt(FIELD_ID));
                beacon.setAddress(rs.getString(FIELD_ADDR));
                beacon.setCoord_X(rs.getDouble(FIELD_COORD_X));
                beacon.setCoord_Y(rs.getDouble(FIELD_COORD_Y));
                beacon.setID_pdi(rs.getInt(FIELD_ID_PDI));
                if(rs.wasNull()){
                    beacon.setID_pdi(null);
                }
                beacon.setInd_NCD(rs.getDouble(FIELD_NCD));
                beacon.setInd_fumi(rs.getDouble(FIELD_FUMI));
                beacon.setInd_fuoco(rs.getDouble(FIELD_FUOCO));
                beacon.setInd_rischio(rs.getDouble(FIELD_RISCHIO));
                beacon.setID_mappa(rs.getInt(FIELD_ID_MAPPA));
                beacon.setID_piano(search_id);
                beacons.add(beacon);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            close();
        }
        
        return beacons;
    }
    
    public void insert(Beacon beacon) {
            
        try{
            
            open();
            
            String query = "insert into " + TBL_NAME
                    + " (" + FIELD_ADDR + ","
                    + FIELD_COORD_X + ","
                    + FIELD_COORD_Y + ","
                    + FIELD_FUOCO + ","
                    + FIELD_FUMI + ","
                    + FIELD_NCD + ","
                    + FIELD_RISCHIO + ","
                    + FIELD_ID_PDI + ","
                    + FIELD_ID_PIANO + ","
                    + FIELD_ID_MAPPA + ") values(?,?,?,?,?,?,?,?,?,?)";

            st = conn.prepareStatement(query);
            st.setString(1, beacon.getAddress());
            st.setDouble(2, beacon.getCoord_X());
            st.setDouble(3, beacon.getCoord_Y());
            st.setDouble(4, 0.0);
            st.setDouble(5, 0.0);
            st.setDouble(6, 0.0);
            st.setDouble(7, 0.0);
            if(beacon.getID_pdi() != null) {
                st.setInt(8, beacon.getID_pdi());
            } else {
                st.setNull(8, Types.INTEGER);
            }
            st.setInt(9, beacon.getID_piano());
            st.setInt(10, beacon.getID_mappa());

            st.executeUpdate();
            
            // Log della modifica nel DB
            Modifica modifica = new Modifica();
            ModificaService modificaService = new ModificaService();
            modifica.setTabella(TBL_NAME);
            modifica.setTipo("Inserimento beacon");
            modificaService.insert(modifica);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }
    
    public void update(Beacon beacon, Integer id_beacon) {
        try {
            open();
            
            String query = "update " + TBL_NAME + " set "
                    + FIELD_ADDR + "=?, "
                    + FIELD_COORD_X + "=?, "
                    + FIELD_COORD_Y + "=?, "
                    + FIELD_ID_PDI + "=? "
                    + "where " + FIELD_ID + "=?";
            st = conn.prepareStatement(query);
            st.setString(1, beacon.getAddress());
            st.setDouble(2, beacon.getCoord_X());
            st.setDouble(3, beacon.getCoord_Y());
            if(beacon.getID_pdi() != null) {
                st.setInt(4, beacon.getID_pdi());
            } else {
                st.setNull(4, Types.INTEGER);
            }
            st.setInt(5, id_beacon);
            
            st.executeUpdate();
            
            // Log della modifica nel DB
            Modifica modifica = new Modifica();
            ModificaService modificaService = new ModificaService();
            modifica.setTabella(TBL_NAME);
            modifica.setTipo("Modifica beacon");
            modificaService.insert(modifica);
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }
    
    public void delete(Integer id_beacon) {
        
        try {
            open();
            
            String query = "delete from " + TBL_NAME + " where " + FIELD_ID + "=?";
            st = conn.prepareStatement(query);
            st.setInt(1, id_beacon);
            st.executeUpdate();
            
            // Log della modifica nel DB
            Modifica modifica = new Modifica();
            ModificaService modificaService = new ModificaService();
            modifica.setTabella(TBL_NAME);
            modifica.setTipo("Eliminazione beacon");
            modificaService.insert(modifica);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }
    
    public void deleteAll() {
        
        try {
            open();
            
            String query = "truncate table " + TBL_NAME;
            st = conn.prepareStatement(query);
            st.executeUpdate();
            
            // Log della modifica nel DB
            Modifica modifica = new Modifica();
            ModificaService modificaService = new ModificaService();
            modifica.setTabella(TBL_NAME);
            modifica.setTipo("Svuotamento tabella beacon");
            modificaService.insert(modifica);
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }
    
    public void updateNCD(Beacon beacon) {
        try {
            open();
            
            String query = "update " + TBL_NAME + " set "
                    + FIELD_NCD + " = ? "
                    + " where " + FIELD_ID + " = ? ";
            st = conn.prepareStatement(query);
            st.setDouble(1, beacon.getInd_NCD());
            st.setInt(2, beacon.getID_beacon());
            
            st.executeUpdate();
            
            // Log della modifica nel DB
            Modifica modifica = new Modifica();
            ModificaService modificaService = new ModificaService();
            modifica.setTabella(TBL_NAME);
            modifica.setTipo("Modifica beacon");
            modificaService.insert(modifica);
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }
    
}
