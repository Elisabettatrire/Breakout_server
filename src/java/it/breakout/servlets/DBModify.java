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
import it.breakout.utility.FormFilter;
import it.breakout.models.Mappa;

/**
 *
 * @author Giovanni
 */
public class DBModify extends HttpServlet {

    private String azione;
    
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
        FormFilter form_filter = new FormFilter();
        Integer check;  // Serve per vedere se le query restituiscono un valore nullo
        int id_mappa;
        int id_piano;
        
        /* Variabili per inserimento-modifica-eliminazione piano */
        Piano_Resource piano_resource = new Piano_Resource();
        String quota;
        String quota_filtered;
        
        /* Variabili per inserimento-modifica-eliminazione scala */
        
        /* Variabili per inserimento-modifica-eliminazione mappa */
        Mappa_Resource mappa_resource = new Mappa_Resource();
        Mappa mappa = new Mappa();
        String nome_mappa;
        String nome_mappa_filtered;
        String url_immagine;
        
        try {
            switch(azione) {

                /* Azioni relative alla tabella dei piani */
                case "aggiungi-piano":

                    quota = request.getParameter("quota");
                    quota_filtered = form_filter.filtraQuota(quota);
                    /* Controllo se esiste una mappa con lo stesso nome */
                    check = piano_resource.findByQuota(quota_filtered).getID_piano();
                    
                    if(!quota_filtered.equals("empty") && check==null) {
                        piano_resource.insert(quota_filtered);
                    }

                    /* Ritorno alla lista dei piani che sarà aggiornata */
                    rd = request.getRequestDispatcher("/DBAccess");
                    rd.forward(request, response);

                    break;

                case "modifica-piano":

                    id_piano = Integer.parseInt(request.getParameter("id_piano"));
                    quota = request.getParameter("quota");
                    /* Se il campo viene lasciato vuoto non si deve fare niente */
                    if(!quota.equals("")) {
                        
                        quota_filtered = form_filter.filtraQuota(quota);
                        check = piano_resource.findByQuota(quota_filtered).getID_piano();
                        
                        if(!quota_filtered.equals("empty") && check==null) {
                            piano_resource.update(quota_filtered, id_piano);
                        }
                    }

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
                    check = mappa_resource.findByNome(nome_mappa_filtered).getID_mappa();
                    
                    if(!nome_mappa_filtered.equals("empty") && check==null) {
                        
                        mappa.setNome(nome_mappa_filtered);
                        mappa.setID_piano(piano_resource.findByQuota(quota).getID_piano()); // veloce
                        mappa.setUrlImmagine(url_immagine);
                        mappa_resource.insert(mappa);
                    }

                    rd = request.getRequestDispatcher("ObjectAccess?obj=piano&nm="+quota);
                    rd.forward(request, response);

                    break;
                    
                case "modifica-mappa":
                                        
                    nome_mappa = request.getParameter("nome-mappa");
                    quota = request.getParameter("nm"); // non c'è bisogno di filtrarla
                    url_immagine = request.getParameter("url-immagine"); // non c'è bisogno di filtrarlo
                    id_mappa = Integer.parseInt(request.getParameter("id_mappa"));
                    mappa.setID_mappa(id_mappa);
                                        
                    /* Aggiornamento nome mappa */
                    // Se il campo è vuoto bisogna mantenere il valore precedente
                    if(nome_mappa.equals("")) {
                        
                        nome_mappa = mappa_resource.findByID(id_mappa).getNome();
                        mappa.setNome(nome_mappa);
                        
                    } else {
                        
                        nome_mappa_filtered = form_filter.filtraNomeMappa(nome_mappa);
                        // Se una volta filtrato non è vuoto...
                        check = mappa_resource.findByNome(nome_mappa_filtered).getID_mappa();
                        
                        if(!nome_mappa_filtered.equals("empty") && check==null) {
                            mappa.setNome(nome_mappa_filtered);
                        }
                    }
                    
                    /* Aggiornamento url */
                    // Se il campo è vuoto bisogna mantenere il valore precedente
                    if(url_immagine.equals("")) {
                        url_immagine = mappa_resource.findByID(id_mappa).getUrlImmagine();
                    }
                    mappa.setUrlImmagine(url_immagine);
                    
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
