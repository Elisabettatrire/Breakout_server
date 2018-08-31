/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static it.breakout.utility.EnvVariables.DB_PSW;
import static it.breakout.utility.EnvVariables.DB_URL;
import static it.breakout.utility.EnvVariables.DB_USR;

/**
 *
 * @author Giovanni
 */
public class NotificaService {
    
    private PreparedStatement st = null;
    private Connection conn = null;
    
    public static final String TBL_NAME = "notifiche";
    public static final String FIELD_ID = "id_notifica";
    public static final String FIELD_EMERG = "is_emergenza";
    
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
    
    public void startEmergency() {
        
        try{
            
            open();
            
            String query = "insert into " + TBL_NAME
                    + " (" + FIELD_EMERG + ") values(?)";
            
            st = conn.prepareStatement(query);
            st.setBoolean(1, true);
            
            st.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }
    
    public void abortEmergency() {
        
        try{
            
            open();
            
            String query = "insert into " + TBL_NAME
                    + " (" + FIELD_EMERG + ") values(?)";
            
            st = conn.prepareStatement(query);
            st.setBoolean(1, false);
            
            st.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }
    
    /* Il metodo sottostante restituisce il booleano che identifica l'inizio o
    la fine di un'emergenza
    */
    public String retrieveLastState() {
        
        ResultSet rs = null;
        String state = "";
        
        try{
            
            open();
            
            String query = "select * from " + TBL_NAME
                    + " order by " + FIELD_ID + " desc fetch first 1 row only";
            
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            
            while(rs.next()) {
                state = rs.getString(FIELD_EMERG);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
        
        return state;
    }
}
