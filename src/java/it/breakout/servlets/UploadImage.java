/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.servlets;

import static com.sun.xml.ws.spi.db.BindingContextFactory.LOGGER;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import static it.breakout.utility.EnvVariables.DB_PSW;
import static it.breakout.utility.EnvVariables.DB_URL;
import static it.breakout.utility.EnvVariables.DB_USR;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 *
 * @author Giovanni
 */
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class UploadImage extends HttpServlet {
    
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
    
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
        // gets values of text fields
         
        InputStream inputStream = null; // input stream of the upload file
        String file_name = null;
        String savePath = request.getServletContext().getRealPath("") + "\\images";
         
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("immagine");
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }
        
        // Create path components to save the file
        final String fileName = extractFileName(filePart);

        OutputStream out = null;
        InputStream filecontent = null;
        final PrintWriter writer = response.getWriter();

        try {
            out = new FileOutputStream(new File(savePath + File.separator + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } catch (FileNotFoundException fne) {
            writer.println("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.");
            writer.println("ERROR: " + fne.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
            if (writer != null) {
                writer.close();
            }
        }

        
        /**********************************************************************
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List <FileItem> fields = upload.parseRequest(request);
            Iterator <FileItem> it = fields.iterator();
            if (!it.hasNext()) {
                return;
            }
            while (it.hasNext()) {
                FileItem fileItem = it.next();
                if (fileItem.getSize() > 0) {
                fileItem.write(new File(IMG_PATH + fileItem.getName()));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        **********************************************************************/
         
//        Connection conn = null; // connection to the database
//        String message = null;  // message will be sent back to client
//         
//        try {
//            // connects to the database
//            conn = DriverManager.getConnection(DB_URL, DB_USR, DB_PSW);
// 
//            // constructs SQL statement
//            String sql = "INSERT INTO immagine values (?)";
//            PreparedStatement statement = conn.prepareStatement(sql);
//             
//            if (inputStream != null) {
//                // fetches input stream of the upload file for the blob column
//                statement.setBlob(1, inputStream);
//            }
// 
//            // sends the statement to the database server
//            int row = statement.executeUpdate();
//            if (row > 0) {
//                message = "File uploaded and saved into database";
//                System.out.println(message);
//            }
// 
//            System.out.println("File Saved Successfully");
//        } catch (SQLException ex) {
//            message = "ERROR: " + ex.getMessage();
//            System.out.println(message);
//        } finally {
//            if (conn != null) {
//                // closes the database connection
//                try {
//                    conn.close();
//                } catch (SQLException ex) {
//                    System.out.println(ex.getMessage());
//                }
//            }
             
            // forwards to the message page
            //getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
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
