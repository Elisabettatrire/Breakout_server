/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.services;

import it.breakout.models.Nodo;
import it.breakout.models.Utente;
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
public class Utente_Service {
    
    private PreparedStatement st = null;
    private Connection conn = null;
    
    public static final String TBL_NAME = "utente";
    public static final String FIELD_ID = "id_utente";
    public static final String FIELD_USR = "username";
    public static final String FIELD_PSW = "password";
    
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
    
    public ArrayList<Utente> findAllUsers() {
        ResultSet rs = null;
        ArrayList<Utente> utenti = new ArrayList<>();
        
        try {
            open();
            
            String query = "select * from " + TBL_NAME + " order by username";
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            while(rs.next()) {
                Utente utente = new Utente();
                utente.setID_utente(rs.getInt("id_utente"));
                utente.setUsername(rs.getString("username"));
                utente.setPassword(rs.getString("password"));
                utente.setEmail(rs.getString("email"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utenti.add(utente);
            }
        } 
        catch (SQLException e) {
        	e.printStackTrace();
        }
        finally {
            close();
        }
        
        return utenti;
    }
}
