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
import it.breakout.models.Collegamento;
import it.breakout.services.MappaService;
import it.breakout.services.PianoService;
import it.breakout.services.NodoService;
import it.breakout.services.BeaconService;
import it.breakout.services.TroncoService;

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
                    
                    fillPianoData(request);
                    
                    /* Reindirizzamento pagina di gestione piano */
                    rd = request.getRequestDispatcher("gestione-piano.jsp");
                    rd.forward(request, response);
                    
                    break;
                    
                case "mappa":
                    
                    fillMappaData(request);
                    
                    /* Reindirizzamento pagina di gestione mappa */
                    rd = request.getRequestDispatcher("gestione-mappa.jsp");
                    rd.forward(request, response);
                    
                    break;
                    
                case "grafo":
                    
                    fillGrafoData(request);
                    
                    /* Reindirizzamento pagina di gestione grafo */
                    rd = request.getRequestDispatcher("gestione-grafo.jsp");
                    rd.forward(request, response);
                    
                    break;
                    
                case "beacon":
                    
                    fillBeaconData(request);
                    
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
    public void fillPianoData(HttpServletRequest request) {
        
        MappaService mappaService = new MappaService();
        PianoService pianoService = new PianoService();
        NodoService nodoService = new NodoService();
        BeaconService beaconService = new BeaconService();
        TroncoService troncoService = new TroncoService();
        
        Piano piano_search = pianoService.findByQuota(identificatore);
        Integer id_piano = piano_search.getID_piano();
        
        ArrayList<Collegamento> al_collegamenti = troncoService.findAllLinks();
        ArrayList<Nodo> al_nodi = nodoService.findByIDPiano(id_piano);
        ArrayList<Mappa> al_mappe = mappaService.findByIDPiano(id_piano);
        ArrayList<Beacon> al_beacon = beaconService.findByIDPiano(id_piano);
        
        /* Invio alla view i dati da visualizzare */
        request.setAttribute("quota", identificatore);
        request.setAttribute("mappe", al_mappe);
        request.setAttribute("nodi", al_nodi);
        request.setAttribute("al_beacon", al_beacon);
        request.setAttribute("collegamenti", al_collegamenti);
        
    }
    
    public void fillMappaData(HttpServletRequest request) {
        
        BeaconService beaconService = new BeaconService();
        MappaService mappaService = new MappaService();
        PianoService pianoService = new PianoService();
        NodoService nodoService = new NodoService();

        Mappa mappa_search = mappaService.findByNome(identificatore);
        Integer id_mappa = mappa_search.getID_mappa();
        ArrayList<Beacon> al_beacon = beaconService.findByIDMappa(id_mappa);
        ArrayList<Nodo> al_nodi = nodoService.findByIDMappa(id_mappa);
        ArrayList<Pdi> al_pdi = nodoService.findPdiByIDMappa(id_mappa);
        String url_immagine = mappa_search.getUrlImmagine();
        String quota = pianoService.findById(mappa_search.getID_piano()).getQuota();

        /* Invio alla view i dati da visualizzare */
        request.setAttribute("nome", identificatore);
        request.setAttribute("al_beacon", al_beacon);
        request.setAttribute("nodi", al_nodi);
        request.setAttribute("al_pdi", al_pdi);
        request.setAttribute("quota", quota);
        request.setAttribute("id_mappa", id_mappa);
        request.setAttribute("url_immagine", url_immagine);
        
    }
    
    public void fillGrafoData(HttpServletRequest request) {
        
        MappaService mappaService = new MappaService();
        NodoService nodoService = new NodoService();
        PianoService pianoService = new PianoService();
        TroncoService troncoService = new TroncoService();
        BeaconService beaconService = new BeaconService();

        Mappa mappa_search = mappaService.findByNome(identificatore);
        Integer id_mappa = mappa_search.getID_mappa();
        String quota = pianoService.findById(mappa_search.getID_piano()).getQuota();

        ArrayList<Tronco> al_tronchi = troncoService.findAllArcs(id_mappa);
        ArrayList<Nodo> al_nodi = nodoService.findByIDMappa(id_mappa);
        ArrayList<Pdi> al_pdis = nodoService.findPdiByIDMappa(id_mappa);
        ArrayList<Beacon> al_beacon = beaconService.findByIDMappa(id_mappa);
               
        /* Invio alla view i dati da visualizzare */
        request.setAttribute("nome", identificatore);
        request.setAttribute("tronchi", al_tronchi);
        request.setAttribute("nodi", al_nodi);
        request.setAttribute("pdis", al_pdis);
        request.setAttribute("quota", quota);
        request.setAttribute("al_beacon", al_beacon);
        request.setAttribute("nodoService", nodoService);
        
    }
    
    public void fillBeaconData(HttpServletRequest request) {
        
        MappaService mappaService = new MappaService();
        BeaconService beaconService = new BeaconService();
        PianoService pianoService = new PianoService();
        NodoService nodoService = new NodoService();

        Integer id_mappa = Integer.parseInt(identificatore);
        Mappa mappa_search = mappaService.findByID(id_mappa);
        String quota = pianoService.findById(mappa_search.getID_piano()).getQuota();
        ArrayList<Beacon> al_beacon = beaconService.findByIDMappa(id_mappa);
        ArrayList<Pdi> al_pdi = nodoService.findPdiByIDMappa(id_mappa);

        /* Invio alla view i dati da visualizzare */
        request.setAttribute("id_mappa", id_mappa);
        request.setAttribute("al_beacon", al_beacon);
        request.setAttribute("al_pdi", al_pdi);
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
