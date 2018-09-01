/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.services;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;

import it.breakout.models.Modifica;

import static it.breakout.utility.EnvVariables.DB_PSW;
import static it.breakout.utility.EnvVariables.DB_URL;
import static it.breakout.utility.EnvVariables.DB_USR;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author costantino
 */
public class ModificaService {
    
    private PreparedStatement st = null;
    private Connection conn = null;
    
    public static final String TBL_NAME = "modifiche";
    public static final String FIELD_ID = "id_modifica";
    public static final String FIELD_DATA = "data_modifica";
    public static final String FIELD_TIPO = "tipo_modifica";
    public static final String FIELD_TBL = "tabella";
    public static final String FIELD_OBJ = "id_oggetto_mod";    
    
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
    
    public void insert(Modifica modifica) {
        
        try{
            
            open();
            
            String query = "insert into " + TBL_NAME
                    + " (" + FIELD_DATA + ","
                    + FIELD_TIPO + ","
                    + FIELD_TBL + ") values(?,?,?)";
            
            st = conn.prepareStatement(query);
            st.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            st.setString(2, modifica.getTipo());
            st.setString(3, modifica.getTabella());
            
            st.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
        
    }
    
    public Modifica findLast() {
        
        ResultSet rs = null;
        Modifica modifica = new Modifica();

        try {

            open();
            
            String query = "select * from " + TBL_NAME;
            st = conn.prepareStatement(query);
            
            rs = st.executeQuery();
            
            while(rs.next()){
            
                modifica.setID_modifica(rs.getInt(FIELD_ID));
                modifica.setID_oggetto(rs.getInt(FIELD_OBJ));
                modifica.setTabella(rs.getString(FIELD_TBL));
                modifica.setTipo(rs.getString(FIELD_TIPO));
                modifica.setData(rs.getTimestamp(FIELD_DATA).getTime());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
        
        return modifica;

    }

    public ArrayList<Modifica> findAllAfterDate(Timestamp data) {
        ResultSet rs = null;
        ArrayList<Modifica> modifiche = new ArrayList<>();
        
        try {
            open();
            
            String query = "select * from " + TBL_NAME + " where " + FIELD_DATA + " >= ? ";
            st = conn.prepareStatement(query);
            st.setTimestamp(1, data);
            rs = st.executeQuery();
            while(rs.next()) {
                Modifica modifica = new Modifica();
                modifica.setID_modifica(rs.getInt(FIELD_ID));
                modifica.setID_oggetto(rs.getInt(FIELD_OBJ));
                modifica.setTabella(rs.getString(FIELD_TBL));
                modifica.setTipo(rs.getString(FIELD_TIPO));
                modifica.setData(rs.getTimestamp(FIELD_DATA).getTime());
                modifiche.add(modifica);
            }
        } 
        catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        finally {
            close();
        }
        
        return modifiche;    }
    
}
