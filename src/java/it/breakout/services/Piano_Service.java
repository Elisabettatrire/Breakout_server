/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.breakout.models.Piano;

/**
 *
 * @author costantino
 */
public class Piano_Service {

    private PreparedStatement st = null;
    private Connection conn = null;
    
    public static final String TBL_NAME = "piano";
    public static final String FIELD_ID = "id_piano";
    public static final String FIELD_NOME = "quota";
    
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
    
    public ArrayList<Piano> findAll() {
		ResultSet rs = null;
		ArrayList<Piano> piani = new ArrayList<>();
        try {
            open();
            
            String query = "select * from " + TBL_NAME + " order by quota";
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            while(rs.next()) {
                Piano piano = new Piano();
                piano.setQuota(rs.getString("quota"));
                piano.setID_piano(rs.getLong("id_piano"));
                piani.add(piano);
            }
        } 
        catch (SQLException e) {
        	e.printStackTrace();
        }
        finally {
            close();
        }
        
        return piani;
    }
    
    public Piano findById(String search_id) {
		ResultSet rs = null;
                Piano piano = new Piano();
        try {
            open();
            String query = "select * from " + TBL_NAME + "where id_piano=" + search_id;
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            piano.setQuota(rs.getString("quota"));
            piano.setID_piano(rs.getLong("id_piano"));
        } 
        catch (SQLException e) {
        	e.printStackTrace();
        }
        finally {
            close();
        }
        
        return piano;
    }
}
