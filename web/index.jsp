<%-- 
    Document   : index
    Created on : 15-mag-2018, 14.29.18
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it-IT">
    <head>
        <title>Breakout - Accedi</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
        <script src="static/bootstrap-4.1.1-dist/js/bootbox.min.js"></script>
    </head>
    <body>
        <!-- Header -->
        <%@include file="header.jsp" %>
        
        <!-- Page Content -->
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <h1>Autenticazione</h1>
                    <br>
                    <form method="POST" action="j_security_check">
                        <p style="font-size: xx-large">Username</p>
                        <input type="text" name="j_username"  autofocus="true" size="45" style=" height:40px">
                        <br><br>
                        <p style="font-size: xx-large">Password</p> 
                        <input type="password" name="j_password" size="45" style=" height:40px">
                        <br><br>
                        <input type="submit" value="Accedi" class="btn btn-lg btn-outline-success"
                               style="font-weight: bold" name="accedi">
                    </form>
                </div>
            </div>
        </div>
        
        <!-- Footer -->
        <%@include file="footer.html" %>
    </body>
</html>