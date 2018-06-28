<%-- 
    Document   : aggiungi-immagine
    Created on : 28-giu-2018, 17.55.04
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<form action="UploadImage" method="post" enctype="multipart/form-data">
    <input type="file" name="immagine">
    <input type="submit" value="carica">
</form>
