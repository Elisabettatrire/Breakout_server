package it.breakout.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import it.breakout.models.Mappa;

/**
 *
 * @author costantino
 */
public class Mappa_Service {
    
    private PreparedStatement st = null;
    private Connection conn = null;
    
    public static final String TBL_NAME = "mappa";
    public static final String FIELD_ID = "id_mappa";
    public static final String FIELD_ID_PIANO = "id_piano";
    
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
                mappa.setNome(rs.getString("nome"));
                mappe.add(mappa);
            }
        } 
        catch (SQLException e) {
        	e.printStackTrace();
        }
        finally {
            close();
        }
        
        return mappe;
    }
    
}
