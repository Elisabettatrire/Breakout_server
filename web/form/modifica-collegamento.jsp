<%-- 
    Document   : modifica-collegamento
    Created on : 5-lug-2018, 12.21.56
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- Modal Form Modifica Collegamento -->
<div id="modal-mod-collegamento" class="modal fade">
    <div class="modal-dialog ">
        <div class="modal-content ">
            <div class="modal-header" >
                <h5 class="modal-title">Modifica collegamento</h5>
                <button type="button" class="close" data-dismiss="modal">&times;</button> 
            </div>
            <!-- text area per modificare i dati dei collegamenti caricati -->
            <div class="modal-body">
                <div  class="col-md-12">
                    <form action="DBModify" method="post" id="form-mod-collegamento">
                        <table class="table table-borderless">
                            <tr><td>Codice Nodo 1:</td><td>
                                <select name="codice-1">
                                    <c:forEach items="${requestScope.nodi}" var="nodo">
                                        <c:set var="codice_nodo" value="${nodo.getCodice()}" />
                                        <c:set var="id_nodo" value="${nodo.getID()}" />
                                        <option value="${id_nodo}" data-mappa="${nodo.getID_mappa()}">
                                            ${codice_nodo}</option>
                                    </c:forEach>
                                    <c:forEach items="${requestScope.pdis}" var="pdi">
                                        <c:set var="codice_pdi" value="${pdi.getCodice()}" />
                                        <c:set var="id_pdi" value="${pdi.getID()}" />
                                        <option value="${id_pdi}" data-mappa="${pdi.getID_mappa()}">
                                            ${codice_pdi}</option>
                                    </c:forEach>
                                </select></td></tr>
                            <tr><td>Codice Nodo 2:</td><td>
                                <select name="codice-2">
                                    <c:forEach items="${requestScope.nodi}" var="nodo">
                                        <c:set var="codice_nodo" value="${nodo.getCodice()}" />
                                        <c:set var="id_nodo" value="${nodo.getID()}" />
                                        <option value="${id_nodo}" data-mappa="${nodo.getID_mappa()}">
                                            ${codice_nodo}</option>
                                    </c:forEach>
                                    <c:forEach items="${requestScope.pdis}" var="pdi">
                                        <c:set var="codice_pdi" value="${pdi.getCodice()}" />
                                        <c:set var="id_pdi" value="${pdi.getID()}" />
                                        <option value="${id_pdi}" data-mappa="${pdi.getID_mappa()}">
                                            ${codice_pdi}</option>
                                    </c:forEach>
                                </select></td></tr>
                            <tr><td>Codice Beacon:</td><td>
                                <select name="codice-beacon">
                                    <c:forEach items="${requestScope.al_beacon}" var="beacon">
                                        <c:set var="codice_beacon" value="${beacon.getCodice()}" />
                                        <c:set var="id_beacon" value="${beacon.getID_beacon()}" />
                                        <option value="${id_beacon}">${codice_beacon}</option>
                                    </c:forEach>
                                </select></td></tr>
                        </table>
                        <!-- Bottoni per tornare alla schermata precedente o per aggiornare le modifiche -->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                Annulla</button>
                            <input class="btn btn-outline-success" type='submit' 
                                style="font-weight: bold" value='Conferma modifiche'>
                            <input type="hidden" name="id_collegamento" value="">
                            <input type="hidden" name="azione" value="modifica-collegamento">
                            <input type="hidden" name="nm" value="${quota}">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>   
</div>
