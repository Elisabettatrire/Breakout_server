<%-- 
    Document   : gestione-utenti
    Created on : 1-giu-2018, 17.15.32
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it-IT">
    <head>
        <title>Breakout - Gestione Utenti</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link href="static/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css"/>
        <link href="static/css/full-height.css" rel="stylesheet" type="text/css"/>
        <link href="static/css/fontawesome/fontawesome-all.css" rel="stylesheet" type="text/css"/>
        <script src="static/js/bootbox.min.js" type="text/javascript"></script>
        <script src="static/js/bootstrap.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.js"></script>
        <script src="static/js/modal-forms.js" type="text/javascript"></script>
        <script src="static/js/scroll-order-table.js" type="text/javascript"></script>
    </head>
    <body>
        <!-- Header -->
        <%@include file="header.jsp" %>

        <!-- Page Content -->
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Gestione Utenti App</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6" style="margin-top: 20px">
                    <button type="button" class="btn btn-secondary">
                        <a href="home.jsp" style="color: inherit; text-decoration: none">
                        < Home
                        </a>
                    </button>
                </div>
            </div><br>
            <div class="row">
                <div class="col-md-12">
                    <!-- Tabella dei Nodi della mappa -->
                    <h4>Lista Utenti</h4>
                    <table class="display" style="width:100%; text-align: center">
                        <thead>
                            <tr><th>Username</th><th class="no-sort">Password</th>
                                <th>Nome</th><th>Cognome</th>
                                <th class="no-sort">e-mail</th>
                                <th class="no-sort">Modifica Password</th>
                                <th class="no-sort">Elimina</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.utenti}" var="utente">
                                <c:set var="id_utente" value="${utente.getID_utente()}"/>
                                <c:set var="username" value="${utente.getUsername()}"/>
                                <c:set var="password" value="${utente.getPassword()}"/>
                                <c:set var="nome" value="${utente.getNome()}"/>
                                <c:set var="cognome" value="${utente.getCognome()}"/>
                                <c:set var="email" value="${utente.getEmail()}"/>
                                <tr><td>${username}</td><td>${password}</td><td>${nome}</td>
                                    <td>${cognome}</td><td>${email}</td>
                                <td><button id="mod-${id_utente}" class="btn btn-outline-dark btn-sm"
                                    data-toggle="modal" data-target="#modal-mod-utente">
                                    <span class="fas fa-cog"></span></button></td>
                                <td><button id="del-${id_utente}" class="btn btn-outline-danger btn-sm"
                                        data-toggle="modal" data-target="#modal-elimina-utente">
                                <span class="fas fa-trash-alt"></span></button></td></tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        
        <!-- Modal Form Modifica Utente -->
        <%@include file="form/modifica-utente.jsp"%>
        
        <!-- Modal Conferma Eliminazione Utente -->
        <div id="modal-elimina-utente" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                    <h5 class="modal-title">Conferma eliminazione utente</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    </div>
                    <div class="modal-body">
                        <form action='DBModify' method="post" id="form-del-utente">
                            <p>Sicuro di voler rimuovere l'utente selezionato?
                                Questa azione non pu?? essere annullata.</p>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                    Annulla</button>
                                <input class="btn btn-danger" type='submit' value='Elimina utente'
                                       name='elimina-utente'>
                                <input type="hidden" name="id_utente" value="">
                                <input type="hidden" name="azione" value="elimina-utente">
                                <input type="hidden" name="modalita" value="utenti">
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