<%-- 
    Document   : modifica-pdi
    Created on : 28-giu-2018, 11.00.01
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!-- Modal Form Modifica PDI -->
<div id="modal-mod-pdi" class="modal fade">
    <div class="modal-dialog " >
        <div class="modal-content ">
            <div class="modal-header" >
                <h5 class="modal-title">Modifica PDI</h5>
                <button type="button" class="close" data-dismiss="modal">&times;</button> 
            </div>
            <div class="modal-body">
                <div  class="col-md-12">
                    <form action="DBModify" method="post" id="form-mod-pdi">
                        <table class="table table-borderless">
                            <tr><td>Codice:</td><td>
                                    <input type="text" name="codice" size="30"
                                           placeholder=" (invariato)" autofocus="true"></td></tr>
                            <tr><td>Coord. X (cm):</td>
                                <td><input type="text" name="coord-x" size="30"
                                           maxlength="15" placeholder=" (invariato)"></td></tr>
                            <tr><td>Coord. Y (cm):</td><td>
                                    <input type="text" name="coord-y" size="30"
                                           maxlength="15" placeholder=" (invariato)"></td></tr>
                            <tr><td>Lunghezza (m):</td><td>
                                    <input type="text" name="lunghezza" size="30"
                                           maxlength="15" placeholder=" (invariato)"></td></tr>
                            <tr><td>Larghezza (m):</td><td>
                                    <input type="text" name="larghezza" size="30"
                                           maxlength="15" placeholder=" (invariato)"></td></tr>
                            <tr><td>Tipo:</td><td>
                                <textarea rows="2" cols="32" name="tipo"
                                          placeholder=" (invariato)" maxlength="70"
                                          style="resize: none"></textarea></td></tr>
                        </table>
                        <!-- Bottoni per tornare alla schermata precedente o per aggiornare il PDI-->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-dark" data-dismiss="modal">
                                Annulla</button>
                            <input class="btn btn-outline-success" type='submit' 
                                style="font-weight: bold" value='Conferma modifiche'>
                            <input type="hidden" name="id_pdi" value="">
                            <input type="hidden" name="azione" value="modifica-pdi">
                            <input type="hidden" name="nm" value="${nome_mappa}">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
