<%-- 
    Document   : modifica-utente
    Created on : 28-giu-2018, 11.15.42
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!-- Modal Form Modifica Utente -->
<div id="modal-mod-utente" class="modal fade">
    <div class="modal-dialog " >
        <div class="modal-content ">
            <div class="modal-header" >
                <h5 class="modal-title">Modifica password utente</h5>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- text area per modificare i dati dei nodi caricati -->
            <div class="modal-body">
                <form action="DBModify" method="post" id="form-mod-piano">
                    <table class="table table-borderless">
                        <tr><td>Nuova password:</td><td><input type="password" name="psw" size="30"
                                                              autofocus="true"></td></tr>
                        <tr><td>Conferma password:</td><td><input type="password" name="psw-confirm"
                                                                 size="30"></td></tr>
                        <input type="hidden" name="id_utente" value="">
                        <input type="hidden" name="azione" value="modifica-utente">
                        <input type="hidden" name="modalita" value="utenti">
                    </table>
                    <!-- Bottoni per tornare alla schermata precedente o per aggiornare le modifiche-->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            Annulla</button>
                        <input class="btn btn-outline-success" type='submit' 
                            style="font-weight: bold" value='Conferma modifiche'
                            name='conferma-modifiche'>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
