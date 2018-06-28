<%-- 
    Document   : gestione-mappa
    Created on : 23-mag-2018, 17.56.17
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html lang="it-IT">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Breakout - Gestione Mappa</title>
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
                <div class="col-md-6">
                    <c:set var="nome" value="${requestScope.nome}" />
                    <h2>Gestione Mappa ${nome}</h2>
                    <br>
                    <br>
                    <!--Bottoni per la gestione dei beacon e dei grafi-->
                    <div class="row">
                        <div class="col-md-6">
                            <form action="ObjectAccess" method="POST">
                                <input type="submit" value="Gestione Beacon" class="btn btn-outline-dark"
                                       style="font-weight: bold">
                                <input type="hidden" name="obj" value="beacon">
                                <input type="hidden" name="nm" value="${requestScope.id_mappa}">
                            </form>
                        </div>
                        <div class="col-md-6" style="text-align: right">
                            <form action="ObjectAccess" method="POST">
                                <input type="submit" value="Gestione Grafo" class="btn btn-outline-dark"
                                       style="font-weight: bold">
                                <input type="hidden" name="obj" value="grafo">
                                <input type="hidden" name="nm" value="${nome}">
                            </form>
                        </div>
                    </div>
                    <br><br>
                    <!--Tabella dei beacon e dei tronchi presenti nella mappa-->
                    <table class="table table-bordered table-striped" style="text-align: center">
                        <thead>
                            <tr><th>Nome beacon</th><th>Codice Tronco/codice PDI</th></tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.al_beacon}" var="beacon">
                                <c:set var="id_beacon" value="${beacon.getID_beacon()}"/>
                                <tr><td>Beacon B${id_beacon}</td><td><i>Codice tronco</i></td></tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div style="text-align: right">
                        <button  id="agg-collegamento" type="button" class="btn btn-outline-success"
                                 data-toggle="modal" data-target="#agg-coll">
                            <b>Aggiungi collegamento</b></button>
                    </div>
                    <c:set var="quota" value="${requestScope.quota}" />
                    <div class="row">
                        <div class="col-md-6">
                            <button type="button" class="btn btn-secondary">
                                <a href="ObjectAccess?obj=piano&nm=${quota}"
                                   style="color: inherit; text-decoration: none">
                                    < Gestione Piano</a>
                            </button>
                        </div>
                    </div>
                </div>
                <!--Immagine dell mappa fs:990x1572-->
                <c:set var = "nome_img" value = "${fn:replace(requestScope.nome, '/', '-')}" />
                <div class="col-md-4" >  
                    <img src="/Immagini/${nome_img}.jpg" width="396" height="630">
                </div>
            </div>
            
            
        </div>
        
        <!-- Modal Form Aggiungi Collegamento -->
        <%@include file="form/aggiungi-collegamento.jsp"%>
        
        <!-- Footer -->
        <%@include file="footer.html" %>
    </body>
</html>