<%-- 
    Document   : aggiungi-pdi
    Created on : 28-giu-2018, 11.00.01
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!-- Modal Form Aggiungi PDI -->
<div id="modal-agg-pdi" class="modal fade">
    <div class="modal-dialog ">
        <div class="modal-content ">
            <div class="modal-header" >
                <h5 class="modal-title">Aggiungi PDI</h5>
                <button type="button" class="close" data-dismiss="modal">&times;</button> 
            </div>
            <!-- text area per inserire i dati dei nodi da caricare -->
            <div class="modal-body">
                <div class="col-md-12">
                    <p class="warning">
                        Devono essere compilati tutti i campi, altrimenti l'inserimento
                        non verrà portato a termine.</p>
                </div>
                <hr>
                <div  class="col-md-12">
                    <form action="DBModify" method="post">
                        <table class="table table-borderless">
                            <tr><td>Codice:</td><td>
                                    <input type="text" name="codice" placeholder=" es. 155DICEA"
                                           size="30" autofocus="true" required maxlength="15"></td></tr>
                            <tr><td>Coord. X (m):</td>
                                <td><input type="text" name="coord-x" placeholder=" es. 12.3"
                                           size="30" required></td></tr>
                            <tr><td>Coord. Y (m):</td><td>
                                    <input type="text" name="coord-y" placeholder=" es. 4.6"
                                           size="30" maxlength="15" required></td></tr>
                            <tr><td>Lunghezza (m):</td><td>
                                    <input type="text" name="lunghezza" placeholder=" es. 2.5"
                                           size="30" maxlength="15" required></td></tr>
                            <tr><td>Larghezza (m):</td><td>
                                    <input type="text" name="larghezza" placeholder=" es. 3"
                                           size="30" maxlength="15" required></td></tr>
                            <tr><td>Descrizione:</td><td>
                                    <textarea rows="2" cols="32" name="tipo"
                                        required placeholder=" Breve descrizione"
                                        style="resize: none" maxlength="70">
                                    </textarea></td></tr>
                        </table>
                        <!-- Bottoni per tornare alla schermata precedente o per aggiungere il PDI-->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                            <input class="btn btn-outline-success" type='submit' 
                                style="font-weight: bold" value='Aggiungi PDI'>
                            <input type="hidden" name="azione" value="aggiungi-pdi">
                            <input type="hidden" name="nm" value="${nome_mappa}">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>