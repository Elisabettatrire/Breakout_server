<%-- 
    Document   : gestione-grafo
    Created on : 22-mag-2018, 17.00.09
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html lang="it-IT">
    <head>
        <title>Breakout - Gestione Grafo</title>
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
        <script src="static/js/disable-select.js" type="text/javascript"></script>
        <script src="static/js/select-old-value.js" type="text/javascript"></script>
        <script src="static/js/clear-selected.js" type="text/javascript"></script>
    </head>
    <body>
        <!-- Header -->
        <%@include file="header.jsp" %>

        <!-- Page Content -->
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <c:set var="nome_mappa" value="${requestScope.nome}" />
                    <h2>Mappa ${nome_mappa} - Gestione Grafo</h2>
                    <br>
                    <div class="row">
                        <div class="col-md-2">
                            <form action="ObjectAccess" method="POST">
                                <input type="submit" value="< Gestione Mappa" class="btn btn-secondary">
                                <input type="hidden" name="obj" value="mappa">
                                <input type="hidden" name="nm" value="${requestScope.nome}">
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
                    <br>
                    <p>Da questa pagina è possibile gestire i nodi, i tronchi e i punti di interesse relativi
                    alla mappa.</p>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-12">
                    <!-- Tabella dei Nodi della mappa -->
                    <h4>Lista Nodi&nbsp;&nbsp;
                        <i data-toggle="tooltip" data-placement="top" style="color: #007bff; font-size: medium"
                            title="I nodi sono i punti della mappa in cui si incontrano diversi tronchi"
                            class="fas fa-info-circle" ></i></h4>
                    <table class="display" style="width:100%; text-align: center">
                        <thead>
                            <tr><th>Codice</th>
                                <th>Coord. X</th>
                                <th>Coord. Y</th>
                                <th>Larghezza</th>
                                <th class="no-sort">Modifica</th>
                                <th class="no-sort">Elimina</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.nodi}" var="nodo">
                                <c:set var="codice_nodo" value="${nodo.getCodice()}"/>
                                <c:set var="id_nodo" value="${nodo.getID()}"/>
                                <c:set var="x" value="${nodo.getCoord_X()}"/>
                                <c:set var="y" value="${nodo.getCoord_Y()}"/>
                                <c:set var="larghezza" value="${nodo.getLarghezza()}"/>
                                <tr><td>${codice_nodo}</td><td>${x}</td><td>${y}</td><td>${larghezza}</td>
                                    <td><button id="mod-${id_nodo}" class="btn btn-outline-dark btn-sm"
                                                data-toggle="modal" data-target="#modal-mod-nodo">
                                            <span class="fas fa-cog"></span></button></td>
                                    <td><button id="del-${id_nodo}" class="btn btn-outline-danger btn-sm"
                                                data-toggle="modal" data-target="#modal-elimina-nodo">
                                            <span class="fas fa-trash-alt"></span></button></td></tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div style="text-align: right; margin-top: 10px">
                        <button type="button" class="btn btn-outline-success"
                                data-toggle="modal" data-target="#modal-agg-nodo"
                                id="btn-agg-nodo">
                            <b>Aggiungi nodo</b>
                        </button>
                    </div>
                    <hr>
                    <!-- Tabella dei Tronchi della mappa -->
                    <h4>Lista Tronchi&nbsp;&nbsp;
                        <i data-toggle="tooltip" data-placement="top" style="color: #007bff; font-size: medium"
                            title="I tronchi sono i segmenti della mappa che identificano delle porzioni di percorso"
                            class="fas fa-info-circle" ></i></h4>
                    <table class="display" style="width:100%; text-align: center">
                        <thead>
                            <tr>
                                <th>Codice</th><th>Lunghezza</th><th>Cod. Nodo 1</th>
                                <th>Cod. Nodo 2</th><th>Cod. Beacon</th>
                                <th class="no-sort">Modifica</th><th class="no-sort">Elimina</th>
                            </tr>
                        </thead>
                        
                        <c:forEach items="${requestScope.tronchi}" var="tronco">
                            <c:set var="codice_tronco" value="${tronco.getCodice()}"/>
                            <c:set var="lunghezza" value="${tronco.getLunghezza()}"/>
                            <c:set var="nodi" value="${tronco.getNodiInteger()}"/>
                            <c:set var="codici_nodi" value="${tronco.getCodiciNodi()}"/>
                            <c:set var="codice_beacon" value="${tronco.getCodiceBeacon()}"/>
                            <c:set var="id_tronco" value="${tronco.getID()}"/>
                            <c:set var="id_beacon" value="${tronco.getID_beacon()}"/>
                            <c:set var="bundle" value="${id_tronco}-${nodi[0]}-${nodi[1]}-${id_beacon}"/>
                            <tr><td>${codice_tronco}</td><td>${lunghezza}</td><td>${codici_nodi[0]}</td>
                                <td>${codici_nodi[1]}</td><td>${codice_beacon}</td>
                                <td><button id="mod-${id_tronco}" class="btn btn-outline-dark btn-sm"
                                            data-toggle="modal" data-target="#modal-mod-tronco">
                                        <span class="fas fa-cog"></span></button></td>
                                <td><button id="del-${id_tronco}" class="btn btn-outline-danger btn-sm"
                                            data-toggle="modal" data-target="#modal-elimina-tronco">
                                        <span class="fas fa-trash-alt"></span></button></td></tr>
                                <!-- Questo paragrafo nascosta serve per il js che
                                disabilita le select -->
                                <p style="display: none" id="${bundle}"></p>
                        </c:forEach>
                    </table>
                    <div style="text-align: right; margin-top: 10px">
                        <button type="button" class="btn btn-outline-success"
                                data-toggle="modal" data-target="#modal-agg-tronco"
                                id="btn-agg-tronco">
                            <b>Aggiungi tronco</b>
                        </button>
                    </div>
                    <hr>
                </div>
                <!-- Tabella dei Punti di Interesse-->
                <div class="col-md-12">
                    <h4>Lista punti di interesse&nbsp;&nbsp;
                        <i data-toggle="tooltip" data-placement="top" style="color: #007bff; font-size: medium"
                            title="I punti di interesse (PDI) sono dei nodi che hanno un'importanza particolare; per questo sono corredati di una breve descrizione"
                            class="fas fa-info-circle" ></i></h4>
                    <table class="display" style="width:100%; text-align: center">
                        <thead>
                            <tr>
                                <th>Codice</th><th>Coord. X</th><th>Coord. Y</th><th>Larghezza</th>
                                <th>Lunghezza</th><th class="no-sort">Tipo</th>
                                <th class="no-sort">Modifica</th>
                                <th class="no-sort">Elimina</th>
                            </tr>
                        </thead>
                        <c:forEach items="${requestScope.pdis}" var="pdi">
                            <c:set var="codice_pdi" value="${pdi.getCodice()}"/>
                            <c:set var="id_pdi" value="${pdi.getID()}"/>
                            <c:set var="x" value="${pdi.getCoord_X()}"/>
                            <c:set var="y" value="${pdi.getCoord_Y()}"/>
                            <c:set var="larghezza" value="${pdi.getLarghezza()}"/>
                            <c:set var="lunghezza" value="${pdi.getLunghezza()}"/>
                            <c:set var="tipo" value="${pdi.getTipo()}"/>
                            <tr><td>${codice_pdi}</td><td>${x}</td><td>${y}</td><td>${larghezza}</td>
                                <td>${lunghezza}</td><td>${tipo}</td>
                                <td><button id="mod-${id_pdi}" class="btn btn-outline-dark btn-sm"
                                            data-toggle="modal" data-target="#modal-mod-pdi">
                                        <span class="fas fa-cog"></span></button></td>
                                <td><button id="del-${id_pdi}" class="btn btn-outline-danger btn-sm"
                                            data-toggle="modal" data-target="#modal-elimina-pdi">
                                        <span class="fas fa-trash-alt"></span></button></td></tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12" style="text-align: right;  margin-top: 10px">
                    <button type="button" class="btn btn-outline-success"
                        data-toggle="modal" data-target="#modal-agg-pdi"
                        id="btn-agg-pdi">
                        <b>Aggiungi PDI</b>
                    </button>
                </div>
            </div>
        </div>
        
        <!-- Modal Form Aggiungi Nodo -->
        <%@include file="form/aggiungi-nodo.jsp"%>

        <!-- Modal Form Modifica Nodo-->
        <%@include file="form/modifica-nodo.jsp"%>
        
        <!-- Modal Conferma Eliminazione Nodo -->
        <div id="modal-elimina-nodo" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                    <h5 class="modal-title">Conferma eliminazione nodo</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    </div>
                    <div class="modal-body">
                        <form action="DBModify" method="post" id="form-del-nodo">
                            <p>Sicuro di voler rimuovere il nodo selezionato?
                                Questa azione non può essere annullata.</p>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                    Annulla</button>
                                <input class="btn btn-danger" type='submit' value='Elimina'>
                                <input type="hidden" name="id_nodo" value="">
                                <input type="hidden" name="azione" value="elimina-nodo">
                                <input type="hidden" name="nm" value="${nome_mappa}">
                            </div>
                        </form>                    
                    </div>
                </div>
            </div>
        </div>
           
        <!-- Modal Form Aggiungi Tronco -->
        <%@include file="form/aggiungi-tronco.jsp"%>

        <!-- Modal Form Modifica Tronco -->
        <%@include file="form/modifica-tronco.jsp"%>
        
        <!-- Modal Conferma Eliminazione Tronco -->
        <div id="modal-elimina-tronco" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                    <h5 class="modal-title">Conferma eliminazione tronchi</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    </div>
                    <div class="modal-body">
                        <form action="DBModify" method="post" id="form-del-tronco">
                            <p>Sicuro di voler rimuovere il tronco selezionato?
                                Questa azione non può essere annullata.</p>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                                <input class="btn btn-danger" type='submit' value='Elimina'>
                                <input type="hidden" name="id_tronco" value="">
                                <input type="hidden" name="azione" value="elimina-tronco">
                                <input type="hidden" name="nm" value="${nome_mappa}">
                            </div>
                        </form>                    
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Modal Form Aggiungi PDI -->
        <%@include file="form/aggiungi-pdi.jsp"%>
        
        <!-- Modal Form Modifica PDI -->
        <%@include file="form/modifica-pdi.jsp"%>
        
        <!-- Modal Conferma Eliminazione Pdi -->
        <div id="modal-elimina-pdi" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                    <h5 class="modal-title">Conferma eliminazione PDI</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    </div>
                    <div class="modal-body">
                        <form action="DBModify" method="post" id="form-del-pdi">
                            <p>Sicuro di voler rimuovere il PDI selezionato?
                                Questa azione non può essere annullata.</p>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                                <input class="btn btn-danger" type='submit' value='Elimina'>
                                <input type="hidden" name="id_pdi" value="">
                                <input type="hidden" name="azione" value="elimina-pdi">
                                <input type="hidden" name="nm" value="${nome_mappa}">
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