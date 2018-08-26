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
    
}
