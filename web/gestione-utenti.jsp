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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Breakout - Gestione Utenti</title>
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
        <%@include file="header.jsp" %>

        <!-- Page Content -->
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Gestione Utenti App</h2>
                    <br><br>
                    <!-- Tabella dei Nodi della mappa -->
                    <h4>Lista Utenti</h4>
                    <table class="table table-bordered table-striped" style="text-align: center">
                        <thead>
                            <tr><th>ID</th><th>Nome</th><th>Cognome</th><th>Username</th>
                                <th>Password</th><th>e-mail</th><th>Modifica</th><th>Elimina</th>
                        </thead>
                        <tr><td>1</td><td>Mario</td><td>Rossi</td><td>mario72</td><td>mariolino</td>
                            <td>mario.rossi@email.it</td>
                            <td><button id="mod-150g2" class="btn btn-outline-dark btn-sm"
                                    data-toggle="modal" data-target="#modal-mod-utente">
                                    <span class="fas fa-cog"></span></button></td>
                            <td><button id="rm-150g2" class="btn btn-outline-danger btn-sm"
                                    data-toggle="modal" data-target="#modal-elimina-utente">
                            <span class="fas fa-trash-alt"></span></button></td></tr>
                    </table>
                </div>
            </div>
            
            <div class="row">
                <div class="col-md-6">
                    <button type="button" class="btn btn-secondary">
                        <a href="home.jsp" style="color: inherit; text-decoration: none">
                        < Home
                        </a>
                    </button>
                </div>
            </div>
            
        </div>
        
        <!-- Finestra popup per la modifica di un utente-->
        <div id="modal-mod-utente" class="modal fade">
            <div class="modal-dialog " >
                <div class="modal-content ">
                    <div class="modal-header" >
                        <h5 class="modal-title">Modifica informazioni utente</h5>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <!-- text area per modificare i dati dei nodi caricati -->
                    <div class="modal-body">
                        <form>
                            <table class="table table-borderless">
                                <tr><td>Coord_X</td><td><input type="text" name="coord-x" size="40"></td></tr>
                                <tr><td>Coord_Y</td><td><input type="text" name="coord-y" size="40"></td></tr>
                                <tr><td>Larghezza</td><td><input type="text" name="larghezza"size="40"></td></tr>
                                <tr><td>Codice</td><td><input type="text" name="codice" size="40"></td></tr>
                            </table>
                            <!-- Bottoni per tornare alla schermata precedente o per aggiornare le modifiche-->
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                    Annulla</button>
                                <input class="btn btn-outline-success" type='submit' 
                                    style="font-weight: bold" value='Conferma modifiche' name='conferma-modifiche'>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
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
                        <form action='#'>
                            <p>Sicuro di voler rimuovere l'utente selezionato?
                                Questa azione non può essere annullata.</p>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                                <input class="btn btn-danger" type='submit' value='Elimina' name='elimina-nodi'>
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