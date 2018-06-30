<%-- 
    Document   : gestione-piano
    Created on : 24-mag-2018, 10.09.11
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it-IT">
    <head>
        <title>Breakout - Gestione Piano</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link href="static/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="static/css/datatables.min.css" rel="stylesheet" type="text/css"/>
        <link href="static/css/full-height.css" rel="stylesheet" type="text/css"/>
        <link href="static/css/fontawesome/fontawesome-all.css" rel="stylesheet" type="text/css"/>
        <script src="static/js/bootbox.min.js" type="text/javascript"></script>
        <script src="static/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="static/js/datatables.min.js" type="text/javascript"></script>
        <script src="static/js/jqBootstrapValidation.js" type="text/javascript"></script>
        <script src="static/js/modal-forms.js" type="text/javascript"></script>
        <script src="static/js/scroll-table.js" type="text/javascript"></script>
    </head>
    <body>
        <!-- Header -->
        <%@include file="header.jsp" %>

        <!-- Page Content -->
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <c:set var="quota" value="${requestScope.quota}" />
                    <h2>Piano Q${quota} - Mappe</h2>
                    <br><br>
                    <!-- Tabella delle mappe del piano -->
                    <h4>Lista Mappe</h4>
                    <table class="display" style="width:100%; text-align: center">
                        <thead>
                            <tr>
                                <th>Nome Mappa&nbsp;&nbsp;
                                    <i data-toggle="tooltip" data-placement="top"
                                       title="Clicca sul nome della mappa per aprire la pagina di gestione"
                                       class="fas fa-info-circle" style="color: #007bff;"></i>
                                <th>Nome immagine</th>
                                </th><th>Modifica</th><th>Elimina</th>
                            </tr>
                        </thead>
                        <c:forEach items="${requestScope.mappe}" var="mappa">
                            <c:set var="nome" value="${mappa.getNome()}"/>
                            <c:set var="percorso" value="${mappa.getUrlImmagine()}"/>
                            <c:set var="id_mappa" value="${mappa.getID_mappa()}"/>
                            <tr><td><a href="ObjectAccess?obj=mappa&nm=${nome}">${nome}</a></td>
                                <td>${percorso}</td>
                                <td><button id="mod-${id_mappa}" class="btn btn-outline-dark btn-sm"
                                        data-toggle="modal" data-target="#modal-modifica-mappa">
                                        <span class="fas fa-cog"></span></button></td>
                                <td><button id="del-${id_mappa}" class="btn btn-outline-danger btn-sm"
                                        data-toggle="modal" data-target="#modal-elimina-mappa">
                                        <span class="fas fa-trash-alt"></span></button></td></tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <div class="row" style=" margin-top: 30px">
                <div class="col-md-12" style="text-align: right">
                    <button type="button" class="btn btn-outline-success"
                            data-toggle="modal" data-target="#modal-aggiungi-mappa">
                        <b>Aggiungi mappa</b>
                    </button>
                </div>
            </div>    
             
            <div class="row">
                <div class="col-md-6">
                    <form action="DBAccess" method="POST">
                        <input type="submit" value="< Gestione Mappe" class="btn btn-secondary">
                        <input type="hidden" name="modalita" value="mappe">
                    </form>
                </div>
            </div> 
        </div>
                    
        <!-- Modal Form Aggiungi Mappa -->
        <%@include file="form/aggiungi-mappa.jsp"%>
        
        <!-- Modal Form Modifica Mappa -->
        <%@include file="form/modifica-mappa.jsp"%>
        
        <!-- Modal Conferma Eliminazione Mappa -->
        <div id="modal-elimina-mappa" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                    <h5 class="modal-title">Conferma eliminazione mappa</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    </div>
                    <div class="modal-body">
                        <form action='DBModify' method="post" id="form-del-mappa">
                            <p>Sicuro di voler rimuovere la mappa selezionata?
                                Questa azione non può essere annullata.</p>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                                <input class="btn btn-danger" type='submit' value='Elimina mappa' name='elimina-mappa'>
                                <input type="hidden" name="id_mappa" value="">
                                <input type="hidden" name="azione" value="elimina-mappa">
                                <input type="hidden" name="nm" value="${quota}">
                            </div>
                        </form>                    
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Footer -->
        <%@include file="footer.html" %>
    </body>
</html>