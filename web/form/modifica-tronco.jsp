<%-- 
    Document   : modifica-tronco
    Created on : 28-giu-2018, 11.00.01
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!-- Modal Form Modifica Tronco -->
<div id="modal-mod-tronco" class="modal fade">
    <div class="modal-dialog ">
        <div class="modal-content ">
            <div class="modal-header" >
                <h5 class="modal-title">Modifica tronco</h5>
                <button type="button" class="close" data-dismiss="modal">&times;</button> 
            </div>
            <!-- text area per modificare i dati dei tronchi caricati -->
            <div class="modal-body">
                <div  class="col-md-12">
                    <form action="DBModify" method="post" id="form-mod-tronco">
                        <table class="table table-borderless">
                            <tr><td>Codice Nodo 1:</td><td>
                                <select name="codice-1">
                                    <c:forEach items="${requestScope.nodi}" var="nodo">
                                        <c:set var="codice_nodo" value="${nodo.getCodice()}" />
                                        <c:set var="id_nodo" value="${nodo.getID()}" />
                                        <option value="${id_nodo}">${codice_nodo}</option>
                                    </c:forEach>
                                    <c:forEach items="${requestScope.pdis}" var="pdi">
                                        <c:set var="codice_pdi" value="${pdi.getCodice()}" />
                                        <c:set var="id_pdi" value="${pdi.getID()}" />
                                        <option value="${id_pdi}">${codice_pdi}</option>
                                    </c:forEach>
                                </select></td></tr>
                            <tr><td>Codice Nodo 2:</td><td>
                                <select name="codice-2">
                                    <c:forEach items="${requestScope.nodi}" var="nodo">
                                        <c:set var="codice_nodo" value="${nodo.getCodice()}" />
                                        <c:set var="id_nodo" value="${nodo.getID()}" />
                                        <option value="${id_nodo}">${codice_nodo}</option>
                                    </c:forEach>
                                    <c:forEach items="${requestScope.pdis}" var="pdi">
                                        <c:set var="codice_pdi" value="${pdi.getCodice()}" />
                                        <c:set var="id_pdi" value="${pdi.getID()}" />
                                        <option value="${id_pdi}">${codice_pdi}</option>
                                    </c:forEach>
                                </select></td></tr>
                            <tr><td>Indirizzo Beacon:</td><td>
                                <select name="codice-beacon">
                                    <c:forEach items="${requestScope.al_beacon}" var="beacon">
                                        <c:set var="codice_beacon" value="${beacon.getAddress()}" />
                                        <c:set var="id_beacon" value="${beacon.getID_beacon()}" />
                                        <option value="${id_beacon}">${codice_beacon}</option>
                                    </c:forEach>
                                </select></td></tr>
                            <tr><td>Lunghezza (m):</td>
                                <td><input type="text" name="lunghezza" placeholder=" (invariato)"
                                            size="30" maxlength="15"></td></tr>
                        </table>
                        <!-- Bottoni per tornare alla schermata precedente o per aggiornare le modifiche -->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                Annulla</button>
                            <input class="btn btn-outline-success" type='submit' 
                                style="font-weight: bold" value='Conferma modifiche'>
                            <input type="hidden" name="id_tronco" value="">
                            <input type="hidden" name="azione" value="modifica-tronco">
                            <input type="hidden" name="nm" value="${nome_mappa}">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>   
</div>
