<%-- 
    Document   : home
    Created on : 1-giu-2018, 16.33.42
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it-IT">
    <head>
        <title>Breakout - Home</title>
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
            <div class="row" style="margin-top: 100px; margin-bottom: 30px">
                <div class="col-md-6" style="text-align: right">
                    <form action="DBAccess" method="POST">
                        <input type="submit" value="Gestione Mappe" class="btn btn-lg btn-success" name="mappe"
                                   style="width: 500px; height: 250px; font-size: 26pt">
                        <input type="hidden" name="modalita" value="mappe">
                    </form>
                </div>
                <div class="col-md-6" style="text-align: left">
                    <form action="DBAccess" method="POST">
                        <input type="submit" value="Gestione Utenti App" class="btn btn-lg btn-success" name="utenti"
                                   style="width: 500px; height: 250px; font-size: 26pt">
                        <input type="hidden" name="modalita" value="utenti">
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4" style="text-align: center">
                    <table>
                        <tr style="text-align: left"><td><p style="font-size: x-large">
                                    Importa dati da file <a data-toggle="modal" data-target="#modal-info">.csv
                                    </a>
                                </p>
                            </td></tr>
                        <tr style="text-align: center">
                            <td colspan="2"><form action="FileUploader" method="post" enctype="multipart/form-data">
                                    <input type="file" accept=".csv" name="file" id="input-csv">
                                    <tr><td><input type="submit" value="Carica"></td></tr>
                                </form></td></tr>
                    </table>
                </div>
                <div class="col-md-4"></div>
            </div>
        </div>
        
        <!-- Modal informazioni riguardo il file csv -->
        <div id="modal-info" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Importazione dati da CSV</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="col-md-12">
                            <p>Qui andiamo scrivere come deve essere fatto il file per
                                caricare correttamente i dati.</p>             
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">OK</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Footer -->
        <%@include file="footer.html" %>
    </body>
</html>