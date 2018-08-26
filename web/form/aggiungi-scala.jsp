<%-- 
    Document   : aggiungi-scala
    Created on : 28-giu-2018, 10.18.35
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!-- Modal Form Aggiungi Scala -->
<div id="modal-agg-scala" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
            <h5 class="modal-title">Aggiungi scala</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            </div>
            <div class="modal-body">
                <div class="col-md-12">
                    <p class="warning">
                        Devono essere compilati tutti i campi, altrimenti l'inserimento
                        non verrà portato a termine.</p>
                </div>
                <hr>
                <div  class="col-md-12">
                    <!-- Le opzioni delle select contengono dei metadati per poterle
                    disabilitare tramite JS -->
                    <form action="DBModify" method="post">
                        <table class="table table-borderless">
                            <tr><td>Codice Nodo 1:</td><td>
                                <select name="codice-1" required>
                                    <option selected hidden value="">Seleziona</option>
                                    <c:forEach items="${requestScope.nodi}" var="nodo">
                                        <c:set var="codice_nodo" value="${nodo.getCodice()}" />
                                        <c:set var="id_nodo" value="${nodo.getID()}" />
                                        <option value="${id_nodo}" data-piano="${nodo.getID_piano()}">
                                            ${codice_nodo}</option>
                                    </c:forEach>
                                    <c:forEach items="${requestScope.pdis}" var="pdi">
                                        <c:set var="codice_pdi" value="${pdi.getCodice()}" />
                                        <c:set var="id_pdi" value="${pdi.getID()}" />
                                        <option value="${id_pdi}" data-piano="${pdi.getID_piano()}"
                                                data-mappa="${pdi.getID_mappa()}">${codice_pdi}</option>
                                    </c:forEach>
                                </select></td></tr>
                            <tr><td>Codice Nodo 2:</td><td>
                                <select name="codice-2" required>
                                    <option selected hidden value="">Seleziona</option>
                                    <c:forEach items="${requestScope.nodi}" var="nodo">
                                        <c:set var="codice_nodo" value="${nodo.getCodice()}" />
                                        <c:set var="id_nodo" value="${nodo.getID()}" />
                                        <option value="${id_nodo}" data-piano="${nodo.getID_piano()}"
                                                data-mappa="${nodo.getID_mappa()}">${codice_nodo}</option>
                                    </c:forEach>
                                    <c:forEach items="${requestScope.pdis}" var="pdi">
                                        <c:set var="codice_pdi" value="${pdi.getCodice()}" />
                                        <c:set var="id_pdi" value="${pdi.getID()}" />
                                        <option value="${id_pdi}" data-piano="${pdi.getID_piano()}"
                                                data-mappa="${pdi.getID_mappa()}">${codice_pdi}</option>
                                    </c:forEach>
                                </select></td></tr>
                            <tr><td>Indirizzo Beacon:</td><td>
                                <select name="codice-beacon" required>
                                    <option selected hidden value="">Seleziona</option>
                                    <c:forEach items="${requestScope.al_beacon}" var="beacon">
                                        <c:set var="codice_beacon" value="${beacon.getCodice()}" />
                                        <c:set var="id_beacon" value="${beacon.getID_beacon()}" />
                                        <option value="${id_beacon}">${codice_beacon}</option>
                                    </c:forEach>
                                </select></td></tr>
                            <tr><td>Lunghezza (m):</td>
                                <td><input type="text" name="lunghezza" placeholder=" es. 6.3"
                                            size="30" maxlength="15" required></td></tr>
                        </table>
                        <!-- Bottoni per tornare alla schermata precedente o per aggiungere la scala -->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                Annulla</button>
                            <input class="btn btn-outline-success" type='submit'
                                   style="font-weight: bold" value='Aggiungi scala'>
                            <input type="hidden" name="azione" value="aggiungi-scala">
                            <input type="hidden" name="modalita" value="mappe">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End Modal Form Aggiungi Scala -->