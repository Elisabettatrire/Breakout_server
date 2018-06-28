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
                    <form>
                        <table class="table table-borderless">
                            <tr><td>Codice Nodo 1:</td><td>
                                <select name="codice-1">
                                    <option selected disabled hidden>Seleziona</option>
                                    <option>150G1G2</option>
                                    <option>150G2</option>
                                </select></td></tr>
                            <tr><td>Codice Nodo 2:</td><td>
                                <select name="codice-2">
                                    <option selected disabled hidden>Seleziona</option>
                                    <option>150G1G2</option>
                                    <option>150G2</option>
                                </select></td></tr>                               
                        </table>
                        <!-- Bottoni per tornare alla schermata precedente o per aggiornare le modifiche -->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                Annulla</button>
                            <input class="btn btn-outline-success" type='submit' 
                                style="font-weight: bold" value='Conferma modifiche' name='conferma-modifiche'>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>   
</div>
