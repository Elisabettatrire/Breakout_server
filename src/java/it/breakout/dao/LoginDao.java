/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

/**
 *
 * @author Giovanni
 */
public class LoginDao {
    
    private Connection conn;
    private PreparedStatement stat;
    private String query = "select * from utenti where nome=? and password=?";
    
    public boolean checkCredentials(String uname, String pass){
        
        try {
                conn = DriverManager.getConnection("jdbc:derby://localhost:1527/breakout", "app", "app");
                stat = conn.prepareStatement(query);
                stat.setString(1, uname);
                stat.setString(2, pass);
                ResultSet rs = stat.executeQuery();
                if(rs.next()) {
                    return true;
                }

            } catch (Exception f) {
                    f.printStackTrace();
            } finally {
                try {
                if (conn != null) {
                    conn.close();
                }
                if (stat != null) {
                    stat.close();
                }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        
        return false;
        
    }
    
}
