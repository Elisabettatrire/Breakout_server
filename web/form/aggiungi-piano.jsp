<%-- 
    Document   : aggiungi-piano
    Created on : 28-giu-2018, 10.14.29
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!-- Modal Form Aggiungi Piano -->
<div id="modal-piano" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Aggiungi piano</h5>
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
                <div class="col-md-12" style="text-align: center">
                    <form action="DBModify" method="post">
                        <table class="table table-borderless">
                            <tr><td><label>Quota:</label></td>
                                <td><input autofocus="true" type='text' name='quota'
                                           size="30" placeholder='&nbsp;es. 155' required>
                        </td></table>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                            <input class="btn btn-outline-success" type='submit' 
                                   style="font-weight: bold" value='Aggiungi piano' name='aggiungi-piano'>
                            <input type="hidden" name="azione" value="aggiungi-piano">
                            <input type="hidden" name="modalita" value="mappe">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End Modal Form Aggiungi Piano -->