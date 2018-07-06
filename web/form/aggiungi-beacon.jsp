<%-- 
    Document   : aggiungi-beacon
    Created on : 28-giu-2018, 10.56.54
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!-- Modal Form Aggiungi Beacon -->
<div id="modal-beacon" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Aggiungi Beacon</h5>
                <button type="button" class="close" data-dismiss="modal">&times;</button> 
            </div>
            <div class="modal-body">
                <div class="col-md-12">
                    <p class="warning">
                        Devono essere compilati tutti i campi, altrimenti l'inserimento
                        non verrà portato a termine.</p>
                </div>
                <!-- text area per inserire i dati dei nodi da caricare -->
                <div class="col-md-12">
                    <form action="DBModify" method="post">
                        <table class="table table-borderless">
                            <tr><td>Codice:</td><td>
                                    <input type="text" name="codice" placeholder=" es. 150G2"
                                           size="30" autofocus="true" maxlength="15" required></td></tr>
                            <tr><td>Coord. X:</td><td>
                                    <input type="text" name="coord-x" required
                                            placeholder=" es. 129" size="30" maxlength="15"></td></tr>
                            <tr><td>Coord. Y:</td><td>
                                    <input type="text" name="coord-y" required
                                            placeholder=" es. 465" size="30" maxlength="15"></td></tr>
                            <tr><td>Fuoco?:</td><td>
                                    <input type="text" name="fuoco" placeholder=""
                                            size="30" maxlength="15" required></td></tr>
                            <tr><td>Fumi?:</td><td>
                                    <input type="text" name="fumi" placeholder=""
                                            size="30" maxlength="15" required></td></tr>
                            <tr><td>NDC?:</td><td>
                                    <input type="text" name="ndc" placeholder=""
                                            size="30" maxlength="15" required></td></tr>
                            <tr><td>Rischio?:</td><td>
                                    <input type="text" name="rischio" placeholder=""
                                            size="30" maxlength="15" required></td></tr>
                            <tr><td>PDI associato</td><td>
                                    <select name="codice-pdi">
                                        <option value="nessuno" selected>Nessuno</option>
                                        <c:forEach items="${requestScope.al_pdi}" var="pdi">
                                            <c:set var="codice_pdi" value="${pdi.getCodice()}" />
                                            <c:set var="id_pdi" value="${pdi.getID()}" />
                                            <option value="${id_pdi}">${codice_pdi}</option>
                                    </c:forEach>
                                    </select>
                                </td>
                        </table>
                        <!-- Bottoni per tornare alla schermata precedente o per aggiungere il nodo -->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                Annulla</button>
                            <input class="btn btn-outline-success" type='submit' 
                                style="font-weight: bold" value='Aggiungi beacon'>
                            <input type="hidden" name="azione" value="aggiungi-beacon">
                            <input type="hidden" name="nm" value="${nome_mappa}">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
