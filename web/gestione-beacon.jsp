<%--
    Document   : gestione-beacon
    Created on : 22-mag-2018, 14.54.41
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html lang="it-IT">
    <head>
        <title>Breakout - Gestione Beacon</title>
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
        <link rel="stylesheet" href="static/full-height.css" type="text/css">
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
                <div class="col-md-8">
                    <c:set var="nome_mappa" value="${requestScope.nome_mappa}"/>
                    <h2>Mappa ${nome_mappa} - Gestione Beacon</h2>
                    <br><br>
                    <h4>Lista Beacon</h4>
                    <!--Tabella dei beacon della mappa-->
                    <table class="table-striped display" style="text-align: center; width:100%;">
                        <thead>
                            <tr><th>Nome</th><th>Coord. X</th><th>Coord. Y</th><th>Modifica</th>
                                <th>Elimina</th></tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.al_beacon}" var="beacon">
                                <c:set var="id" value="${beacon.getID_beacon()}"/>
                                <c:set var="x" value="${beacon.getCoord_X()}"/>
                                <c:set var="y" value="${beacon.getCoord_Y()}"/>
                                <tr><td>${id}</td><td>${x}</td><td>${y}</td>
                                <td><button id="mod-${id}" class="btn btn-outline-dark btn-sm"
                                            data-toggle="modal" data-target="#modal-mod-beacon">
                                        <span class="fas fa-cog"></span></button></td>
                                <td><button id="del-${id}" class="btn btn-outline-danger btn-sm"
                                            data-toggle="modal" data-target="#modal-elimina-beacon">
                                        <span class="fas fa-trash-alt"></span></button></td></tr></tr>
                            </c:forEach>
                            <tr><td>Beacon B1</td><td>x1</td><td>y1</td>
                                <td><button class="btn btn-outline-dark btn-sm"
                                            data-toggle="modal" data-target="#modal-mod-beacon">
                                        <span class="fas fa-cog"></span></button></td>
                                <td><button class="btn btn-outline-danger btn-sm"
                                            data-toggle="modal" data-target="#modal-elimina-beacon">
                                        <span class="fas fa-trash-alt"></span></button></td></tr>
                            <tr><td>Beacon B2</td><td>x2</td><td>y2</td>
                                <td><button class="btn btn-outline-dark btn-sm"
                                            data-toggle="modal" data-target="#modal-mod-beacon">
                                        <span class="fas fa-cog"></span></button></td>
                                <td><button class="btn btn-outline-danger btn-sm"
                                            data-toggle="modal" data-target="#modal-elimina-beacon">
                                        <span class="fas fa-trash-alt"></span></button></td></tr>
                            <tr><td>Beacon B3</td><td>x3</td><td>y3</td>
                                <td><button class="btn btn-outline-dark btn-sm"
                                            data-toggle="modal" data-target="#modal-mod-beacon">
                                        <span class="fas fa-cog"></span></button></td>
                                <td><button class="btn btn-outline-danger btn-sm"
                                            data-toggle="modal" data-target="#modal-elimina-beacon">
                                        <span class="fas fa-trash-alt"></span></button></td></tr>
                        </tbody>
                    </table>
                    <br>
                    <!--Bottoni per la gestione dei beacon-->
                    <div style="text-align: right; margin-top: 10px">
                        <button type="button" class="btn btn-outline-success" data-toggle="modal"
                                data-target="#modal-beacon">
                            <b>Aggiungi beacon</b>
                        </button>
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <form action="ObjectAccess" method="POST">
                                <input type="submit" value="< Gestione Mappa" class="btn btn-secondary">
                                <input type="hidden" name="obj" value="mappa">
                                <input type="hidden" name="nm" value="${nome_mappa}">
                            </form>
                        </div>
                        <div class="col-md-3">
                            <form action="ObjectAccess" method="POST">
                                <input type="submit" value="Gestione Piano" class="btn btn-secondary">
                                <input type="hidden" name="obj" value="piano">
                                <input type="hidden" name="nm" value="${requestScope.quota}">
                            </form>
                        </div>
                    </div>
                </div>
                
                <!-- Immagine dell mappa fs:990x1572 -->
                <c:set var = "nome_img" value = "${fn:replace(requestScope.nome, '/', '-')}" />
                <div class="col-md-4" >  
                    <img src="/Immagini/${nome_img}.jpg" width="396" height="630">
                </div> 
            </div>
            
            <!-- Modal Form Aggiungi Beacon -->
            <div id="modal-beacon" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Aggiungi Beacon</h5>
                            <button type="button" class="close" data-dismiss="modal">&times;</button> 
                        </div>
                        <div class="modal-body">
                            <div class="col-md-12">
                                <p class="warning">
                                    Devono essere compilati tutti i campi, altrimenti l'inserimento
                                    non verrà portato a termine.</p>
                            </div>
                            <!-- text area per inserire i dati dei nodi da caricare -->
                            <div class="col-md-12">
                                <form action="#" method="post">
                                    <table class="table table-borderless">
                                        <tr><td>Codice:</td><td>
                                                <input type="text" name="codice" placeholder=" es. 150G2"
                                                       size="30" autofocus="true"></td></tr>
                                        <tr><td>Coord. X:</td><td>
                                                <input type="text" name="coord-x"
                                                        placeholder=" es. 129" size="30"></td></tr>
                                        <tr><td>Coord. Y:</td><td>
                                                <input type="text" name="coord-y"
                                                        placeholder=" es. 465" size="30"></td></tr>
                                        <tr><td>Indici vari...</td><td>
                                                <input type="text" name="" placeholder=""
                                                        size="30"></td></tr>
                                    </table>
                                    <!-- Bottoni per tornare alla schermata precedente o per aggiungere il nodo -->
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                            Annulla</button>
                                        <input class="btn btn-outline-success" type='submit' 
                                            style="font-weight: bold" value='Aggiungi beacon'>
                                        <input type="hidden" name="azione" value="aggiungi-beacon">
                                        <input type="hidden" name="nm" value="${nome_mappa}">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
                                
            <!-- Finestra popup per la modifica di un beacon-->
            <div id="modal-mod-beacon" class="modal fade">
                <div class="modal-dialog " >
                    <div class="modal-content ">
                        <div class="modal-header" >
                            <h5 class="modal-title">Modifica beacon</h5>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <!-- text area per modificare i dati dei nodi caricati -->
                        <div class="modal-body">
                            <div class="col-md-12">
                                <form action="DBModify" method="post" id="form-mod-beacon">
                                    <table class="table table-borderless">
                                        <tr><td>Codice:</td><td>
                                                <input type="text" name="codice" placeholder=" es. 150G2"
                                                       size="30" autofocus="true"></td></tr>
                                        <tr><td>Coord. X:</td><td>
                                                <input type="text" name="coord-x"
                                                        placeholder=" (invariato)" size="30"></td></tr>
                                        <tr><td>Coord. Y:</td><td>
                                                <input type="text" name="coord-y"
                                                        placeholder=" (invariato)" size="30"></td></tr>
                                        <tr><td>Indici vari...</td><td>
                                                <input type="text" name="" placeholder=" (invariato)"
                                                        size="30"></td></tr>
                                    </table>
                                    <!-- Bottoni per tornare alla schermata precedente o per aggiornare le modifiche-->
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                            Annulla</button>
                                        <input class="btn btn-outline-success" type='submit' 
                                            style="font-weight: bold" value='Conferma modifiche'>
                                        <input type="hidden" name="id_beacon" value="">
                                        <input type="hidden" name="azione" value="modifica-beacon">
                                        <input type="hidden" name="nm" value="${nome_mappa}">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal Conferma Eliminazione Beacon -->
            <div id="modal-elimina-beacon" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                        <h5 class="modal-title">Conferma eliminazione beacon</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        </div>
                        <div class="modal-body">
                            <form action="DBModify" method="post" id="form-del-beacon">
                                <p>Sicuro di voler rimuovere il beacon selezionato?
                                    Questa azione non può essere annullata.</p>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                        Annulla</button>
                                    <input class="btn btn-danger" type='submit' value='Elimina'>
                                    <input type="hidden" name="id_beacon" value="">
                                    <input type="hidden" name="azione" value="elimina-beacon">
                                    <input type="hidden" name="nm" value="${nome_mappa}">
                                </div>
                            </form>                    
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Footer -->
        <%@include file="footer.html" %>
    </body>
</html>