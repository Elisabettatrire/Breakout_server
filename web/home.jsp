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
        <link href="static/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css"/>
        <link href="static/css/full-height.css" rel="stylesheet" type="text/css"/>
        <link href="static/css/fontawesome/fontawesome-all.css" rel="stylesheet" type="text/css"/>
        <script src="static/js/bootbox.min.js" type="text/javascript"></script>
        <script src="static/js/bootstrap.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.js"></script>
        <script src="static/js/modal-forms.js" type="text/javascript"></script>
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
                <%@include file="form/beacon-csv.jsp" %>
            </div>
        </div>
        
        <!-- Footer -->
        <%@include file="footer.html" %>
    </body>
</html>