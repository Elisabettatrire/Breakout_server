/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.breakout.models.Mappa;
import it.breakout.models.Piano;
import it.breakout.models.Nodo;
import it.breakout.models.Pdi;
import it.breakout.models.Beacon;
import it.breakout.models.Tronco;
import it.breakout.resources.Mappa_Resource;
import it.breakout.resources.Piano_Resource;
import it.breakout.resources.Nodo_Resource;
import it.breakout.resources.Beacon_Resource;
import it.breakout.resources.Tronco_Resource;

/**
 *
 * @author Giovanni
 */
@ServletSecurity(@HttpConstraint(rolesAllowed = "admin"))
public class ObjectAccess extends HttpServlet {
    
    private String oggetto;
    private String identificatore;
    
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

        oggetto = request.getParameter("obj");
        identificatore = request.getParameter("nm");
        RequestDispatcher rd;
        
        try {
            switch(oggetto) {
                case "piano":
                    
                    FillPianoData(request);
                    
                    /* Reindirizzamento pagina di gestione piano */
                    rd = request.getRequestDispatcher("gestione-piano.jsp");
                    rd.forward(request, response);
                    
                    break;
                    
                case "mappa":
                    
                    FillMappaData(request);
                    
                    /* Reindirizzamento pagina di gestione mappa */
                    rd = request.getRequestDispatcher("gestione-mappa.jsp");
                    rd.forward(request, response);
                    
                    break;
                    
                case "grafo":
                    
                    FillGrafoData(request);
                    
                    /* Reindirizzamento pagina di gestione grafo */
                    rd = request.getRequestDispatcher("gestione-grafo.jsp");
                    rd.forward(request, response);
                    
                    break;
                    
                case "beacon":
                    
                    FillBeaconData(request);
                    
                    /* Reindirizzamento pagina di gestione beacon della mappa */
                    rd = request.getRequestDispatcher("gestione-beacon.jsp");
                    rd.forward(request, response);
                    
                    break;
            }
        } catch (IOException | ServletException e) {
            System.out.println(e.getMessage());
            response.sendRedirect("500.jsp");
        }

    }
    
    
    /**
     * Le funzioni sottostanti reperiscono i dati dal DB e li inviano alle varie
     * view
     *
     * @param request servlet request
     *
     */
    public void FillPianoData(HttpServletRequest request) {
        
        Mappa_Resource mappa_resource = new Mappa_Resource();
        Piano_Resource piano_resource = new Piano_Resource();
        
        Piano piano_search = piano_resource.findByQuota(identificatore);
        Integer id_piano = piano_search.getID_piano();
        
        ArrayList<Mappa> al_mappe = mappa_resource.findByIDPiano(id_piano);
        
        /* Invio alla view i dati da visualizzare */
        request.setAttribute("quota", identificatore);
        request.setAttribute("mappe", al_mappe);
        
    }
    
    public void FillMappaData(HttpServletRequest request) {
        
        //Beacon_Resource beacon_resource = new Beacon_Resource();
        Mappa_Resource mappa_resource_2 = new Mappa_Resource();
        Piano_Resource piano_resource2 = new Piano_Resource();

        //ArrayList<Beacon> al_beacon = beacon_resource.findAll();
        Mappa mappa_search1 = mappa_resource_2.findByNome(identificatore);
        Integer id_mappa = mappa_search1.getID_mappa();
        String url_immagine = mappa_search1.getUrlImmagine();
        String quota = piano_resource2.findById(mappa_search1.getID_piano()).getQuota();

        /* Invio alla view i dati da visualizzare */
        request.setAttribute("nome", identificatore);
        //request.setAttribute("beacons", al_beacon);
        request.setAttribute("quota", quota);
        request.setAttribute("id_mappa", id_mappa);
        request.setAttribute("url_immagine", url_immagine);
        
    }
    
    public void FillGrafoData(HttpServletRequest request) {
        
        Mappa_Resource mappa_resource = new Mappa_Resource();
        Nodo_Resource nodo_resource = new Nodo_Resource();
        Piano_Resource piano_resource = new Piano_Resource();
        Tronco_Resource tronco_resource = new Tronco_Resource();
        Beacon_Resource beacon_resource = new Beacon_Resource();

        Mappa mappa_search = mappa_resource.findByNome(identificatore);
        Integer id_mappa = mappa_search.getID_mappa();
        String quota = piano_resource.findById(mappa_search.getID_piano()).getQuota();

        ArrayList<Tronco> al_tronchi = tronco_resource.findAllArcs(id_mappa);
        ArrayList<Nodo> al_nodi = nodo_resource.findByIDMappa(id_mappa);
        ArrayList<Pdi> al_pdis = nodo_resource.findPoisByIDMappa(id_mappa);
        ArrayList<Beacon> al_beacon = beacon_resource.findByIDMappa(id_mappa);
               
        /* Invio alla view i dati da visualizzare */
        request.setAttribute("nome", identificatore);
        request.setAttribute("tronchi", al_tronchi);
        request.setAttribute("nodi", al_nodi);
        request.setAttribute("pdis", al_pdis);
        request.setAttribute("quota", quota);
        request.setAttribute("al_beacon", al_beacon);
        request.setAttribute("nodo_resource", nodo_resource);
        
    }
    
    public void FillBeaconData(HttpServletRequest request) {
        
        Mappa_Resource mappa_resource = new Mappa_Resource();
        Beacon_Resource beacon_resource = new Beacon_Resource();
        Piano_Resource piano_resource = new Piano_Resource();

        Integer id_mappa = Integer.parseInt(identificatore);
        Mappa mappa_search = mappa_resource.findByID(id_mappa);
        String quota = piano_resource.findById(mappa_search.getID_piano()).getQuota();
        ArrayList<Beacon> al_beacon = beacon_resource.findByIDMappa(id_mappa);

        /* Invio alla view i dati da visualizzare */
        request.setAttribute("id_mappa", id_mappa);
        request.setAttribute("al_beacon", al_beacon);
        request.setAttribute("quota", quota);
        request.setAttribute("nome_mappa", mappa_search.getNome());
        
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
