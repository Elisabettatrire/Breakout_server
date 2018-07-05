<%-- 
    Document   : gestione-mappe
    Created on : 15-mag-2018, 14.31.24
    Author     : Giovanni
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" session="true"%>

<!DOCTYPE html>
<html lang="it-IT">
    <head>
        <title>Breakout - Gestione Mappe</title>
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
        <script src="static/js/disable-select.js" type="text/javascript"></script>
        <script src="static/js/select-old-value.js" type="text/javascript"></script>
    </head>
    <body>
        <!-- Header -->
        <%@include file="header.jsp" %>
        
        <!-- Page Content -->        
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <button type="button" class="btn btn-secondary">
                        <a href="home.jsp" style="color: inherit; text-decoration: none">
                        < Home
                        </a>
                    </button>
                </div>
            </div>
            <br>
            <div class="row">
                <!-- Table Piani -->
                <div class="col-md-12">
                    <h4>Lista Piani</h4>
                    <table class="display" style="width:100%; text-align: center">
                        <thead>
                            <tr><th>Quota&nbsp;&nbsp;
                                <i data-toggle="tooltip" data-placement="top"
                                    title="Clicca sulla quota per aprire la pagina di gestione del piano"
                                    class="fas fa-info-circle" style="color: #007bff;"></i>
                                </th><th>Modifica</th><th>Elimina</th>
                        </thead>
                        <tbody>
                            <!-- Get data from request object -->
                            <c:forEach items="${requestScope.piani}" var="piano">
                                <c:set var="quota" value="${piano.getQuota()}"/>
                                <c:set var="id_piano" value="${piano.getID_piano()}"/>                            
                                <tr><td><a href='ObjectAccess?obj=piano&nm=${quota}'>${quota}</a></td>
                                    <td><button id="mod-${id_piano}" class="btn btn-outline-dark btn-sm"
                                            data-toggle="modal" data-target="#modal-mod-piano">
                                            <span class="fas fa-cog"></span></button></td>
                                    <td><button id="del-${id_piano}" class="btn btn-outline-danger btn-sm"
                                                data-toggle="modal" data-target="#modal-elimina-piano">
                                        <span class="fas fa-trash-alt"></span></button></td>
                                    </td></tr>
                            </c:forEach>
                        </tbody>
                        <!-- -->
                    </table>
                    <div style="text-align: right; margin-top: 10px">
                        <button id="aggiungiPiano" data-toggle="modal" data-target="#modal-piano"
                                class="btn btn-outline-success"><b>Aggiungi Piano</b></button>
                    </div>
                    <!-- End Table Piani -->
                    
                    <hr>
                    
                    <!-- Table Scale scrollabile-->
                    <h4>Lista Scale</h4>
                    <table class="table-striped display" style="text-align: center; width:100%;">
                        <thead>
                        <th>Codice</th><th>Lunghezza</th><th>Cod. Nodo 1</th>
                            <th>Cod. Nodo 2</th><th>Cod. Beacon</th>
                            <th>Modifica</th><th>Elimina</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.scale}" var="scala">
                                <c:set var="codice_scala" value="${scala.getCodice()}"/>
                                <c:set var="lunghezza" value="${scala.getLunghezza()}"/>
                                <c:set var="nodi" value="${scala.getNodiInteger()}"/>
                                <c:set var="codici_nodi" value="${scala.getCodiciNodi()}"/>
                                <c:set var="codice_beacon" value="${scala.getCodiceBeacon()}"/>
                                <c:set var="id_scala" value="${scala.getID()}"/>
                                <c:set var="id_beacon" value="${scala.getID_beacon()}"/>
                                <c:set var="bundle" value="${id_scala}-${nodi[0]}-${nodi[1]}-${id_beacon}"/>
                                <tr><td>${codice_scala}</td><td>${lunghezza}</td>
                                    <td>${codici_nodi[0]}</td><td>${codici_nodi[1]}</td>
                                    <td>${codice_beacon}</td>
                                    <td><button id="mod-${id_scala}" class="btn btn-outline-dark btn-sm"
                                            data-toggle="modal" data-target="#modal-mod-scala">
                                        <span class="fas fa-cog"></span></button></td>
                                    <td><button id="del-${id_scala}" class="btn btn-outline-danger btn-sm"
                                            data-toggle="modal" data-target="#modal-elimina-scala">
                                        <span class="fas fa-trash-alt"></span></button></td></tr>
                                <!-- Questo paragrafo nascosta serve per il js che
                                disabilita le select -->
                                <p style="display: none" id="${bundle}"></p>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div style="text-align: right; margin-top: 10px">
                        <button id="aggiungiScala" data-toggle="modal" data-target="#modal-agg-scala"
                                class="btn btn-outline-success"><b>Aggiungi Scala</b></button>
                    </div>
                </div>
                <!-- End Table Scale -->
                
            </div>
            <!-- End Row -->
            
        </div>
        <!-- End Page Content -->        
        
        <!-- Modal Form Aggiungi Piano -->
        <%@include file="form/aggiungi-piano.jsp"%>
        
        <!-- Modal Form Modifica Piano -->
        <%@include file="form/modifica-piano.jsp"%>
        
        <!-- Modal Conferma Eliminazione Piano -->
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
                        <form action='DBModify' method="post" id="form-del-piano">
                            <p>Sicuro di voler rimuovere il piano selezionato?
                                Questa azione non può essere annullata.</p>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                                <input class="btn btn-danger" type='submit' value='Elimina piano'>
                                <input type="hidden" name="id_piano" value="">
                                <input type="hidden" name="azione" value="elimina-piano">
                                <input type="hidden" name="modalita" value="mappe">
                            </div>
                        </form>                    
                    </div>
                </div>
            </div>
        </div>
        <!-- End Modal Conferma Eliminazione Piano -->
        
        <!-- Modal Form Aggiungi Scala -->
        <%@include file="form/aggiungi-scala.jsp"%>
        
        <!-- Modal Form Modifica Scala -->
        <%@include file="form/modifica-scala.jsp"%>
        
        <!-- Modal Conferma Eliminazione Scala -->
        <div id="modal-elimina-scala" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                    <h5 class="modal-title">Conferma eliminazione scala</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    </div>
                    <div class="modal-body">
                        <form action='DBModify' method="post" id="form-del-piano">
                            <p>Sicuro di voler rimuovere la scala selezioniata?
                                Questa azione non può essere annullata.</p>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                                <input class="btn btn-danger" type='submit' value='Elimina scala'>
                                <input type="hidden" name="id_scala" value="">
                                <input type="hidden" name="azione" value="elimina-scala">
                                <input type="hidden" name="modalita" value="mappe">
                            </div>
                        </form>                    
                    </div>
                </div>
            </div>
        </div>
        <!-- End Modal Conferma Eliminazione Scala -->
        
    </body>
</html>

<%@include file="footer.html" %>
