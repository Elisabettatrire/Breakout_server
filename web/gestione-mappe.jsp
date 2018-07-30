<%-- 
    Document   : gestione-mappe
    Created on : 15-mag-2018, 14.31.24
    Author     : Giovanni
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" session="true"%>
<c:set var="flag" value="${requestScope.flag}"/>
<c:set var="success" value="${requestScope.success}"/>

<!DOCTYPE html>
<html lang="it-IT">
    <head>
        <title>Breakout - Gestione Mappe</title>
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
        <script src="static/js/select-old-value.js" type="text/javascript"></script>
        <script src="static/js/disable-same-floor.js" type="text/javascript"></script>
        <script src="static/js/clear-selected.js" type="text/javascript"></script>
        <!-- Il codice js sottostante fa apparire i popup al termine del caricamento
        dei dati da csv, se lo metto in un file esterno non funziona -->
        <script type="text/javascript">
            // La variabile flag serve ad indicare che è stato chiamato il servlet,
            // è un controllo in più oltre alla variabile success
            $(document).ready(function(){
                // Se success è true il caricamento è avvenuto correttamente
                if('${success}'==="true" && '${flag}'==="true") {
                    $('#success-upload').modal('show');
                } else if('${success}'==="false" && '${flag}'==="true") {
                    // altrimenti è fallito
                    $('#failed-upload').modal('show');
                }
            });
        </script>
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
                <div class="col-md-6" style="text-align: right">
                    <button id="elimina-dati" data-toggle="modal" data-target="#modal-elimina-dati"
                            class="btn btn-outline-danger" id="btn-agg-csv" style="margin-right: 20px">
                        <b>Elimina Tutti i Beacon</b></button>
                    <button id="caricaCSV" data-toggle="modal" data-target="#modal-csv"
                            class="btn btn-outline-success" id="btn-agg-csv">
                        <b>Aggiungi i Beacon da CSV</b></button>
                </div>
            </div>
            <br>
            <div class="row">
                <!-- Table Piani -->
                <div class="col-md-12">
                    <h4>Lista Piani</h4>
                    <table id="tabella-piani" class="display" style="width:100%; text-align: center">
                        <thead>
                            <tr><th>Quota&nbsp;&nbsp;
                                <i data-toggle="tooltip" data-placement="top"
                                    title="Clicca sulla quota per aprire la pagina di gestione del piano"
                                    class="fas fa-info-circle" style="color: #007bff;"></i>
                                </th><th class="no-sort">Modifica</th><th class="no-sort">Elimina</th>
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
                                class="btn btn-outline-success" id="btn-agg-piano">
                            <b>Aggiungi Piano</b></button>
                    </div>
                    <!-- End Table Piani -->
                    
                    <hr>
                    
                    <!-- Table Scale scrollabile-->
                    <h4>Lista Scale</h4>
                    <table class="table-striped display" style="text-align: center; width:100%;">
                        <thead>
                        <th>Codice</th><th>Lunghezza (m)</th><th>Cod. Nodo 1</th>
                            <th>Cod. Nodo 2</th><th>Cod. Beacon</th>
                            <th class="no-sort">Modifica</th><th class="no-sort">Elimina</th>
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
                        <button data-toggle="modal" data-target="#modal-agg-scala"
                                class="btn btn-outline-success" id="btn-agg-scala">
                            <b>Aggiungi Scala</b></button>
                    </div>
                </div>
                <!-- End Table Scale -->
                
            </div>
            <!-- End Row -->
            
        </div>
        <!-- End Page Content -->
        
        <!-- Modal Form Aggiungi Beacon da CSV -->
        <%@include file="form/beacon-csv.jsp"%>
        
        <!-- Modal Form Caricamento Avvenuto con Successo -->
        <%@include file="success-upload.jsp"%>
        
        <!-- Modal Form Caricamento Fallito -->
        <%@include file="failed-upload.jsp"%>
        
        <!-- Modal Conferma Eliminazione Lista Beacon -->
        <div id="modal-elimina-dati" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                    <h5 class="modal-title">Conferma eliminazione beacon</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    </div>
                    <div class="modal-body">
                        <form action='DBModify' method="post" id="form-del-dati">
                            <p>Sicuro di voler rimuovere tutti beacon dal database?
                                Questa azione non può essere annullata.</p>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                                <input class="btn btn-danger" type='submit' value='Elimina tutti i beacon'>
                                <input type="hidden" name="azione" value="elimina-beacon-all">
                                <input type="hidden" name="modalita" value="mappe">
                            </div>
                        </form>                    
                    </div>
                </div>
            </div>
        </div>
        <!-- End Modal Conferma Eliminazione Lista Beacon -->
        
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
