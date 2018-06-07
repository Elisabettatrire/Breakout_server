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
        <link rel="stylesheet" href="static/fontawesome/fontawesome-all.css">
        <script src="static/bootstrap-4.1.1-dist/js/bootstrap.bundle.js"></script>
        <script src="static/bootstrap-4.1.1-dist/js/bootstrap.bundle.min.js"></script>
        <script src="static/bootstrap-4.1.1-dist/js/bootstrap.js"></script>
        <script src="static/bootstrap-4.1.1-dist/js/bootstrap.min.js"></script>
        <script src="static/bootstrap-4.1.1-dist/js/bootbox.min.js"></script>
    </head>
    <body>
        <!-- Header -->
        <%@include file="header.jsp" %>

        <!-- Page Content -->
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Piano Q${requestScope.quota} - Mappe</h2>
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
                                </th><th>Modifica</th><th>Elimina</th>
                            </tr>
                        </thead>
                        <c:forEach items="${requestScope.mappe}" var="mappa">
                            <c:set var="nome" value="${mappa.getNome()}"/>
                            <tr><td><a href="SingleObject?obj=mappa&nm=${nome}">${nome}</a></td>
                                <td><button id="mod-${nome}" class="btn btn-outline-dark btn-sm"
                                        data-toggle="modal" data-target="#modal-mod-mappa">
                                        <span class="fas fa-cog"></span></button></td>
                                <td><button id="rm-${nome}" class="btn btn-outline-danger btn-sm"
                                        data-toggle="modal" data-target="#modal-elimina-mappa">
                                        <span class="fas fa-trash-alt"></span></button></td></tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <form action="DBAccess" method="POST">
                        <input type="submit" value="< Indietro" class="btn btn-secondary">
                        <input type="hidden" name="modalita" value="mappe">
                    </form>
                </div>
                <div class="col-md-6" style="text-align: right">
                    <button type="button" class="btn btn-outline-success">
                        <b>Aggiungi mappa</b>
                    </button>
                </div>
            </div>    
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