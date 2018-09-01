package it.breakout.servlets;

import javax.servlet.RequestDispatcher; 
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.HttpConstraint;
import java.io.IOException;
import java.util.ArrayList;

import it.breakout.services.BeaconService;
import it.breakout.services.NodoService;
import it.breakout.services.TroncoService;
import it.breakout.services.PianoService;
import it.breakout.services.UtenteService;
import it.breakout.models.Scala;
import it.breakout.models.Nodo;
import it.breakout.models.Piano;
import it.breakout.models.Utente;
import it.breakout.models.Beacon;

/**
 *
 * @author Giovanni
 */

@ServletSecurity(@HttpConstraint(rolesAllowed = "admin"))
public class DBAccess extends HttpServlet{
    
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
                    
                    fillMappeData(request);
                    
                    /* Invio alla pagina web */
                    rd = request.getRequestDispatcher("gestione-mappe.jsp");
                    rd.forward(request, response);
                    
                    break;
                    
                /*  Click su "Gestione utenti app" nella home*/
                case "utenti":
                    
                    fillUtentiData(request);
                    
                    rd = request.getRequestDispatcher("gestione-utenti.jsp");
                    rd.forward(request, response);

                    break;

            }
        } catch (IOException | ServletException e) {
            System.out.println(e.getMessage());
            response.sendRedirect("500.jsp");
        }

    }
    
    public void fillMappeData(HttpServletRequest request) {
        
        /* Riempimento tabella piani */
        PianoService pianoService = new PianoService();
        ArrayList<Piano> al_piani = pianoService.findAll();
        request.setAttribute("piani", al_piani);

        /* Riempimento tabella scale */
        TroncoService troncoService = new TroncoService();
        ArrayList<Scala> al_scale = troncoService.findAllStairs();
        request.setAttribute("scale", al_scale);

        /* Rimepimento select delle form */
        BeaconService beaconService = new BeaconService();
        NodoService nodoService = new NodoService();
        ArrayList<Beacon> al_beacon = beaconService.findAll();
        ArrayList<Nodo> al_nodi = nodoService.findAllNormalNodes();
        request.setAttribute("al_beacon", al_beacon);
        request.setAttribute("nodi", al_nodi);
        
    }
    
    public void fillUtentiData(HttpServletRequest request) {
        
        UtenteService utenteService = new UtenteService();
        ArrayList<Utente> al_utenti = utenteService.findAll();
        request.setAttribute("utenti", al_utenti);
        
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
