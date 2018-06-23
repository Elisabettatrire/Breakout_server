package it.breakout.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.breakout.models.Mappa;

import static it.breakout.utility.Constants.DB_PSW;
import static it.breakout.utility.Constants.DB_URL;
import static it.breakout.utility.Constants.DB_USR;

/**
 *
 * @author costantino
 */
public class Mappa_Service {
    
    private PreparedStatement st = null;
    private Connection conn = null;
    
    public static final String TBL_NAME = "mappa";
    public static final String FIELD_ID = "id_mappa";
    public static final String FIELD_NOME = "nome";
    public static final String FIELD_ID_PIANO = "id_piano";
    public static final String FIELD_IMG = "img";
    
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
                e.getMessage();
        }
    }
    
    public ArrayList<Mappa> findAll() {
        
        ResultSet rs = null;
        ArrayList<Mappa> mappe = new ArrayList<>();
        
        try {
            open();
            
            String query = "select * from " + TBL_NAME + " order by nome";
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            while(rs.next()) {
            	Mappa mappa;
                mappa = new Mappa();
                mappa.setNome(rs.getString(FIELD_NOME));
                mappa.setUrlImmagine(rs.getString(FIELD_IMG));
                mappa.setID_mappa(rs.getInt(FIELD_ID));
                mappa.setID_piano(rs.getInt(FIELD_ID_PIANO));
                mappe.add(mappa);
            }
        } 
        catch (SQLException e) {
        	e.getMessage();
        }
        finally {
            close();
        }
        
        return mappe;
    }
    
    public ArrayList<Mappa> findByIDPiano(Integer search_id) {
        
        ResultSet rs = null;
        ArrayList<Mappa> mappe = new ArrayList<>();
        
        try {
            open();
            
            String query = "select * from " + TBL_NAME + " where " + FIELD_ID_PIANO + "=? order by nome";
            st = conn.prepareStatement(query);
            st.setInt(1, search_id);
            rs = st.executeQuery();
            while(rs.next()) {
            	Mappa mappa;
                mappa = new Mappa();
                mappa.setNome(rs.getString(FIELD_NOME));
                mappa.setUrlImmagine(rs.getString(FIELD_IMG));
                mappa.setID_mappa(rs.getInt(FIELD_ID));
                mappa.setID_piano(rs.getInt(FIELD_ID_PIANO));
                mappe.add(mappa);
            }
        } 
        catch (SQLException e) {
        	e.getMessage();
        }
        finally {
            close();
        }
        
        return mappe;
    }
    
    public Mappa findByID(Integer search_id) {
        
        ResultSet rs = null;
        Mappa mappa = new Mappa();
        
        try {
            open();
            
            String query = "select * from " + TBL_NAME + " where " + FIELD_ID + "=?";
            st = conn.prepareStatement(query);
            st.setInt(1, search_id);
            rs = st.executeQuery();
            while(rs.next()) {
            	mappa.setNome(rs.getString(FIELD_NOME));
                mappa.setUrlImmagine(rs.getString(FIELD_IMG));
                mappa.setID_mappa(rs.getInt(FIELD_ID));
                mappa.setID_piano(rs.getInt(FIELD_ID_PIANO));
            }
        } 
        catch (SQLException e) {
        	e.getMessage();
        }
        finally {
            close();
        }
        
        return mappa;
    }
    
    public Mappa findByNome(String search_nome) {
        
        ResultSet rs = null;
        Mappa mappa = new Mappa();
        
        try {
            open();
            
            String query = "select * from " + TBL_NAME + " where " + FIELD_NOME + "=?";
            st = conn.prepareStatement(query);
            st.setString(1, search_nome);
            rs = st.executeQuery();
            while(rs.next()) {
            	mappa.setNome(rs.getString(FIELD_NOME));
                mappa.setUrlImmagine(rs.getString(FIELD_IMG));
                mappa.setID_mappa(rs.getInt(FIELD_ID));
                mappa.setID_piano(rs.getInt(FIELD_ID_PIANO));
            }
        } 
        catch (SQLException e) {
        	e.getMessage();
        }
        finally {
            close();
        }
        
        return mappa;
    }
    
    public void insert(Mappa mappa) {
        
        try {
            open();
            
            String query = "insert into " + TBL_NAME + " (img,nome,id_piano) values(?,?,?)";
            st = conn.prepareStatement(query);
            st.setString(1, mappa.getUrlImmagine());
            st.setString(2, mappa.getNome());
            st.setInt(3, mappa.getID_piano());
            st.executeUpdate();
            
        } catch (SQLException e) {
            e.getMessage();
        }
        finally {
            close();
        }
    }
    
    public void update(Mappa mappa, Integer id_mappa) {
        
        try {
            open();
            
            String query = "update " + TBL_NAME + " set "
                    + FIELD_NOME + "=?, "
                    + FIELD_IMG + "=? "
                    + "where " + FIELD_ID + "=?";
            st = conn.prepareStatement(query);
            st.setString(1, mappa.getNome());
            st.setString(2, mappa.getUrlImmagine());
            st.setInt(3, mappa.getID_mappa());
            st.executeUpdate();
            
        } catch (SQLException e) {
            e.getMessage();
        }
        finally {
            close();
        }
    }
    
    public void delete(Integer id_mappa) {
        
        try {
            open();
            
            String query = "delete from " + TBL_NAME + " where " + FIELD_ID + "=?";
            st = conn.prepareStatement(query);
            st.setInt(1, id_mappa);
            st.executeUpdate();
            
        } catch (SQLException e) {
            e.getMessage();
        }
        finally {
            close();
        }
    }
    
}
