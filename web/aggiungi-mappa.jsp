<%-- 
    Document   : aggiungi-mappa
    Created on : 22-mag-2018, 11.22.25
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it-IT">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Breakout - Aggiungi mappa</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap-grid.css" type="text/css">
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap-grid.min.css" type="text/css">
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap-reboot.css" type="text/css">
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap-reboot.min.css" type="text/css">
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap.css" type="text/css">
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="static/fontawesome/fontawesome-all.css">
        <script src="static/bootstrap-4.1.1-dist/js/bootstrap.bundle.js"></script>
        <script src="static/bootstrap-4.1.1-dist/js/bootstrap.bundle.min.js"></script>
        <script src="static/bootstrap-4.1.1-dist/js/bootstrap.js"></script>
        <script src="static/bootstrap-4.1.1-dist/js/bootstrap.min.js"></script>
        <script src="static/bootstrap-4.1.1-dist/js/bootbox.min.js"></script>
    </head>
    <body>
        <!-- Header -->
        <%@include file="header.html" %>
        
        <!-- Page Content -->
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <form>
                        <h2>Piano Q150 - Aggiungi Mappa</h2>
                        <br><br>
                        <div>
                            <h4>Carica immagine della mappa</h4>
                            <br>
                            <table class='table table-borderless'>
                                <tr><td>Seleziona il file <i>.jpg</i> da aggiungere</td></tr>
                                <tr><td>
                                <!--Bottone per caricare l'immagine della mappa-->
                                <div class="custom-file">
                                    <input type="file" class="custom-file-input" id="img-mappa">
                                    <label class="custom-file-label" for="img-mappa">Scegli file</label>
                                </div>
                                </td></tr>
                            </table>
                            
                            <!--Nome della mappa caricata che vedremo nella schermata -->
                            <div style= "margin-top: 50px; font-size: x-large" >
                                Nome mappa:<br>
                                <h3>Q150\1</h3>
                            </div>
                            <br><br><br>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <button type="button" class="btn btn-outline-dark">
                                    <b>Annulla</b>
                                </button>
                            </div>
                            <div class="col-md-8" style="text-align: right">
                                <input type="submit" class="btn btn-outline-success"
                                       style="font-weight: bold" value='Aggiungi mappa' name='aggiungi-mappa'>
                            </div>
                        </div>
                    </form>
                </div>
                
                <!-- Immagine della mappa fs:990x1572 -->
                <div class="col-md-6" style="text-align: center">
                       <img src="/Immagini/150_color.jpg" width="594" height="943">
                </div>
            </div>
        </div>
        
        <!-- Footer -->
        <%@include file="footer.html" %>
    </body>
</html>