package it.breakout.servlets;


import javax.servlet.RequestDispatcher; 
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.HttpConstraint;;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import it.breakout.resources.Tronco_Resource;
import it.breakout.resources.Piano_Resource;
import it.breakout.resources.Nodo_Resource;
import it.breakout.models.Scala;
import it.breakout.models.Piano;
import it.breakout.models.Nodo;
import it.breakout.models.Tronco;

/**
 *
 * @author Giovanni
 */

@ServletSecurity(@HttpConstraint(rolesAllowed = "admin"))
public class DBAccess extends HttpServlet{
    
    private String query;
    private Connection conn;
    private Statement stat;
    private String modalita;

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
        
        modalita = request.getParameter("modalita");
        RequestDispatcher rd;
        
        try {
            switch(modalita) {
                /*  Click su "Gestione mappe nella home" */
                case "mappe":
                    
                    /* Riempimento tabella piani */
                    Piano_Resource piano_resource = new Piano_Resource();
                    ArrayList<Piano>  al_piani = piano_resource.findAll();
                    request.setAttribute("piani", al_piani);
                    
                    /* Riempimento tabella scale */
                    Tronco_Resource tronco_resource1 = new Tronco_Resource();
                    ArrayList<Scala> al_scale = tronco_resource1.findAllStairs();
                    request.setAttribute("scale", al_scale);
                    
                    /* Invio alla pagina web */
                    rd = request.getRequestDispatcher("gestione-mappe.jsp");
                    rd.forward(request, response);
                    
                    break;
                    
                /*  Click su "Gestione utenti app" nella home*/
                case "utenti":

                    query = "select * from utenti";

                    break;
                    
                /*  Click su "Gestione Grafo" */
                case "grafo":
                    
                    /* Riempimento tabella nodi */
                    Nodo_Resource nodo_resource = new Nodo_Resource();
                    ArrayList<Nodo> al_nodi = nodo_resource.findAllNodes();
                    request.setAttribute("nodi", al_nodi);
                    
                    Tronco_Resource tronco_resource2 = new Tronco_Resource();
                    ArrayList<Tronco> al_tronchi = tronco_resource2.findAllArcs();
                    request.setAttribute("tronchi", al_tronchi);
                    
                    rd = request.getRequestDispatcher("gestione-grafo.jsp");
                    rd.forward(request, response);
                    
            }
        } catch (IOException | ServletException f) {
            f.printStackTrace();
            response.sendRedirect("500.jsp");
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
