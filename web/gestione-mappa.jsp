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
        <meta name="MSSmartTagsPreventParsing" content="true" /> 
        <meta http-equiv="pragma" content="no-cache" /> 
        <meta http-equiv="expires" content="0" /> 
        <meta http-equiv="Cache-Control" content="no-cache" /> 
        <meta http-equiv="pragma-directive" content="no-cache" /> 
        <meta http-equiv="cache-directive" content="no-cache" /> 
        <meta name="robots" content="all,follow" /> 

        <title>Breakout - Gestione Mappa</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link href="static/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css"/>
        <link href="static/css/full-height.css" rel="stylesheet" type="text/css"/>
        <link href="static/css/fontawesome/fontawesome-all.css" rel="stylesheet" type="text/css"/>
        <script src="static/js/bootbox.min.js" type="text/javascript"></script>
        <script src="static/js/bootstrap.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.js"></script>
        <script src="static/js/modal-forms.js" type="text/javascript"></script>
        <script src="static/js/maps.js" type="text/javascript"></script>
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
                </div>
            </div>
            <!--Bottoni per la gestione dei beacon e dei grafi-->
            <div class="row">
                <c:set var="quota" value="${requestScope.quota}" />
                <div class="col-md-2">
                    <button type="button" class="btn btn-secondary" style="margin-bottom: 20px;">
                        <a href="ObjectAccess?obj=piano&nm=${quota}"
                           style="color: inherit; text-decoration: none">
                            < Gestione Piano</a>
                    </button>
                </div>
                <div class="col-md-2">
                    <form action="ObjectAccess" method="POST">
                        <input type="submit" value="Gestione Beacon" class="btn btn-outline-dark"
                               style="font-weight: bold">
                        <input type="hidden" name="obj" value="beacon">
                        <input type="hidden" name="nm" value="${requestScope.id_mappa}">
                    </form>
                </div>
                <div class="col-md-2">
                    <form action="ObjectAccess" method="POST">
                        <input type="submit" value="Gestione Grafo" class="btn btn-outline-dark"
                               style="font-weight: bold">
                        <input type="hidden" name="obj" value="grafo">
                        <input type="hidden" name="nm" value="${nome}">
                    </form>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-2" style="margin-left: 30px">
                        <i class="fas fa-map-marker-alt"></i> = Beacon&nbsp;&nbsp;
                </div>
                <div class="col-md-2">
                    <i class="fas fa-circle"></i> = Nodo&nbsp;&nbsp;
                </div>
                <div class="col-md-6">
                    <i class="fas fa-asterisk"></i> = Punto di interesse
                </div>
            </div>
            <br>
            
            <!--Immagine della mappa fs:990x1572-->
            <div class="row">
                <c:set var = "url_img" value = "${requestScope.url_immagine}" />
                <div class="col-md-12" style="margin-bottom: 20px">
                    <div class="container" style="position:relative">
                        <canvas id="map-canvas" width="1000" height="1700"  style="border:1px solid #d3d3d3;" >                    
                        </canvas>
                        <img src="images/${url_img}" id="imag" hidden="true">
                        <div class="btn-group btn-group-toggle " data-toggle="buttons"
                             style="color:red; position:absolute; left: 0px; top: -13px; right: 0px">
                            
                            <label style="position: absolute; left: 8px;  top: 1px" >
                                <span  class="fas fa-crosshairs" >
                                </span><br>origine
                            </label>
                            
                            <c:forEach items="${requestScope.nodi}" var="nodo">
                                <c:set var="x" value="${nodo.getCoord_X_px()}"/>
                                <c:set var="y" value="${nodo.getCoord_Y_px()}"/>
                                <!--
                                Non so perché ma sulle ascisse vanno sommati 12px
                                -->
                                <label style="position:absolute; left:${x+12}px; top:${y}px;">
                                    <i class="fas fa-circle"></i>
                                </label>
                            </c:forEach>
                                
                            <c:forEach items="${requestScope.al_pdi}" var="pdi">
                                <c:set var="x" value="${pdi.getCoord_X_px()}"/>
                                <c:set var="y" value="${pdi.getCoord_Y_px()}"/>
                                <!--
                                Non so perché ma sulle ascisse vanno sommati 12px
                                -->
                                <label style="position:absolute; left:${x+11}px; top:${y}px;">
                                    <i class="fas fa-asterisk"></i>
                                </label>
                            </c:forEach>
                            
                            <c:forEach items="${requestScope.al_beacon}" var="beacon">
                                <c:set var="x" value="${beacon.getCoord_X_px()}"/>
                                <c:set var="y" value="${beacon.getCoord_Y_px()}"/>
                                <c:set var="codice" value="${beacon.getCodice()}"/>
                                <!--
                                Sulle y va compensato l'offset inserito con il css (13+1)px
                                -->
                                <label class="btn" name="marker" id="${codice}"
                                       style="position:absolute; left:${x}px; top:${y-14}px;">
                                    <i data-toggle="tooltip" data-placement="top"
                                    title="${codice}" class="fas fa-map-marker-alt"></i>
                                </label>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
                    
        <!-- Footer -->
        <%@include file="footer.html" %>
    </body>
</html>