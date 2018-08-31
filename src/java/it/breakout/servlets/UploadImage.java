/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.RequestDispatcher;

import it.breakout.services.MappaService;

import static it.breakout.utility.EnvVariables.URL_PIANO;

/**
 *
 * @author Giovanni
 */
@MultipartConfig
public class UploadImage extends HttpServlet {
    
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
        
        RequestDispatcher rd;
        String quota = request.getParameter("nm");
        String old_img = request.getParameter("old-img");
        
        /* Per inserire le immagini nella cartella "images" del progetto bisogna
        prendere il percorso e manipolarlo (testato su Windows e su MacOS)
        */
        String buildPath = request.getServletContext().getRealPath(""); // D:\Documents\NetBeansProjects\Breakout_server\build\web
        String[] splitted = buildPath.split("build"); // {D:\Documents\NetBeansProjects\Breakout_server\, \web}
        String savePath = splitted[0] + "web" + File.separator + "images"; // D:\Documents\NetBeansProjects\Breakout_server\web\images
        
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("immagine");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        if((filePart.getSize() <= (1024*1024*2))    // Dimensione massima 2MB
                && !fileName.equals("")             // Nome file non vuoto...
                && fileName.length() < 50 ) {       // ...e lungo massimo 50 caratteri

            OutputStream out = null;
            InputStream filecontent = null;
            try {
                out = new FileOutputStream(new File(savePath + File.separator + fileName));
                filecontent = filePart.getInputStream();

                int read = 0;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                } // Caricamento nel server completato            

            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
                response.sendRedirect("500.jsp");

            } finally {
                if (out != null) {
                    out.close();
                }
                if (filecontent != null) {
                    filecontent.close();
                }
            }
            
            /* Inserimento del nome dell'immagine nel database */
            Integer id_mappa = Integer.parseInt(request.getParameter("id_mappa"));
            MappaService mappaService = new MappaService();
            mappaService.insertImg(fileName, id_mappa);
            
            /* Se è già presente un'immagine, la cancello in modo da liberare lo spazio
            sul server
            */
            if(!fileName.equals(old_img)) {
                new File(savePath + File.separator + old_img).delete();
            }

        }
        
        /* Reindirizzamento */
        rd = request.getRequestDispatcher(URL_PIANO+quota);
        rd.forward(request, response);
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
