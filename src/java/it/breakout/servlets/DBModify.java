/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import it.breakout.resources.Piano_Resource;
import it.breakout.resources.Mappa_Resource;
import it.breakout.resources.Utente_Resource;
import it.breakout.resources.Nodo_Resource;
import it.breakout.resources.Tronco_Resource;
import it.breakout.resources.Beacon_Resource;
import it.breakout.utility.FormFilter;
import it.breakout.models.Mappa;
import it.breakout.models.Nodo;
import it.breakout.models.Tronco;
import it.breakout.models.Pdi;
import it.breakout.models.Beacon;
import static it.breakout.utility.EnvVariables.*;
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
 
    /* Variabili per modifica-eliminazione utente */
    private Utente_Resource utente_resource = new Utente_Resource();
    private Integer id_utente;
    private String psw;
    private String psw_confirm;
    
    /* Variabili per inserimento-modifica-eliminazione tronco */
    private Tronco_Resource tronco_resource = new Tronco_Resource();
    private Tronco tronco = new Tronco();
    private Tronco tronco_old = new Tronco();
    private Integer id_nodo_1;
    private Integer id_nodo_2;
    private String id_n1_str;
    private String id_n2_str;
    private String id_beac_str;
    private Integer id_tronco;
    
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
    private String tipo_filtered;
 
    /* Variabili per inserimento-modifica-eliminazione beacon */
    private Beacon_Resource beacon_resource = new Beacon_Resource();
    private Beacon beacon = new Beacon();
    private Beacon beacon_old = new Beacon();
    private Integer id_beacon;
    private String codice_beacon;
    private String codice_beacon_filtered;
    private String fuoco;
    private Double fuoco_filtered;
    private String fumi;
    private Double fumi_filtered;
    private String ndc;
    private Double ndc_filtered;
    private String rischio;
    private Double rischio_filtered;
    
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
                    
                    break;

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
                    mappa_resource.update(mappa, id_mappa);
                    
                    exists = null;
                    
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
                    
                    exists = null;
                    
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
                    
                    exists = null;
                    
                    rd = request.getRequestDispatcher("/ObjectAccess?obj=grafo&nm="+nome_mappa);
                    rd.forward(request, response);
                    
                    break;
                    
                case "elimina-nodo":
                    
                    nodo_resource.deleteNodo(Integer.parseInt(request.getParameter("id_nodo")));
                    nome_mappa = request.getParameter("nm");
                    
                    rd = request.getRequestDispatcher("/ObjectAccess?obj=grafo&nm="+nome_mappa);
                    rd.forward(request, response);
                    
                    break;
                    
                /* Azioni relative alla tabella dei tronchi */
                case "aggiungi-tronco":
                    
                    nome_mappa = request.getParameter("nm");
                    
                    id_n1_str = request.getParameter("codice-1");
                    id_n2_str = request.getParameter("codice-2");
                    id_beac_str = request.getParameter("codice-beacon");
                    
                    /* Se la validazione lato client non dovesse funzionare si
                    viene reindirizzati alla pagina di gestione del grafo
                    */
                    if(id_n1_str.equals("") || id_n2_str.equals("") || id_beac_str.equals("")) {
                        rd = request.getRequestDispatcher("/ObjectAccess?obj=grafo&nm="+nome_mappa);
                        rd.forward(request, response);
                    }
                    
                    id_nodo_1 = Integer.parseInt(id_n1_str);
                    id_nodo_2 = Integer.parseInt(id_n2_str);
                    id_beacon = Integer.parseInt(id_beac_str);
                    
                    exists = tronco_resource.findArcByNodi(id_nodo_1, id_nodo_2).getID();
                    
                    if(exists == null){
                        
                        id_mappa = mappa_resource.findByNome(nome_mappa).getID_mappa();
                        
                        tronco.setNodiInteger(id_nodo_1, id_nodo_2);
                        tronco.setID_beacon(id_beacon);
                        tronco.setID_mappa(id_mappa);
                        
                        tronco_resource.insert(tronco);
                    }
                    
                    exists = null;
                    
                    rd = request.getRequestDispatcher("/ObjectAccess?obj=grafo&nm="+nome_mappa);
                    rd.forward(request, response);
                    
                    break;
                    
                case "modifica-tronco":
                    
                    nome_mappa = request.getParameter("nm");
                    id_n1_str = request.getParameter("codice-1");
                    id_n2_str = request.getParameter("codice-2");
                    id_beac_str = request.getParameter("codice-beacon");
                    
                    id_tronco = Integer.parseInt(request.getParameter("id_tronco"));
                    
                    /* Visto che i campi da controllare sono più di uno ho bisogno
                    di un oggetto che contenga i vecchi valori
                    */
                    tronco_old = tronco_resource.findArcByID(id_tronco);
                    Integer[] nodi_old = tronco_old.getNodiInteger();
                    
                    /* Controllo campi */
                    //exists = tronco_resource.findArcByNodi(id_nodo_1, id_nodo_2).getID();
                    if(!id_n1_str.equals("invariato") && !id_n1_str.equals("invariato")) {
                        tronco.setNodiInteger(Integer.parseInt(id_n1_str), Integer.parseInt(id_n2_str));
                    } else{
                        tronco.setNodiInteger(nodi_old[0], nodi_old[1]);
                    }
                    
                    if(!id_beac_str.equals("invariato")) {
                        tronco.setID_beacon(Integer.parseInt(id_beac_str));
                    } else {
                        tronco.setID_beacon(tronco_old.getID_beacon());
                    }
                    
                    tronco_resource.update(tronco, id_tronco);
                    
                    exists = null;
                    
                    rd = request.getRequestDispatcher("/ObjectAccess?obj=grafo&nm="+nome_mappa);
                    rd.forward(request, response);
                    
                    break;
                    
                case "elimina-tronco":
                    
                    tronco_resource.delete(Integer.parseInt(request.getParameter("id_tronco")));
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
                    tipo_filtered = form_filter.filtraTextArea(tipo);
                    
                    exists = nodo_resource.findByCodice(codice_pdi_filtered).getID();
                    
                    if(!codice_pdi_filtered.equals(DEFAULT_STRING)
                            && !Objects.equals(coord_x_filtered, DEFAULT_DOUBLE)
                            && !Objects.equals(coord_y_filtered, DEFAULT_DOUBLE)
                            && !Objects.equals(larghezza_filtered, DEFAULT_DOUBLE)
                            && !Objects.equals(lunghezza_filtered, DEFAULT_DOUBLE)
                            && !Objects.equals(tipo_filtered, DEFAULT_STRING)
                            && exists == null){
                        
                        id_mappa = mappa_resource.findByNome(nome_mappa).getID_mappa();
                        
                        pdi.setCodice(codice_pdi_filtered);
                        pdi.setCoord_X(coord_x_filtered);
                        pdi.setCoord_Y(coord_y_filtered);
                        pdi.setLarghezza(larghezza_filtered);
                        pdi.setLunghezza(lunghezza_filtered);
                        pdi.setID_mappa(id_mappa);
                        pdi.setTipo(tipo_filtered);
                        
                        nodo_resource.insertPdi(pdi);
                        
                    }
                    
                    exists = null;
                    
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
                    tipo_filtered = form_filter.filtraTextArea(tipo);
                    
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
                    
                    if(!tipo_filtered.equals(DEFAULT_STRING)) {
                        pdi.setTipo(tipo_filtered);
                    } else {
                        pdi.setTipo(pdi_old.getTipo());
                    }
                    
                    nodo_resource.updatePoi(pdi, id_pdi);
                    
                    exists = null;
                    
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
                    
                    nome_mappa = request.getParameter("nm");
                    codice_beacon = request.getParameter("codice");
                    coord_x = request.getParameter("coord-x");
                    coord_y = request.getParameter("coord-y");
                    fuoco = request.getParameter("fuoco");
                    fumi = request.getParameter("fumi");
                    ndc = request.getParameter("ndc");
                    rischio = request.getParameter("rischio");
                    
                    codice_beacon_filtered = form_filter.filtraCodice(codice_beacon);
                    coord_x_filtered = form_filter.filtraCoordinata(coord_x);
                    coord_y_filtered = form_filter.filtraCoordinata(coord_y);
                    fuoco_filtered = form_filter.filtraMisura(fuoco);
                    fumi_filtered = form_filter.filtraMisura(fumi);
                    ndc_filtered = form_filter.filtraMisura(ndc);
                    rischio_filtered = form_filter.filtraMisura(rischio);
                    
                    exists = beacon_resource.findByCodice(codice_beacon_filtered).getID_beacon();
                    id_mappa = mappa_resource.findByNome(nome_mappa).getID_mappa();
                    
                    if(!codice_beacon_filtered.equals(DEFAULT_STRING)
                            && !Objects.equals(coord_x_filtered, DEFAULT_DOUBLE)
                            && !Objects.equals(coord_y_filtered, DEFAULT_DOUBLE)
                            && !Objects.equals(fuoco_filtered, DEFAULT_DOUBLE)
                            && !Objects.equals(fumi_filtered, DEFAULT_DOUBLE)
                            && !Objects.equals(ndc_filtered, DEFAULT_DOUBLE)
                            && !Objects.equals(rischio_filtered, DEFAULT_DOUBLE)                            
                            && exists == null){
                        
                        beacon.setCodice(codice_beacon_filtered);
                        beacon.setCoord_X(coord_x_filtered);
                        beacon.setCoord_Y(coord_y_filtered);
                        beacon.setInd_fuoco(fuoco_filtered);
                        beacon.setInd_fumi(fumi_filtered);
                        beacon.setInd_NDC(ndc_filtered);
                        beacon.setInd_rischio(rischio_filtered);                        
                        beacon.setID_mappa(id_mappa);                        
                        
                        beacon_resource.insert(beacon);
                        
                    }
                    
                    exists = null;
                    
                    rd = request.getRequestDispatcher("/ObjectAccess?obj=beacon&nm="+id_mappa);
                    rd.forward(request, response);
                    
                    break;
                    
                case "modifica-beacon":
                    
                    nome_mappa = request.getParameter("nm");
                    codice_beacon = request.getParameter("codice");
                    coord_x = request.getParameter("coord-x");
                    coord_y = request.getParameter("coord-y");
                    fuoco = request.getParameter("fuoco");
                    fumi = request.getParameter("fumi");
                    ndc = request.getParameter("ndc");
                    rischio = request.getParameter("rischio");
                    id_beacon = Integer.parseInt(request.getParameter("id_beacon"));
                    
                    /* Visto che i campi da controllare sono più di uno ho bisogno
                    di un oggetto che contenga i vecchi valori
                    */
                    beacon_old = beacon_resource.findByID(id_beacon);
                    
                    codice_beacon_filtered = form_filter.filtraCodice(codice_beacon);
                    coord_x_filtered = form_filter.filtraCoordinata(coord_x);
                    coord_y_filtered = form_filter.filtraCoordinata(coord_y);
                    fuoco_filtered = form_filter.filtraMisura(fuoco);
                    fumi_filtered = form_filter.filtraMisura(fumi);
                    ndc_filtered = form_filter.filtraMisura(ndc);
                    rischio_filtered = form_filter.filtraMisura(rischio);
                    
                    exists = beacon_resource.findByCodice(codice_beacon_filtered).getID_beacon();
                    
                    if(!codice_beacon_filtered.equals(DEFAULT_STRING) && exists == null) {
                        beacon.setCodice(codice_beacon_filtered);
                    } else{
                        beacon.setCodice(beacon_old.getCodice());
                    }
                    
                    if(!Objects.equals(coord_x_filtered, DEFAULT_DOUBLE)) {
                        beacon.setCoord_X(coord_x_filtered);
                    } else {
                        beacon.setCoord_X(beacon_old.getCoord_X());
                    }
                    
                    if(!Objects.equals(coord_y_filtered, DEFAULT_DOUBLE)) {
                        beacon.setCoord_Y(coord_y_filtered);
                    } else {
                        beacon.setCoord_Y(beacon_old.getCoord_Y());
                    }
                    
                    if(!Objects.equals(fuoco_filtered, DEFAULT_DOUBLE)) {
                        beacon.setInd_fuoco(fuoco_filtered);
                    } else {
                        beacon.setInd_fuoco(beacon_old.getInd_fuoco());
                    }
                    
                    if(!Objects.equals(fumi_filtered, DEFAULT_DOUBLE)) {
                        beacon.setInd_fumi(fumi_filtered);
                    } else {
                        beacon.setInd_fumi(beacon_old.getInd_fumi());
                    }
                    
                    if(!Objects.equals(ndc_filtered, DEFAULT_DOUBLE)) {
                        beacon.setInd_NDC(ndc_filtered);
                    } else {
                        beacon.setInd_NDC(beacon_old.getInd_NDC());
                    }
                    
                    if(!Objects.equals(rischio_filtered, DEFAULT_DOUBLE)) {
                        beacon.setInd_rischio(rischio_filtered);
                    } else {
                        beacon.setInd_rischio(beacon_old.getInd_rischio());
                    }
                    
                    beacon_resource.update(beacon, id_beacon);
                    
                    id_mappa = mappa_resource.findByNome(nome_mappa).getID_mappa();
                    
                    exists = null;
                    
                    rd = request.getRequestDispatcher("/ObjectAccess?obj=beacon&nm="+id_mappa);
                    rd.forward(request, response);
                    
                    break;
                    
                case "elimina-beacon":
                    
                    beacon_resource.delete(Integer.parseInt(request.getParameter("id_beacon")));
                    nome_mappa = request.getParameter("nm");
                    
                    id_mappa = mappa_resource.findByNome(nome_mappa).getID_mappa();
                    
                    rd = request.getRequestDispatcher("/ObjectAccess?obj=beacon&nm="+id_mappa);
                    rd.forward(request, response);
                    
                    break;
                
                /* Default case -> errore */
                default:
                    
                    response.sendRedirect("500.jsp");
                    
                    break;
                
            }
        } catch (IOException | ServletException e) {
            System.out.println(e.getMessage());
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
