<%-- 
    Document   : gestione-mappe
    Created on : 24-mag-2018, 10.09.11
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it-IT">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Breakout - Gestione Piano</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap-grid.css" type="text/css">
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap-grid.min.css" type="text/css">
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap-reboot.css" type="text/css">
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap-reboot.min.css" type="text/css">
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap.css" type="text/css">
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap.min.css" type="text/css">
        <link href="static/fontawesome/fontawesome-all.css" rel="stylesheet">
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
                    <h2>Piano Q150 - Mappe</h2>
                    <br><br>
                    <!-- Tabella delle mappe del piano -->
                    <h4>Lista Mappe</h4>
                    <table id="tabella" class="table table-bordered table-striped" style="text-align: center">
                        <thead>
                            <tr>
                                <th>Nome Mappa&nbsp;&nbsp;
                                    <i data-toggle="tooltip" data-placement="top"
                                       title="Clicca sul nome della mappa per aprire la pagina di gestione"
                                       class="fas fa-info-circle" style="color: #007bff;"></i>
                                </th><th>Elimina</th>
                            </tr>
                        </thead>
                        <tr><td><a href="gestione-mappa.jsp" target="_blank">Q150/1</a></td>
                            <td><button id="rm-q150-1" class="btn btn-outline-danger btn-sm"
                                        data-toggle="modal" data-target="#modal-elimina-mappa">
                                <span class="fas fa-trash-alt"></span></button></td></tr>
                        <tr><td><a href="gestione-mappa.jsp" target="_blank">Q150/2</a></td>
                            <td><button id="rm-q150-2" class="btn btn-outline-danger btn-sm"
                                        data-toggle="modal" data-target="#modal-elimina-mappa">
                                <span class="fas fa-trash-alt"></span></button></td></tr>
                        <tr><td><a href="gestione-mappa.jsp" target="_blank">Q145/1</a></td>
                            <td><button id="rm-q145-1" class="btn btn-outline-danger btn-sm"
                                        data-toggle="modal" data-target="#modal-elimina-mappa">
                                <span class="fas fa-trash-alt"></span></button></td></tr>
                    </table>
                    <div style="text-align: right">
                        <button type="button" class="btn btn-outline-success">
                            <b>Aggiungi mappa</b>
                        </button>
                    </div>
                </div>
                
                <!--Immagine dell mappa fs:990x1572 -->
                <div class="col-md-6" >
                    <img src="/Immagini/150_color.jpg" width="594" height="943">
                </div>
            </div>
                <button type="button" class="btn btn-dark">
                        < Indietro
                </button>
        </div>
        
        <!-- codice per la definizione della tabella DataTable
        e per la selezioene delle righe della tabella-->
        <script type="text/javascript">
             $(document).ready(function() {
                $('#Tabella').DataTable({
                    "searching": false,
                    "bInfo":false,
                     dom: 'frti',
                     select: {
                     style: 'single'
                       }
            } );
           });
        </script>
        
        
        <!-- Footer -->
        <%@include file="footer.html" %>
    </body>
</html>