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
        <title>Breakout - Gestione Piano</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap-grid.css" type="text/css">
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap-grid.min.css" type="text/css">
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap-reboot.css" type="text/css">
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap-reboot.min.css" type="text/css">
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap.css" type="text/css">
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="static/fontawesome/fontawesome-all.css">
        <link rel="stylesheet" type="text/css" href="static/datatables.min.css"/>
        <script src="static/bootstrap-4.1.1-dist/js/bootstrap.bundle.js"></script>
        <script src="static/bootstrap-4.1.1-dist/js/bootstrap.bundle.min.js"></script>
        <script src="static/bootstrap-4.1.1-dist/js/bootstrap.js"></script>
        <script src="static/bootstrap-4.1.1-dist/js/bootstrap.min.js"></script>
        <script src="static/bootstrap-4.1.1-dist/js/bootbox.min.js"></script>
        <script src="static/scroll-table.js"></script>
        <script type="text/javascript" src="static/datatables.min.js"></script>
        <script type="text/javascript" src="static/modal-forms.js"></script>
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
                                <th>Percorso immagine</th>
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
        <div id="modal-aggiungi-mappa" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Aggiungi mappa</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="col-md-12">
                            <p class="warning">
                                Devono essere compilati tutti i campi, altrimenti l'inserimento
                                non verrà portato a termine.</p>
                        </div>
                        <div class="col-md-12">
                            <form action="DBModify" method="post">
                                <table class='table table-borderless'>
                                    <tr><td>Nome:</td>
                                    <td><input autofocus="true" type='text' name='nome-mappa'
                                                   size="40" placeholder='&nbsp;es. Q155/2'>&nbsp;
                                        <i data-toggle="tooltip" data-placement="top"
                                            title="Sono ammessi solamente caratteri alfanumerici, l'underscore, il trattino  e lo slash."
                                            class="fas fa-question-circle" style="color: #007bff;"></i>
                                    </td></tr>
                                    <tr><td>Immagine:</td>
                                        <td><input type="file" name="url-immagine"></td></tr>
                                </table>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                                    <input class="btn btn-outline-success" type='submit' 
                                           style="font-weight: bold" value='Aggiungi mappa' name='aggiungi-mappa'>
                                    <input type="hidden" name="azione" value="aggiungi-mappa">
                                    <input type="hidden" name="nm" value="${quota}">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Modal Form Aggiungi Mappa -->
        
        <!-- Modal Form Modifica Mappa -->
        <div id="modal-modifica-mappa" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Modifica mappa</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="col-md-12">
                            <form action="DBModify" method="post" id="form-mod-mappa">
                                <table class='table table-borderless'>
                                    <tr><td>Nome:</td>
                                    <td><input autofocus="true" type='text' name='nome-mappa'
                                               size="40" placeholder='&nbsp;(invariato)'>&nbsp;
                                        <i data-toggle="tooltip" data-placement="top"
                                            title="Sono ammessi solamente caratteri alfanumerici, l'underscore, il trattino  e lo slash."
                                            class="fas fa-question-circle" style="color: #007bff;"></i>
                                    </td></tr>
                                    <tr><td>Immagine:</td>
                                        <td><input type="file" name="url-immagine"></td></tr>
                                </table>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                                    <input class="btn btn-outline-success" type='submit' 
                                           style="font-weight: bold" value='Conferma modifiche' name='modifica-mappa'>
                                    <input type="hidden" name="id_mappa" value="">
                                    <input type="hidden" name="azione" value="modifica-mappa">
                                    <input type="hidden" name="nm" value="${quota}">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Modal Form Modifica Mappa -->
        
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
        <!-- End Modal Conferma Eliminazione Mappa -->
        
        <!-- Footer -->
        <%@include file="footer.html" %>
    </body>
</html>