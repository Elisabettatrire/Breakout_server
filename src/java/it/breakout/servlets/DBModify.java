/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.servlets;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import it.breakout.resources.Piano_Resource;
import it.breakout.resources.Mappa_Resource;
import it.breakout.resources.Utente_Resource;
import it.breakout.resources.Nodo_Resource;
import it.breakout.resources.Beacon_Resource;
import it.breakout.utility.FormFilter;
import it.breakout.models.Mappa;
import it.breakout.models.Nodo;
import it.breakout.models.Pdi;
import it.breakout.models.Beacon;
import static it.breakout.utility.Constants.*;
import java.util.Objects;

/**
 *
 * @author Giovanni
 */
public class DBModify extends HttpServlet {

    private String azione;
    
    private FormFilter form_filter = new FormFilter();
    private Integer exists;  // Serve per vedere se le query restituiscono un valore nullo
    private Integer id_mappa;
    private Integer id_piano;

    /* Variabili per inserimento-modifica-eliminazione piano */
    private Piano_Resource piano_resource = new Piano_Resource();
    private String quota;
    private String quota_filtered;

    /* Variabili per inserimento-modifica-eliminazione scala */
 
    /* Variabili per inserimento-modifica-eliminazione mappa */
    private Mappa_Resource mappa_resource = new Mappa_Resource();
    private Mappa mappa = new Mappa();
    private Mappa mappa_old;
    private String nome_mappa;
    private String nome_mappa_filtered;
    private String url_immagine;
 
    /* Variabili per inserimento-modifica-eliminazione utente */
    private Utente_Resource utente_resource = new Utente_Resource();
    private Integer id_utente;
    private String psw;
    private String psw_confirm;
 
    /* Variabili per inserimento-modifica-eliminazione nodo-pdi */
    private Nodo_Resource nodo_resource = new Nodo_Resource();
    private Nodo nodo = new Nodo();
    private Nodo nodo_old;
    private Pdi pdi = new Pdi();
    private Pdi pdi_old;
    private Integer id_nodo;
    private Integer id_pdi;
    private String codice_nodo;
    private String codice_nodo_filtered;
    private String codice_pdi;
    private String codice_pdi_filtered;
    private String coord_x;
    private Double coord_x_filtered;
    private String coord_y;
    private Double coord_y_filtered;
    private String larghezza;
    private Double larghezza_filtered;
    private String lunghezza;
    private Double lunghezza_filtered;
    private String tipo;
 
    /* Variabili per inserimento-modifica-eliminazione beacon */
    private Beacon_Resource beacon_resource = new Beacon_Resource();
    private Beacon beacon = new Beacon();
    private Beacon beacon_old = new Beacon();
    private Integer id_beacon;
    
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
        
        /* Variabili comuni */
        azione = request.getParameter("azione");
        RequestDispatcher rd;
        
        try {
            switch(azione) {

                /* Azioni relative alla tabella dei piani */
                case "aggiungi-piano":

                    quota = request.getParameter("quota");
                    quota_filtered = form_filter.filtraQuota(quota);
                    /* Controllo se esiste un piano con lo stesso nome */
                    exists = piano_resource.findByQuota(quota_filtered).getID_piano();
                    
                    if(!quota_filtered.equals(DEFAULT_STRING) && exists==null) {
                        piano_resource.insert(quota_filtered);
                    }
                    
                    /* Pulisco la variabile di controllo per poterla riutilizzare */
                    exists = null;
                    
                    /* Ritorno alla lista dei piani che sarà aggiornata */
                    rd = request.getRequestDispatcher("/DBAccess");
                    rd.forward(request, response);

                    break;

                case "modifica-piano":

                    id_piano = Integer.parseInt(request.getParameter("id_piano"));
                    quota = request.getParameter("quota");
                    quota_filtered = form_filter.filtraQuota(quota);
                    /* Controllo che non esista già la quota inserita */
                    exists = piano_resource.findByQuota(quota_filtered).getID_piano();
                        
                    /* Il controllo se il campo viene lasciato vuoto viene fatto
                    dopo che questo è stato filtrato, almeno se sono stati
                    inseriti dei caratteri non validi il valore rimane uguale */
                    if(!quota_filtered.equals(DEFAULT_STRING) && exists==null) {
                        piano_resource.update(quota_filtered, id_piano);
                    }
                    
                    exists = null;
                    
                    rd = request.getRequestDispatcher("/DBAccess");
                    rd.forward(request, response);

                case "elimina-piano":

                    piano_resource.delete(Integer.parseInt(request.getParameter("id_piano")));

                    rd = request.getRequestDispatcher("/DBAccess");
                    rd.forward(request, response);

                    break;

                /* Azioni relative alla tabella delle scale */

                /* Azioni relative alla tabella delle mappe */
                case "aggiungi-mappa":

                    nome_mappa = request.getParameter("nome-mappa");
                    quota = request.getParameter("nm"); // non c'è bisogno di filtrarla
                    url_immagine = request.getParameter("url-immagine"); // non c'è bisogno di filtrarlo

                    nome_mappa_filtered = form_filter.filtraNomeMappa(nome_mappa);
                    /* Controllo se esiste una mappa con lo stesso nome */
                    exists = mappa_resource.findByNome(nome_mappa_filtered).getID_mappa();
                    
                    if(!nome_mappa_filtered.equals(DEFAULT_STRING) && exists==null) {
                        
                        mappa.setNome(nome_mappa_filtered);
                        mappa.setID_piano(piano_resource.findByQuota(quota).getID_piano()); // veloce
                        mappa.setUrlImmagine(url_immagine);
                        mappa_resource.insert(mappa);
                    }
                    
                    exists = null;
                    
                    rd = request.getRequestDispatcher("ObjectAccess?obj=piano&nm="+quota);
                    rd.forward(request, response);

                    break;
                    
                case "modifica-mappa":
                                        
                    nome_mappa = request.getParameter("nome-mappa");
                    quota = request.getParameter("nm"); // non c'è bisogno di filtrarla
                    url_immagine = request.getParameter("url-immagine"); // non c'è bisogno di filtrarlo
                    id_mappa = Integer.parseInt(request.getParameter("id_mappa"));
                    
                    /* Visto che i campi da controllare sono più di uno ho bisogno
                    di un oggetto che contenga i vecchi valori
                    */
                    mappa_old = mappa_resource.findByID(id_mappa);
                    
                    nome_mappa_filtered = form_filter.filtraNomeMappa(nome_mappa);
                    exists = mappa_resource.findByNome(nome_mappa_filtered).getID_mappa();
                                        
                    /* Aggiornamento nome mappa */
                    if(!nome_mappa_filtered.equals(DEFAULT_STRING) && exists==null) {
                        mappa.setNome(nome_mappa_filtered);
                    } else { // Se il campo è vuoto bisogna mantenere il valore precedente
                        mappa.setNome(mappa_old.getNome());
                    }
                    
                    exists = null;
                    
                    /* Aggiornamento url */
                    // Se il campo è vuoto bisogna mantenere il valore precedente
                    if(!url_immagine.isEmpty()) {
                        mappa.setUrlImmagine(url_immagine);
                    } else {
                        mappa.setUrlImmagine(mappa_old.getUrlImmagine());
                    }
                    
                    /* Invio query */
                    mappa_resource.update(mappa, mappa.getID_mappa());
                    
                    rd = request.getRequestDispatcher("ObjectAccess?obj=piano&nm="+quota);
                    rd.forward(request, response);

                    break;

                case "elimina-mappa":

                    mappa_resource.delete(Integer.parseInt(request.getParameter("id_mappa")));
                    quota = request.getParameter("nm");

                    rd = request.getRequestDispatcher("ObjectAccess?obj=piano&nm="+quota);
                    rd.forward(request, response);

                    break;
                    
                /* Azioni relative alla tabella degli utenti (Pericolo SQLInjection?) */
                case "modifica-utente":
                    
                    id_utente = Integer.parseInt(request.getParameter("id_utente"));
                    psw = request.getParameter("psw");
                    psw_confirm = request.getParameter("psw-confirm");
                    
                    if(psw.equals(psw_confirm)) {
                        utente_resource.update(psw, id_utente);
                    }
                    
                    rd = request.getRequestDispatcher("/DBAccess");
                    rd.forward(request, response);

                    break;
                    
                case "elimina-utente":
                    
                    utente_resource.delete(Integer.parseInt(request.getParameter("id_utente")));

                    rd = request.getRequestDispatcher("/DBAccess");
                    rd.forward(request, response);

                    break;
                    
                /* Azioni relative alla tabella dei nodi */
                case "aggiungi-nodo":
                    
                    nome_mappa = request.getParameter("nm");
                    codice_nodo = request.getParameter("codice");
                    coord_x = request.getParameter("coord-x");
                    coord_y = request.getParameter("coord-y");
                    larghezza = request.getParameter("larghezza");
                    
                    codice_nodo_filtered = form_filter.filtraCodice(codice_nodo);
                    coord_x_filtered = form_filter.filtraCoordinata(coord_x);
                    coord_y_filtered = form_filter.filtraCoordinata(coord_y);
                    larghezza_filtered = form_filter.filtraMisura(larghezza);
                    
                    exists = nodo_resource.findByCodice(codice_nodo_filtered).getID();
                    
                    if(!codice_nodo_filtered.equals(DEFAULT_STRING)
                            && !Objects.equals(coord_x_filtered, DEFAULT_DOUBLE)
                            && !Objects.equals(coord_y_filtered, DEFAULT_DOUBLE)
                            && !Objects.equals(larghezza_filtered, DEFAULT_DOUBLE)
                            && exists == null){
                        
                        id_mappa = mappa_resource.findByNome(nome_mappa).getID_mappa();
                        
                        nodo.setCodice(codice_nodo_filtered);
                        nodo.setCoord_X(coord_x_filtered);
                        nodo.setCoord_Y(coord_y_filtered);
                        nodo.setLarghezza(larghezza_filtered);
                        nodo.setID_mappa(id_mappa);
                        
                        nodo_resource.insertNodo(nodo);
                        
                    }
                    
                    rd = request.getRequestDispatcher("/ObjectAccess?obj=grafo&nm="+nome_mappa);
                    rd.forward(request, response);
                    
                    break;

                case "modifica-nodo":

                    nome_mappa = request.getParameter("nm");
                    codice_nodo = request.getParameter("codice");
                    coord_x = request.getParameter("coord-x");
                    coord_y = request.getParameter("coord-y");
                    larghezza = request.getParameter("larghezza");
                    id_nodo = Integer.parseInt(request.getParameter("id_nodo"));
                    
                    /* Visto che i campi da controllare sono più di uno ho bisogno
                    di un oggetto che contenga i vecchi valori
                    */
                    nodo_old = nodo_resource.findByID(id_nodo);
                    
                    codice_nodo_filtered = form_filter.filtraCodice(codice_nodo);
                    coord_x_filtered = form_filter.filtraCoordinata(coord_x);
                    coord_y_filtered = form_filter.filtraCoordinata(coord_y);
                    larghezza_filtered = form_filter.filtraMisura(larghezza);
                    
                    /* Controllo campi */
                    exists = nodo_resource.findByCodice(codice_nodo_filtered).getID();
                    if(!codice_nodo_filtered.equals(DEFAULT_STRING) && exists == null) {
                        nodo.setCodice(codice_nodo_filtered);
                    } else{
                        nodo.setCodice(nodo_old.getCodice());
                    }
                    
                    if(!Objects.equals(coord_x_filtered, DEFAULT_DOUBLE)) {
                        nodo.setCoord_X(coord_x_filtered);
                    } else {
                        nodo.setCoord_X(nodo_old.getCoord_X());
                    }
                    
                    if(!Objects.equals(coord_y_filtered, DEFAULT_DOUBLE)) {
                        nodo.setCoord_Y(coord_y_filtered);
                    } else {
                        nodo.setCoord_Y(nodo_old.getCoord_Y());
                    }
                    
                    if(!Objects.equals(larghezza_filtered, DEFAULT_DOUBLE)) {
                        nodo.setLarghezza(larghezza_filtered);
                    } else {
                        nodo.setLarghezza(nodo_old.getLarghezza());
                    }
                    
                    nodo_resource.updateNodo(nodo, id_nodo);
                    
                    rd = request.getRequestDispatcher("/ObjectAccess?obj=grafo&nm="+nome_mappa);
                    rd.forward(request, response);
                    
                    break;
                    
                case "elimina-nodo":
                    
                    nodo_resource.deleteNodo(Integer.parseInt(request.getParameter("id_nodo")));
                    nome_mappa = request.getParameter("nm");
                    
                    rd = request.getRequestDispatcher("/ObjectAccess?obj=grafo&nm="+nome_mappa);
                    rd.forward(request, response);
                    
                    break;
                
                /* Azioni relative alla tabella dei pdi */
                case "aggiungi-pdi":
                    
                    nome_mappa = request.getParameter("nm");
                    codice_pdi = request.getParameter("codice");
                    coord_x = request.getParameter("coord-x");
                    coord_y = request.getParameter("coord-y");
                    larghezza = request.getParameter("larghezza");
                    lunghezza = request.getParameter("lunghezza");
                    tipo = request.getParameter("tipo");
                    
                    codice_pdi_filtered = form_filter.filtraCodice(codice_pdi);
                    coord_x_filtered = form_filter.filtraCoordinata(coord_x);
                    coord_y_filtered = form_filter.filtraCoordinata(coord_y);
                    larghezza_filtered = form_filter.filtraMisura(larghezza);
                    lunghezza_filtered = form_filter.filtraMisura(lunghezza);
                    
                    exists = nodo_resource.findByCodice(codice_pdi_filtered).getID();
                    
                    if(!codice_pdi_filtered.equals(DEFAULT_STRING)
                            && !Objects.equals(coord_x_filtered, DEFAULT_DOUBLE)
                            && !Objects.equals(coord_y_filtered, DEFAULT_DOUBLE)
                            && !Objects.equals(larghezza_filtered, DEFAULT_DOUBLE)
                            && !Objects.equals(lunghezza_filtered, DEFAULT_DOUBLE)
                            && exists == null){
                        
                        id_mappa = mappa_resource.findByNome(nome_mappa).getID_mappa();
                        
                        pdi.setCodice(codice_pdi_filtered);
                        pdi.setCoord_X(coord_x_filtered);
                        pdi.setCoord_Y(coord_y_filtered);
                        pdi.setLarghezza(larghezza_filtered);
                        pdi.setLunghezza(lunghezza_filtered);
                        pdi.setID_mappa(id_mappa);
                        pdi.setTipo(tipo);
                        
                        nodo_resource.insertPdi(pdi);
                        
                    }
                    
                    rd = request.getRequestDispatcher("/ObjectAccess?obj=grafo&nm="+nome_mappa);
                    rd.forward(request, response);
                    
                    break;

                case "modifica-pdi":

                    nome_mappa = request.getParameter("nm");
                    codice_pdi = request.getParameter("codice");
                    coord_x = request.getParameter("coord-x");
                    coord_y = request.getParameter("coord-y");
                    larghezza = request.getParameter("larghezza");
                    lunghezza = request.getParameter("lunghezza");
                    tipo = request.getParameter("tipo");
                    id_pdi = Integer.parseInt(request.getParameter("id_pdi"));
                    
                    /* Visto che i campi da controllare sono più di uno ho bisogno
                    di un oggetto che contenga i vecchi valori
                    */
                    pdi_old = nodo_resource.findPoiByID(id_pdi);
                    
                    codice_pdi_filtered = form_filter.filtraCodice(codice_pdi);
                    coord_x_filtered = form_filter.filtraCoordinata(coord_x);
                    coord_y_filtered = form_filter.filtraCoordinata(coord_y);
                    larghezza_filtered = form_filter.filtraMisura(larghezza);
                    lunghezza_filtered = form_filter.filtraMisura(lunghezza);
                    
                    /* Controllo campi */
                    exists = nodo_resource.findByCodice(codice_pdi_filtered).getID();
                    if(!codice_pdi_filtered.equals(DEFAULT_STRING) && exists == null) {
                        pdi.setCodice(codice_pdi_filtered);
                    } else{
                        pdi.setCodice(pdi_old.getCodice());
                    }
                    
                    if(!Objects.equals(coord_x_filtered, DEFAULT_DOUBLE)) {
                        pdi.setCoord_X(coord_x_filtered);
                    } else {
                        pdi.setCoord_X(pdi_old.getCoord_X());
                    }
                    
                    if(!Objects.equals(coord_y_filtered, DEFAULT_DOUBLE)) {
                        pdi.setCoord_Y(coord_y_filtered);
                    } else {
                        pdi.setCoord_Y(pdi_old.getCoord_Y());
                    }
                    
                    if(!Objects.equals(larghezza_filtered, DEFAULT_DOUBLE)) {
                        pdi.setLarghezza(larghezza_filtered);
                    } else {
                        pdi.setLarghezza(pdi_old.getLarghezza());
                    }
                    
                    if(!Objects.equals(lunghezza_filtered, DEFAULT_DOUBLE)) {
                        pdi.setLunghezza(lunghezza_filtered);
                    } else {
                        pdi.setLunghezza(pdi_old.getLunghezza());
                    }
                    
                    if(tipo.equals(TIPO_INV)) {
                        pdi.setTipo(tipo);
                    } else {
                        pdi.setTipo(pdi_old.getTipo());
                    }
                    
                    nodo_resource.updatePoi(pdi, id_pdi);
                    
                    rd = request.getRequestDispatcher("/ObjectAccess?obj=grafo&nm="+nome_mappa);
                    rd.forward(request, response);
                    
                    break;
                    
                case "elimina-pdi":
                    
                    nodo_resource.deleteNodo(Integer.parseInt(request.getParameter("id_pdi")));
                    nome_mappa = request.getParameter("nm");
                    
                    rd = request.getRequestDispatcher("/ObjectAccess?obj=grafo&nm="+nome_mappa);
                    rd.forward(request, response);
                    
                    break;
                    
                /* Azioni relative alla tabella dei beacon */
                case "aggiungi-beacon":
                    
                    break;
                    
                case "modifica-beacon":
                    
                    break;
                    
                case "elimina-beacon":
                    
                    break;
                
            }
        } catch (IOException | ServletException f) {
            f.getMessage();
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
