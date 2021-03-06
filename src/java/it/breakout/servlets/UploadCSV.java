/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import it.breakout.services.BeaconService;
import it.breakout.services.MappaService;
import it.breakout.services.NodoService;
import it.breakout.models.Beacon;
import it.breakout.models.Mappa;
import it.breakout.utility.FormFilter;

import static it.breakout.utility.EnvVariables.URL_MAPPE;
import static it.breakout.utility.EnvVariables.DEFAULT_DOUBLE;
import static it.breakout.utility.EnvVariables.DEFAULT_STRING;



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
        
        String buildPath = request.getServletContext().getRealPath(""); // D:\Documents\NetBeansProjects\Breakout_server\build\web
        String[] splitted = buildPath.split("build"); // {D:\Documents\NetBeansProjects\Breakout_server\, \web}
        String savePath = splitted[0] + "web" + File.separator + "csv"; // D:\Documents\NetBeansProjects\Breakout_server\web\csv
        
        String flag = "false";
        String success = "false";
        Part filePart = request.getPart("csv");
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
                
                br = new BufferedReader(new FileReader(filePath));
                br.readLine(); // Salto la riga dell'header
                    
                BeaconService beaconService = new BeaconService();
                MappaService mappaService = new MappaService();
                NodoService nodoService = new NodoService();
                FormFilter formFilter = new FormFilter();

                // Lettura del csv riga per riga
                while ((line = br.readLine()) != null) {

                    /* I dati vengono splittati in base alla virgola e inseriti in un array
                        beaconData[0] : address
                        beaconData[1] : coord_x
                        beaconData[2] : coord_y
                        beaconData[3] : codice_pdi (null)
                        beaconData[4] : nome_mappa
                    */
                    String[] beaconData = line.split(cvsSplitBy);

                    Beacon beacon = new Beacon();

                    String codice_beacon_filtered = formFilter.filtraCodice(beaconData[0]);
                    Double coord_x_filtered = formFilter.filtraCoordinata(beaconData[1]);
                    Double coord_y_filtered = formFilter.filtraCoordinata(beaconData[2]);
                    String codice_pdi_filtered = formFilter.filtraCodice(beaconData[3]);
                    String nome_mappa_filtered = formFilter.filtraNomeMappa(beaconData[4]);

                    if(!codice_beacon_filtered.equals(DEFAULT_STRING)
                        && !Objects.equals(coord_x_filtered, DEFAULT_DOUBLE)
                        && !Objects.equals(coord_y_filtered, DEFAULT_DOUBLE)
                        && !Objects.equals(nome_mappa_filtered, DEFAULT_STRING)) {

                        beacon.setAddress(codice_beacon_filtered);
                        beacon.setCoord_X(coord_x_filtered);
                        beacon.setCoord_Y(coord_y_filtered);
                        Mappa mappa = mappaService.findByNome(nome_mappa_filtered);
                        beacon.setID_mappa(mappa.getID_mappa());
                        beacon.setID_piano(mappa.getID_piano());
                        if(!Objects.equals(codice_pdi_filtered, DEFAULT_STRING)) {
                            beacon.setID_pdi(nodoService.findByCodice(codice_pdi_filtered).getID());
                        }

                        beaconService.insert(beacon);
                        
                        success = "true";
                        
                    } else {
                        success = "false";
                        break;
                    }
                }

                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
                response.sendRedirect("500.jsp");
            } catch (IOException e) {
                System.out.println(e);
                response.sendRedirect("500.jsp");
            } finally {
                if (os != null) {
                    os.close();
                }
                if (filecontent != null) {
                    filecontent.close();
                }
            }
            
            // Terminato il caricamento, il file viene eliminato dal server
            new File(savePath + File.separator + fileName).delete();
        }
        
        flag = "true";
        request.setAttribute("success", success);
        request.setAttribute("flag", flag);
        
        // Torno alla pagina delle mappe
        rd = request.getRequestDispatcher(URL_MAPPE);
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
