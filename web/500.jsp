<%-- 
    Document   : 500
    Created on : 3-giu-2018, 15.40.13
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it-IT">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Breakout - Error 500</title>
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
                    <h1 class="display-2">Errore 500</h1>
                    <br>
                    <p style="font-size: x-large">Il server ha riscontrato un errore interno</p>
                </div>
            </div>
            
            <div class="row" style="margin-bottom: 200px; margin-top: 40px">
                <div class="col-md-6">
                    <button type="button" class="btn btn-secondary">
                        <a href="home.jsp" style="color: inherit; text-decoration: none">
                        Torna alla Home
                        </a>
                    </button>
                </div>
            </div>
        </div>
        
        

        <!-- Footer -->
        <%@include file="footer.html" %>
    </body>
</html>