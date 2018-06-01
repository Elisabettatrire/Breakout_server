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
    public static final String FIELD_QUOTA = "quota";
    
    private void open() throws SQLException {
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/breakout", "app", "app");
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
    
    public Set<Mappa> findAll() {
		ResultSet rs = null;
		Set<Mappa> mappe = new HashSet<>();
        try {
            open();
            String query = "select * from " + TBL_NAME;
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            while(rs.next()) {
            	Mappa mappa;
                mappa = new Mappa();  
                mappe.add(mappa);
            }
        } 
        catch (SQLException e) {
        	e.printStackTrace();
        }
        finally {
            close();
        }
        System.out.println(mappe);
        return mappe;
    }
    
}
