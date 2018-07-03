<%-- 
    Document   : modifica-nodo
    Created on : 28-giu-2018, 11.00.01
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!-- Modal Form Modifica Nodo-->
<div id="modal-mod-nodo" class="modal fade">
    <div class="modal-dialog " >
        <div class="modal-content ">
            <div class="modal-header" >
                <h5 class="modal-title">Modifica nodo</h5>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- text area per modificare i dati dei nodi caricati -->
            <div class="modal-body">
                <div  class="col-md-12">
                    <form action="DBModify" method="post" id="form-mod-nodo">
                        <table class="table table-borderless" style="margin-left: auto; margin-right: auto">
                            <tr><td>Codice:</td><td>
                                    <input type="text" name="codice" maxlength="15" size="30"
                                           placeholder="&nbsp;(invariato)" autofocus="true"></td></tr>
                            <tr><td>Coord. X:</td><td>
                                    <input type="text" name="coord-x" maxlength="15" size="30"
                                            autofocus="true" placeholder="&nbsp;(invariato)"></td></tr>
                            <tr><td>Coord. Y:</td><td>
                                    <input type="text" name="coord-y" maxlength="15" size="30"
                                                placeholder="&nbsp;(invariato)"></td></tr>
                            <tr><td>Larghezza (m):</td>
                                <td><input type="text" name="larghezza" maxlength="15" size="30"
                                                placeholder="&nbsp;(invariato)"></td></tr>
                        </table>
                        <!-- Bottoni per tornare alla schermata precedente o per aggiornare le modifiche-->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                Annulla</button>
                            <input class="btn btn-outline-success" type='submit' 
                                style="font-weight: bold" value='Conferma modifiche'>
                            <input type="hidden" name="id_nodo" value="">
                            <input type="hidden" name="azione" value="modifica-nodo">
                            <input type="hidden" name="nm" value="${nome_mappa}">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    </div>