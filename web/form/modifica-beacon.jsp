<%-- 
    Document   : modifica-beacon
    Created on : 28-giu-2018, 10.58.10
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!-- Modal Form Modifica Beacon -->
<div id="modal-mod-beacon" class="modal fade">
    <div class="modal-dialog " >
        <div class="modal-content ">
            <div class="modal-header" >
                <h5 class="modal-title">Modifica beacon</h5>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- text area per modificare i dati dei nodi caricati -->
            <div class="modal-body">
                <div class="col-md-12">
                    <form action="DBModify" method="post" id="form-mod-beacon">
                        <table class="table table-borderless">
                            <tr><td>Codice:</td><td>
                                    <input type="text" name="codice" placeholder=" es. 150G2"
                                           size="30" maxlength="15" autofocus="true"></td></tr>
                            <tr><td>Coord. X:</td><td>
                                    <input type="text" name="coord-x" maxlength="15" 
                                            placeholder=" (invariato)" size="30"></td></tr>
                            <tr><td>Coord. Y:</td><td>
                                    <input type="text" name="coord-y" maxlength="15" 
                                            placeholder=" (invariato)" size="30"></td></tr>
                            <tr><td>Fuoco?:</td><td>
                                    <input type="text" name="fuoco" placeholder=" (invariato)"
                                            maxlength="15" size="30"></td></tr>
                            <tr><td>Fumi?:</td><td>
                                    <input type="text" name="fumi" placeholder=" (invariato)"
                                            maxlength="15" size="30"></td></tr>
                            <tr><td>NDC?:</td><td>
                                    <input type="text" name="ndc" placeholder=" (invariato)"
                                            maxlength="15" size="30"></td></tr>
                            <tr><td>Rischio?:</td><td>
                                    <input type="text" name="rischio" placeholder=" (invariato)"
                                            maxlength="15" size="30"></td></tr>
                        </table>
                        <!-- Bottoni per tornare alla schermata precedente o per aggiornare le modifiche-->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                Annulla</button>
                            <input class="btn btn-outline-success" type='submit' 
                                style="font-weight: bold" value='Conferma modifiche'>
                            <input type="hidden" name="id_beacon" value="">
                            <input type="hidden" name="azione" value="modifica-beacon">
                            <input type="hidden" name="nm" value="${nome_mappa}">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>