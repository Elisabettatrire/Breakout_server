<%-- 
    Document   : gestione-beacon
    Created on : 22-mag-2018, 14.54.41
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it-IT">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Breakout - Gestione Beacon</title>
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
    </head>
    <body>
        <!-- Header -->
        <%@include file="header.html" %>

        <!-- Page Content -->
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <h2>Mappa Q150\1 - Gestione Beacon</h2>
                    <br><br>
                    <h4>Lista Beacon</h4>
                    <form>
                        <!--Tabella dei beacon della mappa-->
                        <table class="table table-bordered table-striped" style="text-align: center">
                            <thead>
                                <tr><th>Nome</th><th>Coord_X</th><th>Coord_Y</th></tr>
                            </thead>
                            <tr><td>Beacon B1</td><td>x1</td><td>y1</td></tr>
                            <tr><td>Beacon B2</td><td>x2</td><td>y2</td></tr>
                            <tr><td>Beacon B3</td><td>x3</td><td>y3</td></tr>
                        </table>
                    </form>
                    <br>
                    <!--Bottoni per la gestione dei beacon-->
                    <div class="row">
                        <div class="col-md-6">
                            <button type="button" class="btn btn-outline-danger">
                                <b>Elimina i beacon selezionati</b>
                            </button>
                        </div>
                        <div class="col-md-6" style="text-align: right">
                            <button type="button" class="btn btn-outline-success" data-toggle="modal"
                                    data-target="#modal-beacon">
                                <b>Aggiungi beacon</b>
                            </button>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 50px">
                        <div class="col-md-6">
                            <button type="button" class="btn btn-outline-dark">
                                <b>Indietro</b>
                            </button>
                        </div>
                    </div>
                </div>
                
                <!--Immagine dell mappa fs:990x1572-->
                <div class="col-md-6" style="text-align: center">
                    <img src="/Immagini/150_color.jpg" style="max-width:60%; max-height:60%">
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
                            <form>
                                <table class='table table-borderless'>
                                    <tr><td>Seleziona il file <i>.csv</i> contenente i dati del beacon</td></tr>
                                    <tr><td>
                                    <!-- Bottone per caricare il file .cvs contenente i dati dei beacon-->
                                    <div class="custom-file">
                                        <input type="file" class="custom-file-input" id="csv-beacon" lang="it">
                                        <label class="custom-file-label" for="csv-beacon">Scegli file</label>
                                    </div>
                                    </td></tr>
                                </table>
                                <div class="modal-footer">
                                    <!-- Bottoni per tornare alla schermata precedente o per aggiornare le modifiche-->
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                        Annulla
                                    </button>
                                    <input class="btn btn-outline-success" type='submit' 
                                            style="font-weight: bold" value='Aggiungi beacon' name='Aggiungi beacon'>
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