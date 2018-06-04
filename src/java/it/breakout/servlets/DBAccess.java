package it.breakout.servlets;


import javax.servlet.RequestDispatcher; 
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.HttpConstraint;;
import java.io.IOException;
import java.sql.*;
import java.util.*;


/**
 *
 * @author Giovanni
 */

@ServletSecurity(@HttpConstraint(rolesAllowed = "admin"))
public class DBAccess extends HttpServlet{
    
    private String query;
    private ArrayList<String> quote;
    private Connection conn;
    private Statement stat;
    private String scelta;

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
        
        quote = new ArrayList<>();
        scelta = request.getParameter("scelta");
        RequestDispatcher rd;        
        
        switch(scelta) {
            case "mappe":
        
                query = "select * from mappa";

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

                request.setAttribute("quote", quote);
                rd = request.getRequestDispatcher("gestione-mappe.jsp");
                rd.forward(request, response);
                break;
                
            case "utenti":
            
                query = "select * from utenti";

                try {
                    conn = DriverManager.getConnection("jdbc:derby://localhost:1527/breakout", "app", "app");
                    stat = conn.createStatement();
                    ResultSet rs = stat.executeQuery(query);
                    while(rs.next()) {
                        quote.add(rs.getString("nome"));
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
                
                request.setAttribute("quote", quote);
                rd = request.getRequestDispatcher("gestione-utenti.jsp");
                rd.forward(request, response);
                
                break;
        }
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
