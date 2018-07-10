/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.servlets;

import it.breakout.resources.MappaResource;
import static it.breakout.utility.EnvVariables.URL_PIANO;
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

/**
 *
 * @author Giovanni
 */
@MultipartConfig(maxFileSize = 1024*1024*2)    // upload file's size up to 2MB
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
        
        /* Per inserire le immagini nella cartella "images" del progetto bisogna
        prendere il percorso e manipolarlo (testato solo su Winodws)
        */
        String buildPath = request.getServletContext().getRealPath(""); // D:\Documents\NetBeansProjects\Breakout_server\build\web
        String[] splitted = buildPath.split("build"); // {D:\Documents\NetBeansProjects\Breakout_server\, \web}
        String savePath = splitted[0] + "web\\images"; // D:\Documents\NetBeansProjects\Breakout_server\web\images
         
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("immagine");
        String fileName = fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        if(!fileName.equals("")) {

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
        }        
        
        /* Inserimento del nome dell'immagine nel database */
        Integer id_mappa = Integer.parseInt(request.getParameter("id_mappa"));
        MappaResource mappa_resource = new MappaResource();
        mappa_resource.insertImg(fileName, id_mappa);
        
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
