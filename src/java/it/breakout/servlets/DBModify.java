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

import it.breakout.services.PianoService;
import it.breakout.services.MappaService;
import it.breakout.services.UtenteService;
import it.breakout.services.NodoService;
import it.breakout.services.TroncoService;
import it.breakout.services.BeaconService;
import it.breakout.utility.FormFilter;
import it.breakout.models.Mappa;
import it.breakout.models.Nodo;
import it.breakout.models.Tronco;
import it.breakout.models.Scala;
import it.breakout.models.Collegamento;
import it.breakout.models.Pdi;
import it.breakout.models.Beacon;

import static it.breakout.utility.EnvVariables.DEFAULT_DOUBLE;
import static it.breakout.utility.EnvVariables.DEFAULT_STRING;
import static it.breakout.utility.EnvVariables.URL_MAPPE;
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
                
                /* Svuotamento tabella dei beacon */
                case "elimina-beacon-all":
                    
                    eliminaBeaconAll();
                    
                    /* Ritorno alla pagina corrente */
                    rd = request.getRequestDispatcher(URL_MAPPE);
                    rd.forward(request, response);
                    
                    break;
                    
                /* Azioni relative alla tabella dei piani */
                case "aggiungi-piano":

                    aggiungiPiano(request);
                    
                    /* Ritorno alla lista dei piani che sarà aggiornata */
                    rd = request.getRequestDispatcher(URL_MAPPE);
                    rd.forward(request, response);

                    break;

                case "modifica-piano":

                    modificaPiano(request);
                    
                    rd = request.getRequestDispatcher(URL_MAPPE);
                    rd.forward(request, response);
                    
                    break;

                case "elimina-piano":

                    eliminaPiano(request);

                    rd = request.getRequestDispatcher(URL_MAPPE);
                    rd.forward(request, response);

                    break;

                /* Azioni relative alla tabella delle scale */
                case "aggiungi-scala":
                    
                    aggiungiScala(request, response);
                    
                    rd = request.getRequestDispatcher(URL_MAPPE);
                    rd.forward(request, response);
                    
                    break;
                    
                case "modifica-scala":
                    
                    modificaScala(request, response);
                    
                    rd = request.getRequestDispatcher(URL_MAPPE);
                    rd.forward(request, response);
                    
                    break;
                    
                case "elimina-scala":
                
                    eliminaScala(request);
                    
                    rd = request.getRequestDispatcher(URL_MAPPE);
                    rd.forward(request, response);
                    
                    break;
                     
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
                    
                /* Azioni relative alla tabella dei collegamenti */
                case "aggiungi-collegamento":
                    
                    quota = request.getParameter("nm");
                    
                    aggiungiCollegamento(request, response);
                    
                    rd = request.getRequestDispatcher(URL_PIANO+quota);
                    rd.forward(request, response);
                    
                    break;
                    
                case "modifica-collegamento":
                    
                    quota = request.getParameter("nm");
                    
                    modificaCollegamento(request, response);
                    
                    rd = request.getRequestDispatcher(URL_PIANO+quota);
                    rd.forward(request, response);
                    
                    break;
                    
                case "elimina-collegamento":
                
                    quota = request.getParameter("nm");

                    eliminaCollegamento(request);
                    
                    rd = request.getRequestDispatcher(URL_PIANO+quota);
                    rd.forward(request, response);
                    
                    break;
                    
                /* Azioni relative alla tabella degli utenti (Pericolo SQLInjection?) */
                case "modifica-utente":
                    
                    modificaUtente(request);
                    
                    rd = request.getRequestDispatcher(URL_MAPPE);
                    rd.forward(request, response);

                    break;
                    
                case "elimina-utente":
                    
                    eliminaUtente(request);

                    rd = request.getRequestDispatcher(URL_MAPPE);
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
                    System.out.println("default case");
                    
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
    
    public void eliminaBeaconAll() {
        
        BeaconService beaconService = new BeaconService();
        
        beaconService.deleteAll();
        
    }
    
    public void aggiungiPiano(HttpServletRequest request) {
        
        FormFilter formFilter = new FormFilter();
        PianoService pianoService = new PianoService();
        
        String quota_filtered = formFilter.filtraQuota(request.getParameter("quota"));
        /* Controllo se esiste un piano con lo stesso nome */
        Integer exists = pianoService.findByQuota(quota_filtered).getID_piano();

        if(!quota_filtered.equals(DEFAULT_STRING) && exists==null) {
            pianoService.insert(quota_filtered);
        }

        /* Pulisco la variabile di controllo per poterla riutilizzare */
        exists = null;
        
    }
    
    public void modificaPiano(HttpServletRequest request) {
        
        FormFilter formFilter = new FormFilter();
        PianoService pianoService = new PianoService();
        
        Integer id_piano = Integer.parseInt(request.getParameter("id_piano"));
        String quota_filtered = formFilter.filtraQuota(request.getParameter("quota"));
        /* Controllo che non esista già la quota inserita */
        Integer exists = pianoService.findByQuota(quota_filtered).getID_piano();

        /* Il controllo se il campo viene lasciato vuoto viene fatto
        dopo che questo è stato filtrato, almeno se sono stati
        inseriti dei caratteri non validi il valore rimane uguale */
        if(!quota_filtered.equals(DEFAULT_STRING) && exists==null) {
            pianoService.update(quota_filtered, id_piano);
        }

        exists = null;
        
    }
    
    public void eliminaPiano(HttpServletRequest request) {
        
        PianoService pianoService = new PianoService();
        
        pianoService.delete(Integer.parseInt(request.getParameter("id_piano")));
        
    }
    
    public void aggiungiScala(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        FormFilter formFilter = new FormFilter();
        TroncoService troncoService = new TroncoService();
        Scala scala = new Scala();
        
        String id_n1_str = request.getParameter("codice-1");
        String id_n2_str = request.getParameter("codice-2");
        String id_beac_str = request.getParameter("codice-beacon");
        Double lunghezza = formFilter.filtraMisura(request.getParameter("lunghezza"));

        /* Se la validazione lato client non dovesse funzionare si
        viene reindirizzati alla pagina di gestione del grafo
        */
        if(id_n1_str.equals("")
                || id_n2_str.equals("")
                || id_beac_str.equals("")
                || id_n1_str.equals(id_n2_str)) {
            RequestDispatcher rd = request.getRequestDispatcher(URL_MAPPE);
            rd.forward(request, response);
        }

        Integer id_nodo_1 = Integer.parseInt(id_n1_str);
        Integer id_nodo_2 = Integer.parseInt(id_n2_str);
        Integer id_beacon = Integer.parseInt(id_beac_str);
        
        /* Controllo se esiste già un collegamento che abbia come estremi i nodi selezionati */
        Integer exists = troncoService.findByNodi(id_nodo_1, id_nodo_2).getID();
        if(exists == null) {
            exists = troncoService.findByNodi(id_nodo_2, id_nodo_1).getID();
        }
        
        if(exists == null && !Objects.equals(lunghezza, DEFAULT_DOUBLE)){
                
            scala.setNodiInteger(id_nodo_1, id_nodo_2);
            scala.setID_beacon(id_beacon);
            scala.setLunghezza(lunghezza);

            troncoService.insertScala(scala);

        }

        exists = null;
        
    }
    
    public void modificaScala(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        FormFilter formFilter = new FormFilter();
        TroncoService troncoService = new TroncoService();
        Scala scala = new Scala();
        
        String id_n1_str = request.getParameter("codice-1");
        String id_n2_str = request.getParameter("codice-2");
        String id_beac_str = request.getParameter("codice-beacon");
        Double lunghezza = formFilter.filtraMisura(request.getParameter("lunghezza"));

        Integer id_scala = Integer.parseInt(request.getParameter("id_scala"));

        /* Visto che i campi da controllare sono più di uno ho bisogno
        di un oggetto che contenga i vecchi valori
        */
        Scala scala_old = troncoService.findStairByID(id_scala);
        Integer[] nodi_old = scala_old.getNodiInteger();

        /* Se la validazione lato client non dovesse funzionare si
        viene reindirizzati alla pagina di gestione del grafo
        */
        if(id_n1_str.equals("")
                || id_n2_str.equals("")
                || id_beac_str.equals("")
                || id_n1_str.equals(id_n2_str)) {
            RequestDispatcher rd;
            rd = request.getRequestDispatcher(URL_MAPPE);
            rd.forward(request, response);
        }

        Integer id_nodo_1 = Integer.parseInt(id_n1_str);
        Integer id_nodo_2 = Integer.parseInt(id_n2_str);
        Integer id_beacon = Integer.parseInt(id_beac_str);

        /* Controllo se esiste già un collegamento che abbia come estremi i nodi selezionati
         * (posso usare la funzione per i tronchi tanto è uguale)
         */
        Integer exists = troncoService.findByNodi(id_nodo_1, id_nodo_2).getID();
        if(exists == null) {
            exists = troncoService.findByNodi(id_nodo_2, id_nodo_1).getID();
        }
        
        /* Controllo campi */
        if(exists == null) {
            scala.setNodiInteger(id_nodo_1, id_nodo_2);
        } else{
            scala.setNodiInteger(nodi_old[0], nodi_old[1]);
        }
        
        if(!Objects.equals(lunghezza, DEFAULT_DOUBLE)) {
            scala.setLunghezza(lunghezza);
        } else {
            scala.setLunghezza(scala_old.getLunghezza());
        }

        scala.setID_beacon(id_beacon);

        troncoService.updateScala(scala, id_scala);

        exists = null;
        
    }
    
    public void eliminaScala(HttpServletRequest request) {
        
        TroncoService troncoService = new TroncoService();
        
        troncoService.delete(Integer.parseInt(request.getParameter("id_scala")));
        
    }
    
    public void aggiungiMappa(HttpServletRequest request, String quota) {
        
        FormFilter formFilter = new FormFilter();
        PianoService pianoService = new PianoService();
        MappaService mappaService = new MappaService();
        Mappa mappa = new Mappa();
        
        String nome_mappa_filtered = formFilter.filtraNomeMappa(request.getParameter("nome-mappa"));
        //String url_immagine = request.getParameter("url-immagine"); // non c'è bisogno di filtrarlo

        /* Controllo se esiste una mappa con lo stesso nome */
        Integer exists = mappaService.findByNome(nome_mappa_filtered).getID_mappa();

        if(!nome_mappa_filtered.equals(DEFAULT_STRING) && exists==null) {

            mappa.setNome(nome_mappa_filtered);
            mappa.setID_piano(pianoService.findByQuota(quota).getID_piano()); // veloce
            //mappa.setUrlImmagine(url_immagine);
            mappaService.insert(mappa);
        }

        exists = null;
        
    }
    
    public void modificaMappa(HttpServletRequest request) {
        
        FormFilter formFilter = new FormFilter();
        MappaService mappaService = new MappaService();
        Mappa mappa = new Mappa();        
        
        String nome_mappa_filtered = formFilter.filtraNomeMappa(request.getParameter("nome-mappa"));
        Integer id_mappa_local = Integer.parseInt(request.getParameter("id_mappa"));

        /* Visto che i campi da controllare sono più di uno ho bisogno
        di un oggetto che contenga i vecchi valori
        */
        Mappa mappa_old = mappaService.findByID(id_mappa_local);
        
        Integer exists = mappaService.findByNome(nome_mappa_filtered).getID_mappa();

        /* Aggiornamento nome mappa */
        if(!nome_mappa_filtered.equals(DEFAULT_STRING) && exists==null) {
            mappa.setNome(nome_mappa_filtered);
        } else { // Se il campo è vuoto bisogna mantenere il valore precedente
            mappa.setNome(mappa_old.getNome());
        }

        /* Invio query */
        mappaService.update(mappa, id_mappa_local);

        exists = null;
        
    }
    
    public void eliminaMappa(HttpServletRequest request) {
        
        MappaService mappaService = new MappaService();
        
        mappaService.delete(Integer.parseInt(request.getParameter("id_mappa")));
        
    }
    
    public void aggiungiCollegamento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        FormFilter formFilter = new FormFilter();
        TroncoService troncoService = new TroncoService();
        NodoService nodoService = new NodoService();
        Collegamento collegamento = new Collegamento();
        
        String id_n1_str = request.getParameter("codice-1");
        String id_n2_str = request.getParameter("codice-2");
        String id_beac_str = request.getParameter("codice-beacon");
        Double lunghezza = formFilter.filtraMisura(request.getParameter("lunghezza"));

        /* Se la validazione lato client non dovesse funzionare si
        viene reindirizzati alla pagina di gestione del grafo
        */
        if(id_n1_str.equals("")
                || id_n2_str.equals("")
                || id_beac_str.equals("")
                || id_n1_str.equals(id_n2_str)) {
            RequestDispatcher rd = request.getRequestDispatcher(URL_MAPPE);
            rd.forward(request, response);
        }

        Integer id_nodo_1 = Integer.parseInt(id_n1_str);
        Integer id_nodo_2 = Integer.parseInt(id_n2_str);
        Integer id_beacon = Integer.parseInt(id_beac_str);
        Integer id_piano = nodoService.findByID(id_nodo_1).getID_piano();
        
        /* Controllo se esiste già un collegamento che abbia come estremi i nodi selezionati */
        Integer exists = troncoService.findByNodi(id_nodo_1, id_nodo_2).getID();
        if(exists == null) {
            exists = troncoService.findByNodi(id_nodo_2, id_nodo_1).getID();
        }
        
        if(exists == null && !Objects.equals(lunghezza, DEFAULT_DOUBLE)){

            collegamento.setNodiInteger(id_nodo_1, id_nodo_2);
            collegamento.setLunghezza(lunghezza);
            collegamento.setID_beacon(id_beacon);
            collegamento.setID_piano(id_piano);

            troncoService.insertCollegamento(collegamento);
        }

        exists = null;
        
    }
    
    public void modificaCollegamento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        FormFilter formFilter = new FormFilter();
        TroncoService troncoService = new TroncoService();
        Collegamento collegamento = new Collegamento();
        
        String id_n1_str = request.getParameter("codice-1");
        String id_n2_str = request.getParameter("codice-2");
        String id_beac_str = request.getParameter("codice-beacon");
        Double lunghezza = formFilter.filtraMisura(request.getParameter("lunghezza"));

        Integer id_collegamento = Integer.parseInt(request.getParameter("id_collegamento"));

        /* Visto che i campi da controllare sono più di uno ho bisogno
        di un oggetto che contenga i vecchi valori
        */
        Collegamento collegamento_old = troncoService.findLinkByID(id_collegamento);
        Integer[] nodi_old = collegamento_old.getNodiInteger();

        /* Se la validazione lato client non dovesse funzionare si
        viene reindirizzati alla pagina di gestione del grafo
        */
        if(id_n1_str.equals("")
                || id_n2_str.equals("")
                || id_beac_str.equals("")
                || id_n1_str.equals(id_n2_str)) {
            RequestDispatcher rd;
            rd = request.getRequestDispatcher(URL_MAPPE);
            rd.forward(request, response);
        }

        Integer id_nodo_1 = Integer.parseInt(id_n1_str);
        Integer id_nodo_2 = Integer.parseInt(id_n2_str);
        Integer id_beacon = Integer.parseInt(id_beac_str);

        /* Controllo se esiste già un collegamento che abbia come estremi i nodi selezionati
         * (posso usare la funzione per i tronchi tanto è uguale)
         */
        Integer exists = troncoService.findByNodi(id_nodo_1, id_nodo_2).getID();
        if(exists == null) {
            exists = troncoService.findByNodi(id_nodo_2, id_nodo_1).getID();
        }
        
        /* Controllo campi */
        if(exists == null) {
            collegamento.setNodiInteger(id_nodo_1, id_nodo_2);
        } else{
            collegamento.setNodiInteger(nodi_old[0], nodi_old[1]);
        }
        
        if(!Objects.equals(lunghezza, DEFAULT_DOUBLE)) {
            collegamento.setLunghezza(lunghezza);
        } else {
            collegamento.setLunghezza(collegamento_old.getLunghezza());
        }

        collegamento.setID_beacon(id_beacon);

        troncoService.updateCollegamento(collegamento, id_collegamento);

        exists = null;
        
    }
    
    public void eliminaCollegamento(HttpServletRequest request) {
        
        TroncoService troncoService = new TroncoService();
        
        troncoService.delete(Integer.parseInt(request.getParameter("id_collegamento")));
        
    }
    
    public void modificaUtente(HttpServletRequest request) {
        
        UtenteService utenteService = new UtenteService();
        
        Integer id_utente = Integer.parseInt(request.getParameter("id_utente"));
        String psw = request.getParameter("psw");
        String psw_confirm = request.getParameter("psw-confirm");

        if(psw.equals(psw_confirm)) {
            utenteService.updatePassword(psw, id_utente);
        }
        
    }
    
    public void eliminaUtente(HttpServletRequest request) {
        
        UtenteService utenteService = new UtenteService();
        
        utenteService.delete(Integer.parseInt(request.getParameter("id_utente")));
        
    }
    
    public void aggiungiNodo(HttpServletRequest request, String nome_mappa) {
        
        FormFilter formFilter = new FormFilter();
        NodoService nodoService = new NodoService();
        MappaService mappaService = new MappaService();
        Nodo nodo = new Nodo();
        
        String codice_nodo_filtered = formFilter.filtraCodice(request.getParameter("codice"));
        Double coord_x_filtered = formFilter.filtraCoordinata(request.getParameter("coord-x"));
        Double coord_y_filtered = formFilter.filtraCoordinata(request.getParameter("coord-y"));
        Double larghezza_filtered = formFilter.filtraMisura(request.getParameter("larghezza"));

        Integer exists = nodoService.findByCodice(codice_nodo_filtered).getID();

        if(!codice_nodo_filtered.equals(DEFAULT_STRING)
                && !Objects.equals(coord_x_filtered, DEFAULT_DOUBLE)
                && !Objects.equals(coord_y_filtered, DEFAULT_DOUBLE)
                && !Objects.equals(larghezza_filtered, DEFAULT_DOUBLE)
                && exists == null){

            Mappa mappa_local = mappaService.findByNome(nome_mappa);

            nodo.setCodice(codice_nodo_filtered);
            nodo.setCoord_X(coord_x_filtered);
            nodo.setCoord_Y(coord_y_filtered);
            nodo.setLarghezza(larghezza_filtered);
            nodo.setID_mappa(mappa_local.getID_mappa());
            nodo.setID_piano(mappa_local.getID_piano());

            nodoService.insertNodo(nodo);

        }

        exists = null;
        
    }
    
    public void modificaNodo(HttpServletRequest request) {
        
        FormFilter formFilter = new FormFilter();
        NodoService nodoService = new NodoService();
        Nodo nodo = new Nodo();
        
        String codice_nodo_filtered = formFilter.filtraCodice(request.getParameter("codice"));
        Double coord_x_filtered = formFilter.filtraCoordinata(request.getParameter("coord-x"));
        Double coord_y_filtered = formFilter.filtraCoordinata(request.getParameter("coord-y"));
        Double larghezza_filtered = formFilter.filtraMisura(request.getParameter("larghezza"));
        Integer id_nodo = Integer.parseInt(request.getParameter("id_nodo"));

        /* Visto che i campi da controllare sono più di uno ho bisogno
        di un oggetto che contenga i vecchi valori
        */
        Nodo nodo_old = nodoService.findByID(id_nodo);

        /* Controllo campi */
        Integer exists = nodoService.findByCodice(codice_nodo_filtered).getID();
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

        nodoService.updateNodo(nodo, id_nodo);

        exists = null;
        
    }
    
    public void eliminaNodo(HttpServletRequest request) {
        
        NodoService nodoService = new NodoService();
        
        nodoService.deleteNodo(Integer.parseInt(request.getParameter("id_nodo")));
        
    }
    
    public void aggiungiTronco(HttpServletRequest request, HttpServletResponse response,
            String nome_mappa) throws ServletException, IOException {
        
        FormFilter formFilter = new FormFilter();
        TroncoService troncoService = new TroncoService();
        MappaService mappaService = new MappaService();
        Tronco tronco = new Tronco();
        
        String id_n1_str = request.getParameter("codice-1");
        String id_n2_str = request.getParameter("codice-2");
        String id_beac_str = request.getParameter("codice-beacon");
        Double lunghezza = formFilter.filtraMisura(request.getParameter("lunghezza"));

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
        
        /* Controllo se esiste già un collegamento che abbia come estremi i nodi selezionati */
        Integer exists = troncoService.findByNodi(id_nodo_1, id_nodo_2).getID();
        if(exists == null) {
            exists = troncoService.findByNodi(id_nodo_2, id_nodo_1).getID();
        }
        
        if(exists == null && !Objects.equals(lunghezza, DEFAULT_DOUBLE)){

            Mappa mappa_local = mappaService.findByNome(nome_mappa);
            
            tronco.setNodiInteger(id_nodo_1, id_nodo_2);
            tronco.setID_beacon(id_beacon);
            tronco.setLunghezza(lunghezza);
            tronco.setID_mappa(mappa_local.getID_mappa());
            tronco.setID_piano(mappa_local.getID_piano());

            troncoService.insertTronco(tronco);
        }

        exists = null;
        
    }
    
    public void modificaTronco(HttpServletRequest request, HttpServletResponse response,
            String nome_mappa) throws ServletException, IOException {
        
        FormFilter formFilter = new FormFilter();
        TroncoService troncoService = new TroncoService();
        Tronco tronco = new Tronco();
        
        String id_n1_str = request.getParameter("codice-1");
        String id_n2_str = request.getParameter("codice-2");
        String id_beac_str = request.getParameter("codice-beacon");
        Double lunghezza = formFilter.filtraMisura(request.getParameter("lunghezza"));

        Integer id_tronco = Integer.parseInt(request.getParameter("id_tronco"));

        /* Visto che i campi da controllare sono più di uno ho bisogno
        di un oggetto che contenga i vecchi valori
        */
        Tronco tronco_old = troncoService.findArcByID(id_tronco);
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

        /* Controllo se esiste già un collegamento che abbia come estremi i nodi selezionati */
        Integer exists = troncoService.findByNodi(id_nodo_1, id_nodo_2).getID();
        if(exists == null) {
            exists = troncoService.findByNodi(id_nodo_2, id_nodo_1).getID();
        }
        
        /* Controllo campi */
        if(exists == null) {
            tronco.setNodiInteger(id_nodo_1, id_nodo_2);
        } else{
            tronco.setNodiInteger(nodi_old[0], nodi_old[1]);
        }
        
        if(!Objects.equals(lunghezza, DEFAULT_DOUBLE)) {
            tronco.setLunghezza(lunghezza);
        } else {
            tronco.setLunghezza(tronco_old.getLunghezza());
        }

        tronco.setID_beacon(id_beacon);

        troncoService.updateTronco(tronco, id_tronco);

        exists = null;
        
    }
    
    public void eliminaTronco(HttpServletRequest request) {
        
        TroncoService troncoService = new TroncoService();
        
        troncoService.delete(Integer.parseInt(request.getParameter("id_tronco")));
        
    }
    
    public void aggiungiPDI(HttpServletRequest request, String nome_mappa) {
        
        FormFilter formFilter = new FormFilter();
        NodoService nodoService = new NodoService();
        MappaService mappaService = new MappaService();
        Pdi pdi = new Pdi();

        String codice_pdi_filtered = formFilter.filtraCodice(request.getParameter("codice"));
        Double coord_x_filtered = formFilter.filtraCoordinata(request.getParameter("coord-x"));
        Double coord_y_filtered = formFilter.filtraCoordinata(request.getParameter("coord-y"));
        Double larghezza_filtered = formFilter.filtraMisura(request.getParameter("larghezza"));
        Double lunghezza_filtered = formFilter.filtraMisura(request.getParameter("lunghezza"));
        String tipo_filtered = formFilter.filtraTextArea(request.getParameter("tipo"));

        Integer exists = nodoService.findByCodice(codice_pdi_filtered).getID();

        if(!codice_pdi_filtered.equals(DEFAULT_STRING)
                && !Objects.equals(coord_x_filtered, DEFAULT_DOUBLE)
                && !Objects.equals(coord_y_filtered, DEFAULT_DOUBLE)
                && !Objects.equals(larghezza_filtered, DEFAULT_DOUBLE)
                && !Objects.equals(lunghezza_filtered, DEFAULT_DOUBLE)
                && !Objects.equals(tipo_filtered, DEFAULT_STRING)
                && exists == null){

            Mappa mappa_local = mappaService.findByNome(nome_mappa);

            pdi.setCodice(codice_pdi_filtered);
            pdi.setCoord_X(coord_x_filtered);
            pdi.setCoord_Y(coord_y_filtered);
            pdi.setLarghezza(larghezza_filtered);
            pdi.setLunghezza(lunghezza_filtered);
            pdi.setID_mappa(mappa_local.getID_mappa());
            pdi.setID_piano(mappa_local.getID_piano());
            pdi.setTipo(tipo_filtered);

            nodoService.insertPdi(pdi);

        }

        exists = null;
        
    }

    public void modificaPDI(HttpServletRequest request) {
        
        FormFilter formFilter = new FormFilter();
        NodoService nodoService = new NodoService();
        Pdi pdi = new Pdi();
        
        String codice_pdi_filtered = formFilter.filtraCodice(request.getParameter("codice"));
        Double coord_x_filtered = formFilter.filtraCoordinata(request.getParameter("coord-x"));
        Double coord_y_filtered = formFilter.filtraCoordinata(request.getParameter("coord-y"));
        Double larghezza_filtered = formFilter.filtraMisura(request.getParameter("larghezza"));
        Double lunghezza_filtered = formFilter.filtraMisura(request.getParameter("lunghezza"));
        String tipo_filtered = formFilter.filtraTextArea(request.getParameter("tipo"));
        Integer id_pdi = Integer.parseInt(request.getParameter("id_pdi"));

        /* Visto che i campi da controllare sono più di uno ho bisogno
        di un oggetto che contenga i vecchi valori
        */
        Pdi pdi_old = nodoService.findPdiByID(id_pdi);

        /* Controllo campi */
        Integer exists = nodoService.findByCodice(codice_pdi_filtered).getID();
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

        nodoService.updatePdi(pdi, id_pdi);

        exists = null;
        
    }
    
    public void eliminaPDI(HttpServletRequest request) {
        
        NodoService nodoService = new NodoService();
        
        nodoService.deleteNodo(Integer.parseInt(request.getParameter("id_pdi")));
        
    }
    
    public Integer aggiungiBeacon (HttpServletRequest request, String nome_mappa) {
        
        FormFilter formFilter = new FormFilter();
        MappaService mappaService = new MappaService();
        BeaconService beaconService = new BeaconService();
        Beacon beacon = new Beacon();

        String codice_beacon_filtered = formFilter.filtraCodice(request.getParameter("codice"));
        Double coord_x_filtered = formFilter.filtraCoordinata(request.getParameter("coord-x"));
        Double coord_y_filtered = formFilter.filtraCoordinata(request.getParameter("coord-y"));
        String id_pdi = request.getParameter("codice-pdi");

        Integer exists = beaconService.findByCodice(codice_beacon_filtered).getID_beacon();
        Mappa mappa_local = mappaService.findByNome(nome_mappa);

        if(!codice_beacon_filtered.equals(DEFAULT_STRING)
                && !Objects.equals(coord_x_filtered, DEFAULT_DOUBLE)
                && !Objects.equals(coord_y_filtered, DEFAULT_DOUBLE)
                && exists == null){

            beacon.setAddress(codice_beacon_filtered);
            beacon.setCoord_X(coord_x_filtered);
            beacon.setCoord_Y(coord_y_filtered);                        
            beacon.setID_mappa(mappa_local.getID_mappa());
            beacon.setID_piano(mappa_local.getID_piano());
            if(!id_pdi.equals("nessuno")) {
                beacon.setID_pdi(Integer.parseInt(id_pdi));
            }
            
            beaconService.insert(beacon);
            
        }

        exists = null;
        
        return mappa_local.getID_mappa();
        
    }
    
    public Integer modificaBeacon(HttpServletRequest request, String nome_mappa) {
        
        FormFilter formFilter = new FormFilter();
        MappaService mappaService = new MappaService();
        BeaconService beaconService = new BeaconService();
        Beacon beacon = new Beacon();
        
        String codice_beacon_filtered = formFilter.filtraCodice(request.getParameter("codice"));
        Double coord_x_filtered = formFilter.filtraCoordinata(request.getParameter("coord-x"));
        Double coord_y_filtered = formFilter.filtraCoordinata(request.getParameter("coord-y"));
        String id_pdi = request.getParameter("codice-pdi");
        Integer id_beacon = Integer.parseInt(request.getParameter("id_beacon"));

        /* Visto che i campi da controllare sono più di uno ho bisogno
        di un oggetto che contenga i vecchi valori
        */
        Beacon beacon_old = beaconService.findByID(id_beacon);

        Integer exists = beaconService.findByCodice(codice_beacon_filtered).getID_beacon();

        if(!codice_beacon_filtered.equals(DEFAULT_STRING) && exists == null) {
            beacon.setAddress(codice_beacon_filtered);
        } else{
            beacon.setAddress(beacon_old.getAddress());
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

        if(!id_pdi.equals("nessuno")) {
            beacon.setID_pdi(Integer.parseInt(id_pdi));
        } else {
            beacon.setID_pdi(null);
        }
        
        beaconService.update(beacon, id_beacon);

        Integer id_mappa_local = mappaService.findByNome(nome_mappa).getID_mappa();

        exists = null;
        
        return id_mappa_local;
    }
    
    
    public Integer eliminaBeacon(HttpServletRequest request, String nome_mappa) {
        
        MappaService mappaService = new MappaService();
        BeaconService beaconService = new BeaconService();
        
        beaconService.delete(Integer.parseInt(request.getParameter("id_beacon")));

        Integer id_mappa_local = mappaService.findByNome(nome_mappa).getID_mappa();
        
        return id_mappa_local;
        
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
