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
        <link href="static/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css"/>
        <link href="static/css/full-height.css" rel="stylesheet" type="text/css"/>
        <link href="static/css/fontawesome/fontawesome-all.css" rel="stylesheet" type="text/css"/>
        <script src="static/js/bootbox.min.js" type="text/javascript"></script>
        <script src="static/js/bootstrap.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.js"></script>
        <script src="static/js/modal-forms.js" type="text/javascript"></script>
        <script src="static/js/scroll-order-table.js" type="text/javascript"></script>
        <script src="static/js/select-old-pdi.js" type="text/javascript"></script>
        <script src="static/js/clear-selected.js" type="text/javascript"></script>
    </head>
    <body>
        <!-- Header -->
        <%@include file="header.jsp" %>

        <!-- Page Content -->
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <c:set var="nome_mappa" value="${requestScope.nome_mappa}"/>
                    <h2>Mappa ${nome_mappa} - Gestione Beacon</h2>
                    <br>
                    <div class="row">
                        <div class="col-md-2">
                            <form action="ObjectAccess" method="POST">
                                <input type="submit" value="< Gestione Mappa" class="btn btn-secondary">
                                <input type="hidden" name="obj" value="mappa">
                                <input type="hidden" name="nm" value="${nome_mappa}">
                            </form>
                        </div>
                        <div class="col-md-2">
                            <form action="ObjectAccess" method="POST">
                                <input type="submit" value="Gestione Piano" class="btn btn-secondary">
                                <input type="hidden" name="obj" value="piano">
                                <input type="hidden" name="nm" value="${requestScope.quota}">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-12">
                    <h4>Lista Beacon</h4>
                    <!--Tabella dei beacon della mappa-->
                    <table class="table-striped display" style="text-align: center; width:100%;">
                        <thead>
                            <tr><th>Nome</th><th>Coord. X (px)</th><th>Coord. Y (px)</th>
                                <th>Ind. fuoco</th><th>Ind. fumi</th><th>NDC</th>
                                <th>Ind. rischio</th><th>PDI</th>
                                <th class="no-sort">Modifica</th>
                                <th class="no-sort">Elimina</th></tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.al_beacon}" var="beacon">
                                <c:set var="id_beacon" value="${beacon.getID_beacon()}"/>
                                <c:set var="codice" value="${beacon.getCodice()}"/>
                                <c:set var="x" value="${beacon.getCoord_X()}"/>
                                <c:set var="y" value="${beacon.getCoord_Y()}"/>
                                <c:set var="fuoco" value="${beacon.getInd_fuoco()}"/>
                                <c:set var="fumi" value="${beacon.getInd_fumi()}"/>
                                <c:set var="ndc" value="${beacon.getInd_NDC()}"/>
                                <c:set var="rischio" value="${beacon.getInd_rischio()}"/>
                                <c:set var="codice_pdi" value="${beacon.getCodicePdi()}"/>
                                <tr><td>${codice}</td><td>${x}</td><td>${y}</td>
                                    <td>${fuoco}</td><td>${fumi}</td><td>${ndc}</td>
                                    <td>${rischio}</td><td>${codice_pdi}</td>
                                <td><button id="mod-${id_beacon}" class="btn btn-outline-dark btn-sm"
                                            data-toggle="modal" data-target="#modal-mod-beacon">
                                        <span class="fas fa-cog"></span></button></td>
                                <td><button id="del-${id_beacon}" class="btn btn-outline-danger btn-sm"
                                            data-toggle="modal" data-target="#modal-elimina-beacon">
                                        <span class="fas fa-trash-alt"></span></button></td></tr>
                                <!-- Questo paragrafo nascosta serve per il js che
                                disabilita le select -->
                                <p style="display: none" id="${id_beacon}-${beacon.getID_pdi()}"></p>
                            </c:forEach>
                        </tbody>
                    </table>
                    <br>
                    <!--Bottoni per la gestione dei beacon-->
                    <div style="text-align: right; margin-top: 10px">
                        <button class="btn btn-outline-success" data-toggle="modal"
                                data-target="#modal-beacon" id="btn-agg-beacon">
                            <b>Aggiungi beacon</b>
                        </button>
                    </div>
                </div>
                </div>
            </div>
            
            <!-- Modal Form Aggiungi Beacon -->
            <%@include file="form/aggiungi-beacon.jsp"%>

            <!-- Modal Form Modifica Beacon -->
            <%@include file="form/modifica-beacon.jsp"%>

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