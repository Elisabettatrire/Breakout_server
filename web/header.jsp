<%-- 
    Document   : header
    Created on : 31-mag-2018, 17.59.20
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<div class="row" style="margin-right: 0em; margin-bottom: 40px">
    <div class="col-md-12" style="background-color: #28a745">
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <h1 style="color: #FFFFFF; font-size: 3em">
                        <a href="home.jsp" style="color: inherit; text-decoration: none">
                        Breakout Server Admin Panel
                        </a>
                    </h1>
                </div>

                <!--
                Il pulsante per il logout viene mostrato solo se la sessione è
                attiva, ovvero si è loggati come "admin"
                -->
                <jsp:useBean id="notifica" scope="application" class="it.breakout.models.Notifica"/>
                <% if(request.isUserInRole("admin")) {
                    if(notifica.retrieveLastState() == "false") { %>
                    <div class="col-md-2">
                        <form action="Emergency" method="POST">
                            <input type="hidden" name="trigger" value="start">
                            <input type="submit" class="btn btn-lg btn-danger"
                                   style="margin-top: 0.5em" name="emergency-on"
                                   value="Avvio Emergenza">
                        </form>
                    </div>
                    <% } else if(notifica.retrieveLastState() == "true"){ %>
                    <div class="col-md-2">
                        <form action="Emergency" method="POST">
                            <input type="hidden" name="trigger" value="abort">
                            <input type="submit" class="btn btn-lg btn-secondary"
                                   style="margin-top: 0.5em" name="emergency-off"
                                   value="Interruzione Emergenza">
                        </form>
                    </div>
                    <% } %>
                    <div class="col-md-2" style="text-align: right">
                        <form action="Logout" method="POST">
                            <input type="submit" class="btn btn-lg btn-success" 
                                   style="margin-top: 0.5em" value="Logout">
                        </form>
                    </div>
                <% } %>                    
            </div>
        </div>
    </div>
</div>
        
