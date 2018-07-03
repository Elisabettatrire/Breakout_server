<%-- 
    Document   : aggiungi-nodo
    Created on : 28-giu-2018, 11.00.01
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!-- Modal Form Aggiungi Nodo -->
<div id="modal-agg-nodo" class="modal fade">
    <div class="modal-dialog " >
        <div class="modal-content ">
            <div class="modal-header" >
                <h5 class="modal-title">Aggiungi nodo</h5>
                <button type="button" class="close" data-dismiss="modal">&times;</button> 
            </div>
            <div class="modal-body">
                <div class="col-md-12">
                    <p class="warning">
                        Devono essere compilati tutti i campi, altrimenti l'inserimento
                        non verrà portato a termine.</p>
                </div>
                <!-- text area per inserire i dati dei nodi da caricare -->
                <div  class="col-md-12">
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
                            <tr><td>Larghezza (m):</td><td>
                                    <input type="text" name="larghezza" placeholder=" es. 1.8"
                                            size="30" maxlength="15" required></td></tr>
                        </table>
                        <!-- Bottoni per tornare alla schermata precedente o per aggiungere il nodo -->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                            <input class="btn btn-outline-success" type='submit' 
                                style="font-weight: bold" value='Aggiungi nodo'>
                            <input type="hidden" name="azione" value="aggiungi-nodo">
                            <input type="hidden" name="nm" value="${nome_mappa}">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>