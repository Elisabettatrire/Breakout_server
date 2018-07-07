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

import static it.breakout.utility.EnvVariables.DB_PSW;
import static it.breakout.utility.EnvVariables.DB_URL;
import static it.breakout.utility.EnvVariables.DB_USR;

/**
 *
 * @author costantino
 */
public class PianoService {

    private PreparedStatement st = null;
    private Connection conn = null;
    
    public static final String TBL_NAME = "piano";
    public static final String FIELD_ID = "id_piano";
    public static final String FIELD_QUOTA = "quota";
    
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
    
    /* Funzioni di ricerca */
    public ArrayList<Piano> findAll() {
            ResultSet rs = null;
            ArrayList<Piano> piani = new ArrayList<>();
        try {
            open();
            
            String query = "select * from " + TBL_NAME + " order by " + FIELD_QUOTA;
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            while(rs.next()) {
                Piano piano = new Piano();
                piano.setQuota(rs.getString(FIELD_QUOTA));
                piano.setID_piano(rs.getInt(FIELD_ID));
                piani.add(piano);
            }
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        } finally {
            close();
        }
        
        return piani;
    }
    
    public Piano findById(Integer search_id) {
        
        ResultSet rs = null;
        Piano piano = new Piano();
        try {
            open();
            
            String query = "select * from " + TBL_NAME + " where " + FIELD_ID + "= ?";
            st = conn.prepareStatement(query);
            st.setInt(1, search_id);
            rs = st.executeQuery();
            while(rs.next()) {
                piano.setQuota(rs.getString(FIELD_QUOTA));
                piano.setID_piano(rs.getInt(FIELD_ID));
            }
        } 
        catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        finally {
            close();
        }
        
        return piano;
    }
    
    public Piano findByQuota(String quota) {
        
        ResultSet rs = null;
        Piano piano = new Piano();
        
        try {
            open();
            
            String query = "select * from " + TBL_NAME + " where " + FIELD_QUOTA + " = ?";
            st = conn.prepareStatement(query);
            st.setString(1, quota);
            rs = st.executeQuery();
            while(rs.next()) {
                piano.setQuota(rs.getString(FIELD_QUOTA));
                piano.setID_piano(rs.getInt(FIELD_ID));
            }
        } 
        catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        finally {
            close();
        }
        
        return piano;
    }
    
    
    /* Funzioni di inserimento-modifica-cancellazione */
    public void insert(String quota) {
                
        try {
            open();
            
            String query = "insert into " + TBL_NAME
                    + " (" + FIELD_QUOTA + ") values(?)";
            st = conn.prepareStatement(query);
            st.setString(1, quota);
            st.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            close();
        }
    }
    
    public void update(String quota, Integer id_piano) {
                
        try {
            open();
            
            String query = "update " + TBL_NAME + " set " + FIELD_QUOTA + "=? where "
                    + FIELD_ID + "=?";
            st = conn.prepareStatement(query);
            st.setString(1, quota);
            st.setInt(2, id_piano);
            st.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            close();
        }
    }
    
    public void delete(Integer id_piano) {
                
        try {
            open();
            
            String query = "delete from " + TBL_NAME + " where " + FIELD_ID + "=?";
            st = conn.prepareStatement(query);
            st.setInt(1, id_piano);
            st.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            close();
        }
    }
}
