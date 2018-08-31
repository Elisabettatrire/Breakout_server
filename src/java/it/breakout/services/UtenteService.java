/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.services;

import it.breakout.models.Utente;

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
public class UtenteService {
    
    private PreparedStatement st = null;
    private Connection conn = null;
    
    public static final String TBL_NAME = "utente";
    public static final String FIELD_ID = "id_utente";
    public static final String FIELD_USR = "username";
    public static final String FIELD_PSW = "password";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_NOME = "nome";
    public static final String FIELD_COGNOME = "cognome";
    
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
    
    public ArrayList<Utente> findAll() {
        ResultSet rs = null;
        ArrayList<Utente> utenti = new ArrayList<>();
        
        try {
            open();
            
            String query = "select * from " + TBL_NAME + " order by " + FIELD_USR;
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            while(rs.next()) {
                Utente utente = new Utente();
                utente.setID_utente(rs.getInt(FIELD_ID));
                utente.setUsername(rs.getString(FIELD_USR));
                utente.setPassword(rs.getString(FIELD_PSW));
                utente.setEmail(rs.getString(FIELD_EMAIL));
                utente.setNome(rs.getString(FIELD_NOME));
                utente.setCognome(rs.getString(FIELD_COGNOME));
                utenti.add(utente);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
        
        return utenti;
    }
    
    public void update(String psw, Integer id_utente) {
        try {
            open();
            
            String query = "update " + TBL_NAME + " set " + FIELD_PSW + "=? where " + FIELD_ID + "=?";
            st = conn.prepareStatement(query);
            st.setString(1, psw);
            st.setInt(2, id_utente);
            st.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }
    
    public void delete(Integer id_utente) {
        
        try {
            open();
            
            String query = "delete from " + TBL_NAME + " where " + FIELD_ID + "=?";
            st = conn.prepareStatement(query);
            st.setInt(1, id_utente);
            st.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }
}
