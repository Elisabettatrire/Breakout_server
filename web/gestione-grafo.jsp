<%-- 
    Document   : gestione-grafo
    Created on : 22-mag-2018, 17.00.09
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html lang="it-IT">
    <head>
        <title>Breakout - Gestione Grafo</title>
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
                <div class="col-md-12">
                    <c:set var="nome_mappa" value="${requestScope.nome}" />
                    <h2>Mappa ${nome_mappa} - Gestione Grafo</h2>
                    <br><br>
                </div>
            </div>
            <div class="row">
                <div class="col-md-8">
                    <!-- Tabella dei Nodi della mappa -->
                    <h4>Lista Nodi</h4>
                        <table class="display" style="width:100%; text-align: center">
                            <thead>
                                <tr><th scope="col">Codice</th>
                                    <th scope="col">Coord. X</th>
                                    <th scope="col">Coord. Y</th>
                                    <th scope="col">Larghezza</th>
                                    <th scope="col">Modifica</th>
                                    <th scope="col">Elimina</th>
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
                                    data-toggle="modal" data-target="#modal-agg-nodo">
                                <b>Aggiungi nodo</b>
                            </button>
                        </div>
                    <hr>
                    <!-- Tabella dei Tronchi della mappa -->
                    <h4>Lista Tronchi MI SERVE LA TABELLA TRONCHI COMPLETA</h4>
                    <table class="display" style="width:100%; text-align: center">
                        <thead>
                            <tr>
                                <th>Codice</th><th>Lunghezza</th><th>Cod. Nodo 1</th>
                                <th>Cod. Nodo 2</th><th>Modifica</th><th>Elimina</th>
                            </tr>
                        </thead>
                        <c:forEach items="${requestScope.tronchi}" var="tronco">
                            <c:set var="codice_tronco" value="${tronco.getCodice()}"/>
                            <c:set var="lunghezza" value="${tronco.getLunghezza()}"/>
                            <c:set var="nodi" value="${tronco.getNodiLong()}"/>
                            <tr><td>${codice_tronco}</td><td>${lunghezza}</td><td>${nodi[0]}</td>
                                <td>${nodi[1]}</td>
                                <td><button id="mod-${codice_tronco}" class="btn btn-outline-dark btn-sm"
                                            data-toggle="modal" data-target="#modal-mod-tronco">
                                        <span class="fas fa-cog"></span></button></td>
                                <td><button id="del-${codice_tronco}" class="btn btn-outline-danger btn-sm"
                                            data-toggle="modal" data-target="#modal-elimina-tronco">
                                        <span class="fas fa-trash-alt"></span></button></td></tr>
                        </c:forEach>
                    </table>
                    <div style="text-align: right; margin-top: 10px">
                        <button type="button" class="btn btn-outline-success"
                                data-toggle="modal" data-target="#modal-agg-tronco">
                            <b>Aggiungi tronco</b>
                        </button>
                    </div>
                    <hr>
                </div>
                <!-- Immagine della mappa fs:990x1572 -->
                <c:set var = "nome_img" value = "${fn:replace(requestScope.nome, '/', '-')}" />
                <div class="col-md-4" >  
                    <img src="/Immagini/${nome_img}.jpg" width="396" height="630">
                </div>
            </div>
                    
            <!-- Tabella dei Punti di Interesse-->
            <div class="row">
                <div class="col-md-12">
                    <br><br>
                    <h4>Lista punti di interesse</h4>
                    <table class="display" style="width:100%; text-align: center">
                        <thead>
                            <tr>
                                <th>Codice</th><th>Coord. X</th><th>Coord. Y</th><th>Larghezza</th>
                                <th>Lunghezza</th><th>Tipo</th><th>Modifica</th>
                                <th>Elimina</th>
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
                        data-toggle="modal" data-target="#modal-agg-pdi">
                        <b>Aggiungi PDI</b>
                    </button>
                </div>
            </div>
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
        </div>
        
        <!-- Finestra popup aggiunta di un nodo -->
        <div id="modal-agg-nodo" class="modal fade">
            <div class="modal-dialog " >
                <div class="modal-content ">
                    <div class="modal-header" >
                        <h5 class="modal-title">Aggiungi nodo</h5>
                        <button type="button" class="close" data-dismiss="modal">&times;</button> 
                    </div>
                    <div class="modal-body">
                        <div class="col-md-12">
                            <p class="warning">
                                Devono essere compilati tutti i campi, altrimenti l'inserimento
                                non verrà portato a termine.</p>
                        </div>
                        <!-- text area per inserire i dati dei nodi da caricare -->
                        <div  class="col-md-12">
                            <form action="DBModify" method="post">
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
                                    <tr><td>Larghezza (m):</td><td>
                                            <input type="text" name="larghezza" placeholder=" es. 1.8"
                                                    size="30"></td></tr>
                                </table>
                                <!-- Bottoni per tornare alla schermata precedente o per aggiungere il nodo -->
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                                    <input class="btn btn-outline-success" type='submit' 
                                        style="font-weight: bold" value='Aggiungi nodo'>
                                    <input type="hidden" name="azione" value="aggiungi-nodo">
                                    <input type="hidden" name="nm" value="${nome_mappa}">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Finestra popup per la modifica di un nodo-->
        <div id="modal-mod-nodo" class="modal fade">
            <div class="modal-dialog " >
                <div class="modal-content ">
                    <div class="modal-header" >
                        <h5 class="modal-title">Modifica nodo</h5>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <!-- text area per modificare i dati dei nodi caricati -->
                    <div class="modal-body">
                        <div  class="col-md-12">
                            <form action="DBModify" method="post" id="form-mod-nodo">
                                <table class="table table-borderless" style="margin-left: auto; margin-right: auto">
                                    <tr><td>Codice:</td><td>
                                            <input type="text" name="codice" size="30"
                                                   placeholder="&nbsp;(invariato)" autofocus="true"></td></tr>
                                    <tr><td>Coord. X:</td><td>
                                            <input type="text" name="coord-x" size="30"
                                                    autofocus="true" placeholder="&nbsp;(invariato)"></td></tr>
                                    <tr><td>Coord. Y:</td><td>
                                            <input type="text" name="coord-y" size="30"
                                                        placeholder="&nbsp;(invariato)"></td></tr>
                                    <tr><td>Larghezza (m):</td>
                                        <td><input type="text" name="larghezza"size="30"
                                                        placeholder="&nbsp;(invariato)"></td></tr>
                                </table>
                                <!-- Bottoni per tornare alla schermata precedente o per aggiornare le modifiche-->
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                        Annulla</button>
                                    <input class="btn btn-outline-success" type='submit' 
                                        style="font-weight: bold" value='Conferma modifiche'>
                                    <input type="hidden" name="id_nodo" value="">
                                    <input type="hidden" name="azione" value="modifica-nodo">
                                    <input type="hidden" name="nm" value="${nome_mappa}">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
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
           
        <!-- Finestra popup aggiunta di un tronco-->
        <div id="modal-agg-tronco" class="modal fade">
            <div class="modal-dialog " >
                <div class="modal-content ">
                    <div class="modal-header" >
                        <h5 class="modal-title">Aggiungi tronco</h5>
                        <button type="button" class="close" data-dismiss="modal">&times;</button> 
                    </div>
                    <!-- text area per inserire i dati del tronco da caricare -->
                    <div class="modal-body">
                        <div class="col-md-12">
                            <p class="warning">
                                Devono essere compilati tutti i campi, altrimenti l'inserimento
                                non verrà portato a termine.</p>
                        </div>
                        <div  class="col-md-12">
                            <form>
                                <table class="table table-borderless">
                                    <tr><td>Codice Nodo 1:</td><td>
                                        <select name="codice-1">
                                            <option selected disabled hidden>Seleziona</option>
                                            <option>150G1G2</option>
                                            <option>150G2</option>
                                        </select></td></tr>
                                    <tr><td>Codice Nodo 2:</td><td>
                                        <select name="codice-2">
                                            <option selected disabled hidden>Seleziona</option>
                                            <option>150G1G2</option>
                                            <option>150G2</option>
                                        </select></td></tr> 
                                </table>
                                <!-- Bottoni per tornare alla schermata precedente o per aggiungere il tronco -->
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                        Annulla</button>
                                    <input class="btn btn-outline-success" type='submit' 
                                        style="font-weight: bold" value='Aggiungi tronco' name='aggiungi-tronco'>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Finestra popup per la modifica di un tronco -->
        <div id="modal-mod-tronco" class="modal fade">
            <div class="modal-dialog ">
                <div class="modal-content ">
                    <div class="modal-header" >
                        <h5 class="modal-title">Modifica tronco</h5>
                        <button type="button" class="close" data-dismiss="modal">&times;</button> 
                    </div>
                    <!-- text area per modificare i dati dei tronchi caricati -->
                    <div class="modal-body">
                        <div  class="col-md-12">
                            <form>
                                <table class="table table-borderless">
                                    <tr><td>Codice Nodo 1:</td><td>
                                        <select name="codice-1">
                                            <option selected disabled hidden>Seleziona</option>
                                            <option>150G1G2</option>
                                            <option>150G2</option>
                                        </select></td></tr>
                                    <tr><td>Codice Nodo 2:</td><td>
                                        <select name="codice-2">
                                            <option selected disabled hidden>Seleziona</option>
                                            <option>150G1G2</option>
                                            <option>150G2</option>
                                        </select></td></tr>                               
                                </table>
                                <!-- Bottoni per tornare alla schermata precedente o per aggiornare le modifiche -->
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
        </div>
        
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
                        <form action='#'>
                            <p>Sicuro di voler rimuovere il tronco selezionato?
                                Questa azione non può essere annullata.</p>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                                <input class="btn btn-danger" type='submit' value='Elimina'>
                            </div>
                        </form>                    
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Finestra popup aggiunta di un PDI-->
        <div id="modal-agg-pdi" class="modal fade">
            <div class="modal-dialog ">
                <div class="modal-content ">
                    <div class="modal-header" >
                        <h5 class="modal-title">Aggiungi PDI</h5>
                        <button type="button" class="close" data-dismiss="modal">&times;</button> 
                    </div>
                    <!-- text area per inserire i dati dei nodi da caricare -->
                    <div class="modal-body">
                        <div class="col-md-12">
                            <p class="warning">
                                Devono essere compilati tutti i campi, altrimenti l'inserimento
                                non verrà portato a termine.</p>
                        </div>
                        <div  class="col-md-12">
                            <form action="DBModify" method="post">
                                <table class="table table-borderless">
                                    <tr><td>Codice:</td><td>
                                            <input type="text" name="codice" placeholder="Inserisci Codice"
                                                   size="30" autofocus="true"></td></tr>
                                    <tr><td>Coord. X:</td>
                                        <td><input type="text" name="coord-x" placeholder=" es."
                                                   size="30"></td></tr>
                                    <tr><td>Coord. Y:</td><td>
                                            <input type="text" name="coord-y" placeholder=" es."
                                                   size="30"></td></tr>
                                    <tr><td>Lunghezza:</td><td>
                                            <input type="text" name="lunghezza" placeholder=" es."
                                                   size="30"></td></tr>
                                    <tr><td>Larghezza:</td><td>
                                            <input type="text" name="larghezza" placeholder=" es."
                                                   size="30"></td></tr>
                                    <tr><td>Tipo:</td><td>
                                            <select name='tipo'>
                                                <option selected value="Aula">Aula</option>
                                                <option value="Punto Di Ritrovo">Punto di Ritrovo</option>
                                            </select></td></tr>
                                </table>
                                <!-- Bottoni per tornare alla schermata precedente o per aggiungere il PDI-->
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                                    <input class="btn btn-outline-success" type='submit' 
                                        style="font-weight: bold" value='Aggiungi PDI'>
                                    <input type="hidden" name="azione" value="aggiungi-pdi">
                                    <input type="hidden" name="nm" value="${nome_mappa}">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Finestra popup per la modifica di un PDI-->
        <div id="modal-mod-pdi" class="modal fade">
            <div class="modal-dialog " >
                <div class="modal-content ">
                    <div class="modal-header" >
                        <h5 class="modal-title">Modifica PDI</h5>
                        <button type="button" class="close" data-dismiss="modal">&times;</button> 
                    </div>
                    <div class="modal-body">
                        <div  class="col-md-12">
                            <form action="DBModify" method="post" id="form-mod-pdi">
                                <table class="table table-borderless">
                                    <tr><td>Codice:</td><td>
                                            <input type="text" name="codice" size="30"
                                                   placeholder=" (invariato)" autofocus="true"></td></tr>
                                    <tr><td>Coord. X:</td>
                                        <td><input type="text" name="coord-x" size="30"
                                                   placeholder=" (invariato)"></td></tr>
                                    <tr><td>Coord. Y:</td><td>
                                            <input type="text" name="coord-y" size="30"
                                                   placeholder=" (invariato)"></td></tr>
                                    <tr><td>Lunghezza:</td><td>
                                            <input type="text" name="lunghezza" size="30"
                                                   placeholder=" (invariato)"></td></tr>
                                    <tr><td>Larghezza:</td><td>
                                            <input type="text" name="larghezza" size="30"
                                                   placeholder=" (invariato)"></td></tr>
                                    <tr><td>Tipo:</td><td>
                                        <select name="tipo">
                                            <option selected hidden value="invariato">(invariato)</option>
                                            <option value="Aula">Aula</option>
                                            <option value="Punto Di Ritrovo">Punto di Ritrovo</option>
                                        </select></td></tr>
                                </table>
                                <!-- Bottoni per tornare alla schermata precedente o per aggiornare il PDI-->
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-dark" data-dismiss="modal">
                                        Annulla</button>
                                    <input class="btn btn-outline-success" type='submit' 
                                        style="font-weight: bold" value='Conferma modifiche'>
                                    <input type="hidden" name="id_pdi" value="">
                                    <input type="hidden" name="azione" value="modifica-pdi">
                                    <input type="hidden" name="nm" value="${nome_mappa}">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
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