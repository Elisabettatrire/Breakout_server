package it.breakout.controllers;


import javax.servlet.RequestDispatcher; 
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;
import java.util.*;

import it.breakout.models.Mappa;



/**
 *
 * @author Giovanni
 */
@WebServlet(name = "MappaServlet", urlPatterns = {"/MappaServlet"}) 
public class MappaServlet extends HttpServlet{
    
    private String query;
    private String id_mappa;
    private ArrayList<String> quote;
    private String mappaID = "mappaBean";
    private Connection conn;
    private Statement stat;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> 
     * methods. 
     * 
     * @param request servlet request 
     * @param response servlet response 
     * @throws ServletException if a servlet-specific error occurs 
     * @throws IOException if an I/O error occurs 
     */

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        query = "select * from mappa";
        quote = new ArrayList<String>();

        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/breakout", "app", "app");
            stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next()) {
                quote.add(rs.getString("piano"));
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

        RequestDispatcher rd;
        request.setAttribute("quote", quote);
        rd = request.getRequestDispatcher("/home.jsp");
        rd.forward(request, response);
        
    }

   // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
   /**
    * Handles the HTTP <code>GET</code> method.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   /**
    * Handles the HTTP <code>POST</code> method.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
