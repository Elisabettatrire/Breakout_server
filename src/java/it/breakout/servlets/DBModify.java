/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.servlets;

import java.io.IOException;
import java.util.Objects;
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

import static it.breakout.utility.EnvVariables.DEFAULT_DOUBLE;
import static it.breakout.utility.EnvVariables.DEFAULT_STRING;
import static it.breakout.utility.EnvVariables.URL_BEACON;
import static it.breakout.utility.EnvVariables.URL_GRAFO;
import static it.breakout.utility.EnvVariables.URL_PIANO;

/**
 *
 * @author Giovanni
 */
public class DBModify extends HttpServlet {

    private String azione;
    private String quota;
    private String nome_mappa;
    private Integer id_mappa;
    
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

                    aggiungiPiano(request);
                    
                    /* Ritorno alla lista dei piani che sarà aggiornata */
                    rd = request.getRequestDispatcher("/DBAccess");
                    rd.forward(request, response);

                    break;

                case "modifica-piano":

                    modificaPiano(request);
                    
                    rd = request.getRequestDispatcher("/DBAccess");
                    rd.forward(request, response);
                    
                    break;

                case "elimina-piano":

                    eliminaPiano(request);

                    rd = request.getRequestDispatcher("/DBAccess");
                    rd.forward(request, response);

                    break;

                /* Azioni relative alla tabella delle scale */

                /* Azioni relative alla tabella delle mappe */
                case "aggiungi-mappa":

                    quota = request.getParameter("nm");
                    
                    aggiungiMappa(request, quota);
                    
                    rd = request.getRequestDispatcher(URL_PIANO+quota);
                    rd.forward(request, response);

                    break;
                    
                case "modifica-mappa":

                    quota = request.getParameter("nm");
                    
                    modificaMappa(request);
                    
                    rd = request.getRequestDispatcher(URL_PIANO+quota);
                    rd.forward(request, response);

                    break;

                case "elimina-mappa":
                    
                    quota = request.getParameter("nm");

                    eliminaMappa(request);
                    
                    rd = request.getRequestDispatcher(URL_PIANO+quota);
                    rd.forward(request, response);

                    break;
                    
                /* Azioni relative alla tabella degli utenti (Pericolo SQLInjection?) */
                case "modifica-utente":
                    
                    modificaUtente(request);
                    
                    rd = request.getRequestDispatcher("/DBAccess");
                    rd.forward(request, response);

                    break;
                    
                case "elimina-utente":
                    
                    eliminaUtente(request);

                    rd = request.getRequestDispatcher("/DBAccess");
                    rd.forward(request, response);

                    break;
                    
                /* Azioni relative alla tabella dei nodi */
                case "aggiungi-nodo":
                    
                    nome_mappa = request.getParameter("nm");
                    
                    aggiungiNodo(request, nome_mappa);
                    
                    rd = request.getRequestDispatcher(URL_GRAFO+nome_mappa);
                    rd.forward(request, response);
                    
                    break;

                case "modifica-nodo":

                    nome_mappa = request.getParameter("nm");
                    
                    modificaNodo(request);
                    
                    rd = request.getRequestDispatcher(URL_GRAFO+nome_mappa);
                    rd.forward(request, response);
                    
                    break;
                    
                case "elimina-nodo":
                    
                    nome_mappa = request.getParameter("nm");
                    
                    eliminaNodo(request);
                    
                    rd = request.getRequestDispatcher(URL_GRAFO+nome_mappa);
                    rd.forward(request, response);
                    
                    break;
                    
                /* Azioni relative alla tabella dei tronchi */
                case "aggiungi-tronco":
                    
                    nome_mappa = request.getParameter("nm");
                    
                    aggiungiTronco(request, response, nome_mappa);
                    
                    rd = request.getRequestDispatcher(URL_GRAFO+nome_mappa);
                    rd.forward(request, response);
                    
                    break;
                    
                case "modifica-tronco":
                    
                    nome_mappa = request.getParameter("nm");
                    
                    modificaTronco(request, response, nome_mappa);
                    
                    rd = request.getRequestDispatcher(URL_GRAFO+nome_mappa);
                    rd.forward(request, response);
                    
                    break;
                    
                case "elimina-tronco":
                    
                    nome_mappa = request.getParameter("nm");
                    
                    eliminaTronco(request);
                    
                     rd = request.getRequestDispatcher(URL_GRAFO+nome_mappa);
                    rd.forward(request, response);
                    
                    break;
                
                /* Azioni relative alla tabella dei pdi */
                case "aggiungi-pdi":
                    
                    nome_mappa = request.getParameter("nm");
                    
                    aggiungiPDI(request, nome_mappa);
                    
                    rd = request.getRequestDispatcher(URL_GRAFO+nome_mappa);
                    rd.forward(request, response);
                    
                    break;

                case "modifica-pdi":

                    nome_mappa = request.getParameter("nm");
                    
                    modificaPDI(request);
                    
                    rd = request.getRequestDispatcher(URL_GRAFO+nome_mappa);
                    rd.forward(request, response);
                    
                    break;
                    
                case "elimina-pdi":
                    
                    nome_mappa = request.getParameter("nm");
                    
                    eliminaPDI(request);
                    
                    rd = request.getRequestDispatcher(URL_GRAFO+nome_mappa);
                    rd.forward(request, response);
                    
                    break;
                    
                /* Azioni relative alla tabella dei beacon */
                case "aggiungi-beacon":
                    
                    nome_mappa = request.getParameter("nm");
                    
                    id_mappa = aggiungiBeacon(request, nome_mappa);
                    
                    rd = request.getRequestDispatcher(URL_BEACON+id_mappa);
                    rd.forward(request, response);
                    
                    break;
                    
                case "modifica-beacon":
                    
                    nome_mappa = request.getParameter("nm");
                    
                    id_mappa = modificaBeacon(request, nome_mappa);
                    
                    rd = request.getRequestDispatcher(URL_BEACON+id_mappa);
                    rd.forward(request, response);
                    
                    break;
                    
                case "elimina-beacon":
                    
                    nome_mappa = request.getParameter("nm");
                    
                    id_mappa = eliminaBeacon(request, nome_mappa);
                    
                    rd = request.getRequestDispatcher(URL_BEACON+id_mappa);
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
    
    /**
     * Le funzioni sottostanti svolgono le azioni di aggiunta, modifica ed
     * eliminazione dei dati dal DB
     *
     * @param request servlet request
     *
     */
    
    public void aggiungiPiano(HttpServletRequest request) {
        
        FormFilter form_filter = new FormFilter();
        Piano_Resource piano_resource = new Piano_Resource();
        
        String quota_filtered = form_filter.filtraQuota(request.getParameter("quota"));
        /* Controllo se esiste un piano con lo stesso nome */
        Integer exists = piano_resource.findByQuota(quota_filtered).getID_piano();

        if(!quota_filtered.equals(DEFAULT_STRING) && exists==null) {
            piano_resource.insert(quota_filtered);
        }

        /* Pulisco la variabile di controllo per poterla riutilizzare */
        exists = null;
        
    }
    
    public void modificaPiano(HttpServletRequest request) {
        
        FormFilter form_filter = new FormFilter();
        Piano_Resource piano_resource = new Piano_Resource();
        
        Integer id_piano = Integer.parseInt(request.getParameter("id_piano"));
        String quota_filtered = form_filter.filtraQuota(request.getParameter("quota"));
        /* Controllo che non esista già la quota inserita */
        Integer exists = piano_resource.findByQuota(quota_filtered).getID_piano();

        /* Il controllo se il campo viene lasciato vuoto viene fatto
        dopo che questo è stato filtrato, almeno se sono stati
        inseriti dei caratteri non validi il valore rimane uguale */
        if(!quota_filtered.equals(DEFAULT_STRING) && exists==null) {
            piano_resource.update(quota_filtered, id_piano);
        }

        exists = null;
        
    }
    
    public void eliminaPiano(HttpServletRequest request) {
        
        Piano_Resource piano_resource = new Piano_Resource();
        
        piano_resource.delete(Integer.parseInt(request.getParameter("id_piano")));
        
    }
    
    public void aggiungiMappa(HttpServletRequest request, String quota) {
        
        FormFilter form_filter = new FormFilter();
        Piano_Resource piano_resource = new Piano_Resource();
        Mappa_Resource mappa_resource = new Mappa_Resource();
        Mappa mappa = new Mappa();
        
        String nome_mappa_filtered = form_filter.filtraNomeMappa(request.getParameter("nome-mappa"));
        String url_immagine = request.getParameter("url-immagine"); // non c'è bisogno di filtrarlo

        /* Controllo se esiste una mappa con lo stesso nome */
        Integer exists = mappa_resource.findByNome(nome_mappa_filtered).getID_mappa();

        if(!nome_mappa_filtered.equals(DEFAULT_STRING) && exists==null) {

            mappa.setNome(nome_mappa_filtered);
            mappa.setID_piano(piano_resource.findByQuota(quota).getID_piano()); // veloce
            mappa.setUrlImmagine(url_immagine);
            mappa_resource.insert(mappa);
        }

        exists = null;
        
    }
    public void modificaMappa(HttpServletRequest request) {
        
        FormFilter form_filter = new FormFilter();
        Mappa_Resource mappa_resource = new Mappa_Resource();
        Mappa mappa = new Mappa();
        Mappa mappa_old = new Mappa();
        
        String nome_mappa_filtered = form_filter.filtraNomeMappa(request.getParameter("nome-mappa"));
        String url_immagine = request.getParameter("url-immagine"); // non c'è bisogno di filtrarlo
        Integer id_mappa_local = Integer.parseInt(request.getParameter("id_mappa"));

        /* Visto che i campi da controllare sono più di uno ho bisogno
        di un oggetto che contenga i vecchi valori
        */
        mappa_old = mappa_resource.findByID(id_mappa_local);
        
        Integer exists = mappa_resource.findByNome(nome_mappa_filtered).getID_mappa();

        /* Aggiornamento nome mappa */
        if(!nome_mappa_filtered.equals(DEFAULT_STRING) && exists==null) {
            mappa.setNome(nome_mappa_filtered);
        } else { // Se il campo è vuoto bisogna mantenere il valore precedente
            mappa.setNome(mappa_old.getNome());
        }

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
        
    }
    
    public void eliminaMappa(HttpServletRequest request) {
        
        Mappa_Resource mappa_resource = new Mappa_Resource();
        
        mappa_resource.delete(Integer.parseInt(request.getParameter("id_mappa")));
        
    }
    
    public void modificaUtente(HttpServletRequest request) {
        
        Utente_Resource utente_resource = new Utente_Resource();
        
        Integer id_utente = Integer.parseInt(request.getParameter("id_utente"));
        String psw = request.getParameter("psw");
        String psw_confirm = request.getParameter("psw-confirm");

        if(psw.equals(psw_confirm)) {
            utente_resource.update(psw, id_utente);
        }
        
    }
    
    public void eliminaUtente(HttpServletRequest request) {
        
        Utente_Resource utente_resource = new Utente_Resource();
        
        utente_resource.delete(Integer.parseInt(request.getParameter("id_utente")));
        
    }
    
    public void aggiungiNodo(HttpServletRequest request, String nome_mappa) {
        
        FormFilter form_filter = new FormFilter();
        Nodo_Resource nodo_resource = new Nodo_Resource();
        Mappa_Resource mappa_resource = new Mappa_Resource();
        Nodo nodo = new Nodo();
        
        String codice_nodo_filtered = form_filter.filtraCodice(request.getParameter("codice"));
        Double coord_x_filtered = form_filter.filtraCoordinata(request.getParameter("coord-x"));
        Double coord_y_filtered = form_filter.filtraCoordinata(request.getParameter("coord-Y"));
        Double larghezza_filtered = form_filter.filtraMisura(request.getParameter("larghezza"));

        Integer exists = nodo_resource.findByCodice(codice_nodo_filtered).getID();

        if(!codice_nodo_filtered.equals(DEFAULT_STRING)
                && !Objects.equals(coord_x_filtered, DEFAULT_DOUBLE)
                && !Objects.equals(coord_y_filtered, DEFAULT_DOUBLE)
                && !Objects.equals(larghezza_filtered, DEFAULT_DOUBLE)
                && exists == null){

            Integer id_mappa_local = mappa_resource.findByNome(nome_mappa).getID_mappa();

            nodo.setCodice(codice_nodo_filtered);
            nodo.setCoord_X(coord_x_filtered);
            nodo.setCoord_Y(coord_y_filtered);
            nodo.setLarghezza(larghezza_filtered);
            nodo.setID_mappa(id_mappa_local);

            nodo_resource.insertNodo(nodo);

        }

        exists = null;
        
    }
    
    public void modificaNodo(HttpServletRequest request) {
        
        FormFilter form_filter = new FormFilter();
        Nodo_Resource nodo_resource = new Nodo_Resource();
        Nodo nodo = new Nodo();
        Nodo nodo_old = new Nodo();
        
        String codice_nodo_filtered = form_filter.filtraCodice(request.getParameter("codice"));
        Double coord_x_filtered = form_filter.filtraCoordinata(request.getParameter("coord-x"));
        Double coord_y_filtered = form_filter.filtraCoordinata(request.getParameter("coord-y"));
        Double larghezza_filtered = form_filter.filtraMisura(request.getParameter("larghezza"));
        Integer id_nodo = Integer.parseInt(request.getParameter("id_nodo"));

        /* Visto che i campi da controllare sono più di uno ho bisogno
        di un oggetto che contenga i vecchi valori
        */
        nodo_old = nodo_resource.findByID(id_nodo);

        /* Controllo campi */
        Integer exists = nodo_resource.findByCodice(codice_nodo_filtered).getID();
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
        
    }
    
    public void eliminaNodo(HttpServletRequest request) {
        
        Nodo_Resource nodo_resource = new Nodo_Resource();
        
        nodo_resource.deleteNodo(Integer.parseInt(request.getParameter("id_nodo")));
        
    }
    
    public void aggiungiTronco(HttpServletRequest request, HttpServletResponse response,
            String nome_mappa) throws ServletException, IOException {
        
        Tronco_Resource tronco_resource = new Tronco_Resource();
        Mappa_Resource mappa_resource = new Mappa_Resource();
        Tronco tronco = new Tronco();
        
        String id_n1_str = request.getParameter("codice-1");
        String id_n2_str = request.getParameter("codice-2");
        String id_beac_str = request.getParameter("codice-beacon");

        /* Se la validazione lato client non dovesse funzionare si
        viene reindirizzati alla pagina di gestione del grafo
        */
        if(id_n1_str.equals("")
                || id_n2_str.equals("")
                || id_beac_str.equals("")
                || id_n1_str.equals(id_n2_str)) {
            RequestDispatcher rd = request.getRequestDispatcher(URL_GRAFO+nome_mappa);
            rd.forward(request, response);
        }

        Integer id_nodo_1 = Integer.parseInt(id_n1_str);
        Integer id_nodo_2 = Integer.parseInt(id_n2_str);
        Integer id_beacon = Integer.parseInt(id_beac_str);
        
        /* Controllo se esiste già un tronco che abbia come estremi i nodi selezionati */
        Integer exists = tronco_resource.findArcByNodi(id_nodo_1, id_nodo_2).getID();
        if(exists == null) {
            exists = tronco_resource.findArcByNodi(id_nodo_2, id_nodo_1).getID();
        }
        
        if(exists == null){

            Integer id_mappa_local = mappa_resource.findByNome(nome_mappa).getID_mappa();

            tronco.setNodiInteger(id_nodo_1, id_nodo_2);
            tronco.setID_beacon(id_beacon);
            tronco.setID_mappa(id_mappa_local);

            tronco_resource.insert(tronco);
        }

        exists = null;
        
    }
    
    public void modificaTronco(HttpServletRequest request, HttpServletResponse response,
            String nome_mappa) throws ServletException, IOException {
        
        Tronco_Resource tronco_resource = new Tronco_Resource();
        Tronco tronco = new Tronco();
        Tronco tronco_old = new Tronco();
        
        String id_n1_str = request.getParameter("codice-1");
        String id_n2_str = request.getParameter("codice-2");
        String id_beac_str = request.getParameter("codice-beacon");

        Integer id_tronco = Integer.parseInt(request.getParameter("id_tronco"));

        /* Visto che i campi da controllare sono più di uno ho bisogno
        di un oggetto che contenga i vecchi valori
        */
        tronco_old = tronco_resource.findArcByID(id_tronco);
        Integer[] nodi_old = tronco_old.getNodiInteger();

        /* Se la validazione lato client non dovesse funzionare si
        viene reindirizzati alla pagina di gestione del grafo
        */
        if(id_n1_str.equals("")
                || id_n2_str.equals("")
                || id_beac_str.equals("")
                || id_n1_str.equals(id_n2_str)) {
            RequestDispatcher rd;
            rd = request.getRequestDispatcher(URL_GRAFO+nome_mappa);
            rd.forward(request, response);
        }

        Integer id_nodo_1 = Integer.parseInt(id_n1_str);
        Integer id_nodo_2 = Integer.parseInt(id_n2_str);
        Integer id_beacon = Integer.parseInt(id_beac_str);

        /* Controllo se esiste già un tronco che abbia come estremi i nodi selezionati */
        Integer exists = tronco_resource.findArcByNodi(id_nodo_1, id_nodo_2).getID();
        if(exists == null) {
            exists = tronco_resource.findArcByNodi(id_nodo_2, id_nodo_1).getID();
        }
        
        /* Controllo campi */
        if(exists == null) {
            tronco.setNodiInteger(id_nodo_1, id_nodo_2);
        } else{
            tronco.setNodiInteger(nodi_old[0], nodi_old[1]);
        }

        tronco.setID_beacon(id_beacon);

        tronco_resource.update(tronco, id_tronco);

        exists = null;
        
    }
    
    public void eliminaTronco(HttpServletRequest request) {
        
        Tronco_Resource tronco_resource = new Tronco_Resource();
        
        tronco_resource.delete(Integer.parseInt(request.getParameter("id_tronco")));
        
    }
    
    public void aggiungiPDI(HttpServletRequest request, String nome_mappa) {
        
        FormFilter form_filter = new FormFilter();
        Nodo_Resource nodo_resource = new Nodo_Resource();
        Mappa_Resource mappa_resource = new Mappa_Resource();
        Pdi pdi = new Pdi();

        String codice_pdi_filtered = form_filter.filtraCodice(request.getParameter("codice"));
        Double coord_x_filtered = form_filter.filtraCoordinata(request.getParameter("coord-x"));
        Double coord_y_filtered = form_filter.filtraCoordinata(request.getParameter("coord-y"));
        Double larghezza_filtered = form_filter.filtraMisura(request.getParameter("larghezza"));
        Double lunghezza_filtered = form_filter.filtraMisura(request.getParameter("lunghezza"));
        String tipo_filtered = form_filter.filtraTextArea(request.getParameter("tipo"));

        Integer exists = nodo_resource.findByCodice(codice_pdi_filtered).getID();

        if(!codice_pdi_filtered.equals(DEFAULT_STRING)
                && !Objects.equals(coord_x_filtered, DEFAULT_DOUBLE)
                && !Objects.equals(coord_y_filtered, DEFAULT_DOUBLE)
                && !Objects.equals(larghezza_filtered, DEFAULT_DOUBLE)
                && !Objects.equals(lunghezza_filtered, DEFAULT_DOUBLE)
                && !Objects.equals(tipo_filtered, DEFAULT_STRING)
                && exists == null){

            Integer id_mappa_local = mappa_resource.findByNome(nome_mappa).getID_mappa();

            pdi.setCodice(codice_pdi_filtered);
            pdi.setCoord_X(coord_x_filtered);
            pdi.setCoord_Y(coord_y_filtered);
            pdi.setLarghezza(larghezza_filtered);
            pdi.setLunghezza(lunghezza_filtered);
            pdi.setID_mappa(id_mappa_local);
            pdi.setTipo(tipo_filtered);

            nodo_resource.insertPdi(pdi);

        }

        exists = null;
        
    }

    public void modificaPDI(HttpServletRequest request) {
        
        FormFilter form_filter = new FormFilter();
        Nodo_Resource nodo_resource = new Nodo_Resource();
        Pdi pdi = new Pdi();
        Pdi pdi_old = new Pdi();
        
        String codice_pdi_filtered = form_filter.filtraCodice(request.getParameter("codice"));
        Double coord_x_filtered = form_filter.filtraCoordinata(request.getParameter("coord-x"));
        Double coord_y_filtered = form_filter.filtraCoordinata(request.getParameter("coord-y"));
        Double larghezza_filtered = form_filter.filtraMisura(request.getParameter("larghezza"));
        Double lunghezza_filtered = form_filter.filtraMisura(request.getParameter("lunghezza"));
        String tipo_filtered = form_filter.filtraTextArea(request.getParameter("tipo"));
        Integer id_pdi = Integer.parseInt(request.getParameter("id_pdi"));

        /* Visto che i campi da controllare sono più di uno ho bisogno
        di un oggetto che contenga i vecchi valori
        */
        pdi_old = nodo_resource.findPoiByID(id_pdi);

        /* Controllo campi */
        Integer exists = nodo_resource.findByCodice(codice_pdi_filtered).getID();
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
        
    }
    
    public void eliminaPDI(HttpServletRequest request) {
        
        Nodo_Resource nodo_resource = new Nodo_Resource();
        
        nodo_resource.deleteNodo(Integer.parseInt(request.getParameter("id_pdi")));
        
    }
    
    public Integer aggiungiBeacon(HttpServletRequest request, String nome_mappa) {
        
        FormFilter form_filter = new FormFilter();
        Mappa_Resource mappa_resource = new Mappa_Resource();
        Beacon_Resource beacon_resource = new Beacon_Resource();
        Beacon beacon = new Beacon();

        String codice_beacon_filtered = form_filter.filtraCodice(request.getParameter("codice"));
        Double coord_x_filtered = form_filter.filtraCoordinata(request.getParameter("coord-x"));
        Double coord_y_filtered = form_filter.filtraCoordinata(request.getParameter("coord-y"));
        Double fuoco_filtered = form_filter.filtraMisura(request.getParameter("fuoco"));
        Double fumi_filtered = form_filter.filtraMisura(request.getParameter("fumi"));
        Double ndc_filtered = form_filter.filtraMisura(request.getParameter("ndc"));
        Double rischio_filtered = form_filter.filtraMisura(request.getParameter("rischio"));

        Integer exists = beacon_resource.findByCodice(codice_beacon_filtered).getID_beacon();
        Integer id_mappa_local = mappa_resource.findByNome(nome_mappa).getID_mappa();

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
            beacon.setID_mappa(id_mappa_local);                        

            beacon_resource.insert(beacon);

        }

        exists = null;
        
        return id_mappa;
        
    }
    
    public Integer modificaBeacon(HttpServletRequest request, String nome_mappa) {
        
        FormFilter form_filter = new FormFilter();
        Mappa_Resource mappa_resource = new Mappa_Resource();
        Beacon_Resource beacon_resource = new Beacon_Resource();
        Beacon beacon = new Beacon();
        Beacon beacon_old = new Beacon();
        
        String codice_beacon_filtered = form_filter.filtraCodice(request.getParameter("codice"));
        Double coord_x_filtered = form_filter.filtraCoordinata(request.getParameter("coord-x"));
        Double coord_y_filtered = form_filter.filtraCoordinata(request.getParameter("coord-y"));
        Double fuoco_filtered = form_filter.filtraMisura(request.getParameter("fuoco"));
        Double fumi_filtered = form_filter.filtraMisura(request.getParameter("fumi"));
        Double ndc_filtered = form_filter.filtraMisura(request.getParameter("ndc"));
        Double rischio_filtered = form_filter.filtraMisura(request.getParameter("rischio"));
        Integer id_beacon = Integer.parseInt(request.getParameter("id_beacon"));

        /* Visto che i campi da controllare sono più di uno ho bisogno
        di un oggetto che contenga i vecchi valori
        */
        beacon_old = beacon_resource.findByID(id_beacon);

        Integer exists = beacon_resource.findByCodice(codice_beacon_filtered).getID_beacon();

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
        
        return id_mappa;
    }
    
    
    public Integer eliminaBeacon(HttpServletRequest request, String nome_mappa) {
        
        Mappa_Resource mappa_resource = new Mappa_Resource();
        Beacon_Resource beacon_resource = new Beacon_Resource();
        
        beacon_resource.delete(Integer.parseInt(request.getParameter("id_beacon")));

        id_mappa = mappa_resource.findByNome(nome_mappa).getID_mappa();
        
        return id_mappa;
        
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
