<%-- 
    Document   : home
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
    </head>
    <body>
        <!-- Header -->
        <%@include file="header.jsp" %>
        
        <!-- Page Content -->        
        <div class="container">
            <div class="row">
                <!-- Table Piani -->
                <div class="col-md-6">
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
                        <c:forEach items="${requestScope.piani}" var="piano" varStatus="i">
                            <c:set var="quota" value="${piano.getQuota()}"/>
                            <c:set var="indice" value="${i.index}"/>
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
                </div>
                <!-- End Table Piani -->
                
                <!-- Table Scale scrollabile-->
                <div class="col-md-6">
                    <h4>Lista Scale</h4>
                    <table class="table-striped display" style="text-align: center; width:100%;">
                        <thead>
                        <tr><th>Codice</th>
                            <th>Lunghezza</th>
                            <th>Codice Nodo 1</th>
                            <th>Codice Nodo 2</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.scale}" var="scala">
                                <c:set var="codice" value="${scala.getCodice()}"/>
                                <c:set var="lunghezza" value="${scala.getLunghezza()}"/>
                                <c:set var="nodi" value="${scala.getNodiInteger()}"/>
                                <tr><td>${codice}</td><td>${lunghezza}</td><td>${nodi[0]}</td><td>${nodi[1]}</td></tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div style="text-align: right; margin-top: 10px">
                        <button id="aggiungiScala" data-toggle="modal" data-target="#modal-scala"
                                class="btn btn-outline-success"><b>Aggiungi Scala</b></button>
                    </div>
                </div>
                <!-- End Table Scale -->
            </div>
            <!-- End Row -->
            
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
        <!-- End Page Content -->
        
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
                        <div class="col-md-12">
                            <p class="warning">
                                Devono essere compilati tutti i campi, altrimenti l'inserimento
                                non verrà portato a termine.</p>
                        </div>
                        <div class="col-md-12" style="text-align: center">
                            <form action="DBModify" method="post">
                                <table class="table table-borderless"><tr><td>Quota:</td>
                                        <td><input autofocus="true" type='text' name='quota'
                                                   size="30" placeholder='&nbsp;es. 155'>
                                </td></table>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                                    <input class="btn btn-outline-success" type='submit' 
                                           style="font-weight: bold" value='Aggiungi piano' name='aggiungi-piano'>
                                    <input type="hidden" name="azione" value="aggiungi-piano">
                                    <input type="hidden" name="modalita" value="mappe">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Modal Form Aggiungi Piano -->
        
        <!-- Modal Form Modifica Piano -->
        <div id="modal-mod-piano" method="post" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Modifica piano</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="col-md-12">
                            <p class="warning">
                                I campi non compilati manterrano il valore precedente.</p>
                        </div>
                        <div class="col-md-12" style="text-align: center">
                            <form action="DBModify" method="post" id="form-mod-piano">
                                <table class='table table-borderless'><tr><td>Quota:</td>
                                        <td><input autofocus="true" type='text' name='quota'
                                                   size="30" placeholder="&nbsp;(invariato)">
                                </td></table>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                                    <input class="btn btn-outline-success" type='submit' 
                                           style="font-weight: bold" value='Applica modifiche' name='modifica-piano'>
                                    <input type="hidden" name="id_piano" value="">
                                    <input type="hidden" name="azione" value="modifica-piano">
                                    <input type="hidden" name="modalita" value="mappe">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Modal Form Modifica Piano -->
        
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
                                <input class="btn btn-danger" type='submit' value='Elimina piano' name='elimina-piano'>
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
                        <div class="col-md-12">
                            <p class="warning">
                                Devono essere compilati tutti i campi, altrimenti l'inserimento
                                non verrà portato a termine.</p>
                        </div>
                        <div class="col-md-12">
                            <form action='#'>
                                <table class='table table-borderless'>
                                    <tr><td>Lunghezza (m):</td>
                                        <td><input type='text' name='lunghezza' size="30"
                                            autofocus="true" placeholder='&nbsp;es. 25'></td>
                                    <tr><td>Cod. Nodo 1:</td><td><input type='text'
                                            placeholder='&nbsp;es. 145UA5' name='nodo_1' size="30"></td>
                                    <tr><td>Cod. Nodo 2:</td><td><input type='text'
                                            placeholder='&nbsp;es. 145DICEA' name='nodo_1' size="30"></td>
                                </table>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                                    <input class="btn btn-outline-success" type='submit'
                                           style="font-weight: bold" value='Aggiungi scala' name='aggiungi-scala'>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Modal Form Aggiungi Scala -->

    </body>
</html>

<%@include file="footer.html" %>
