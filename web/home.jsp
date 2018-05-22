<%-- 
    Document   : newjsp
    Created on : 15-mag-2018, 14.31.24
    Author     : Giovanni
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@include file="header.html" %>

<!DOCTYPE html>
<html lang="it-IT">
    <head>
        <title>Breakout - Home</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
        <!-- Page Content -->        
        <div class="container">
            <div class="row">
                <!-- Table Piani -->
                <div class="col-md-6">
                    <h4>Lista Piani</h4>
                    <table class="table table-bordered table-striped" style="text-align: center">
                        <thead>
                            <tr><th>Quota</th><th>Modifica</th><th>Elimina</th>
                        </thead>
                        <!-- Get data from request object -->
                        <c:forEach items="${requestScope.quote}" var="quota">

                            <tr><td>${quota}</td>
                                <td><button id="mod-q${quota}" class="btn btn-outline-dark btn-sm">
                                        <span class="fas fa-cog"></span></button></td>
                                <td><button id="rm-q${quota}" class="btn btn-outline-danger btn-sm"
                                            data-toggle="modal" data-target="#modal-elimina-piano">
                                        <span class="fas fa-trash-alt"></span></button></td></tr>
                        </c:forEach>
                        <!-- -->
                    </table>
                            
                    <div style="text-align: right">
                        <button id="aggiungiPiano" data-toggle="modal" data-target="#modal-piano"
                                class="btn btn-outline-success"><b>Aggiungi Piano</b></button>
                    </div>
                </div>
                <!-- End Table Piani -->
                
                <!-- Table Scale -->
                <div class="col-md-6">
                    <h4>Lista Scale</h4>
                    <table class="table table-bordered table-striped" style="text-align: center">
                        <thead>
                            <tr><th>Lunghezza</th><th>Codice Nodo 1</th><th>Codice Nodo 2</th><th>Codice</th>
                        </thead>
                        <tr><td>1</td><td></td><td></td><td></td></tr>
                        <tr><td>2</td><td></td><td></td><td></td></tr>
                        <tr><td>3</td><td></td><td></td><td></td></tr>
                        <tr><td>4</td><td></td><td></td><td></td></tr>
                    </table>
                    <div style="text-align: right">
                        <button id="aggiungiScala" data-toggle="modal" data-target="#modal-scala"
                                class="btn btn-outline-success"><b>Aggiungi Scala</b></button>
                    </div>
                </div>
                <!-- End Table Scale -->
                
            </div>
            <!-- End Row -->
            <div class="row"  style="margin-top: 60px; margin-bottom: 40px">
                <div class="col-lg-12" style="text-align: right">
                    <button class="btn btn-outline-dark"><b>Logout</b></button>
                </div>
            </div>
        </div>
        <!-- End Page Content -->
        
        <!-- Modal Conferma Eliminazione Mappa -->
        <div id="modal-elimina-piano" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                    <h5 class="modal-title">Conferma eliminazione piano</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    </div>
                    <div class="modal-body">
                        <form action='#'>
                            <p>Sicuro di voler rimuovere il piano selezionato?
                                Questa azione non può essere annullata.</p>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                                <input class="btn btn-danger" type='submit' value='Elimina' name='Elimina'>
                            </div>
                        </form>                    
                    </div>
                </div>
            </div>
        </div>
        <!-- End Modal Conferma Eliminazione Mappa -->
        
        <!-- Modal Form Aggiungi Piano -->
        <div id="modal-piano" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Aggiungi piano</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action='MappaServlet'>
                            <table class='table table-borderless'><tr><td>Quota:</td>
                            <td><input type='text' name='quota' size='45' placeholder='&nbsp;es. 155'>
                            </td></table>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                                <input class="btn btn-outline-success" type='submit' 
                                       style="font-weight: bold" value='Aggiungi piano' name='Aggiungi piano'>
                            </div>
                        </form>                    
                    </div>
                </div>
            </div>
        </div>
        <!-- End Modal Form Aggiungi Piano -->
        
        <!-- Modal Form Aggiungi Scala -->
        <div id="modal-scala" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                    <h5 class="modal-title">Aggiungi scala</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    </div>
                    <div class="modal-body">
                        <form action='#'>
                            <table class='table table-borderless'>
                                <tr><td>Lunghezza (m):</td>
                                    <td><input type='text' name='lunghezza' size='38'
                                        placeholder='&nbsp;es. 25'></td>
                                <tr><td>Cod. Nodo 1:</td><td><input type='text'
                                        placeholder='&nbsp;es. 145UA5' name='nodo_1' size='38'></td>
                                <tr><td>Cod. Nodo 2:</td><td><input type='text'
                                        placeholder='&nbsp;es. 145DICEA' name='nodo_1' size='38'></td>
                            </table>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                                <input class="btn btn-outline-success" type='submit'
                                       style="font-weight: bold" value='Conferma' name='Conferma'>
                            </div>
                        </form>                    
                    </div>
                </div>
            </div>
        </div>
        <!-- End Modal Form Aggiungi Scala -->
        
    </body>
</html>

<%@include file="footer.html" %>
