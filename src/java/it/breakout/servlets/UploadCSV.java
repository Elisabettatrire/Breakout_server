/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.servlets;

import static it.breakout.utility.EnvVariables.URL_HOME;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Giovanni
 */
@WebServlet(
    name = "UploadCSV",
    urlPatterns = { "/UploadCSV"},
    loadOnStartup = 1
)
@MultipartConfig
public class UploadCSV extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
        RequestDispatcher rd;
        
        ServletContext servletContext = this.getServletConfig().getServletContext();
        String buildPath = request.getServletContext().getRealPath(""); // D:\Documents\NetBeansProjects\Breakout_server\build\web
        String[] splitted = buildPath.split("build"); // {D:\Documents\NetBeansProjects\Breakout_server\, \web}
        String savePath = splitted[0] + "web" + File.separator + "csv"; // D:\Documents\NetBeansProjects\Breakout_server\web\csv
        
        
        Part filePart = request.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        if(!fileName.equals("") && fileName.endsWith(".csv")) {

            OutputStream os = null;
            InputStream filecontent = null;
            String filePath = savePath + File.separator + fileName;

            try {
                os = new FileOutputStream(new File(filePath));
                filecontent = filePart.getInputStream();

                int read = 0;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    os.write(bytes, 0, read);
                } // Caricamento nel server completato
                
                /* Lettura del file */
                BufferedReader br = null;
                String line = "";
                String cvsSplitBy = ",";
                PrintWriter out = response.getWriter();
                br = new BufferedReader(new FileReader(filePath));
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet UploadCSV</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet UploadCSV at " + request.getContextPath() + "</h1>");
                while ((line = br.readLine()) != null) {

                    // use comma as separator
                    String[] country = line.split(cvsSplitBy);

                    out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");

                }
                out.println("</body>");
                out.println("</html>");
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
                response.sendRedirect("500.jsp");
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                if (os != null) {
                    os.close();
                }
                if (filecontent != null) {
                    filecontent.close();
                }
            }
        }
        
        rd = request.getRequestDispatcher(URL_HOME);
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
