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
                <div  class="col-md-12">
                    <form action="DBModify" method="post">
                        <table class="table table-borderless">
                            <tr><td>Codice:</td><td>
                                    <input type="text" name="codice" placeholder="Inserisci Codice"
                                           size="30" autofocus="true" required></td></tr>
                            <tr><td>Coord. X:</td>
                                <td><input type="text" name="coord-x" placeholder=" es."
                                           size="30" required></td></tr>
                            <tr><td>Coord. Y:</td><td>
                                    <input type="text" name="coord-y" placeholder=" es."
                                           size="30" required></td></tr>
                            <tr><td>Lunghezza:</td><td>
                                    <input type="text" name="lunghezza" placeholder=" es."
                                           size="30" required></td></tr>
                            <tr><td>Larghezza:</td><td>
                                    <input type="text" name="larghezza" placeholder=" es."
                                           size="30" required></td></tr>
                            <tr><td>Tipo:</td><td>
                                    <select name='tipo' required>
                                        <option selected value="Aula">Aula</option>
                                        <option value="Punto Di Ritrovo">Punto di Ritrovo</option>
                                    </select></td></tr>
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