<%-- 
    Document   : aggiungi-mappa
    Created on : 28-giu-2018, 11.12.18
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!-- Modal Form Aggiungi Mappa -->
<div id="modal-aggiungi-mappa" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Aggiungi mappa</h5>
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
                <div class="col-md-12">
                    <form action="DBModify" method="post">
                        <table class='table table-borderless'>
                            <tr><td>Nome:</td>
                            <td><input autofocus="true" type='text' name='nome-mappa'
                                           size="30" placeholder='&nbsp;es. Q155/2'
                                           maxlength="15" required>&nbsp;
                                <i data-toggle="tooltip" data-placement="top"
                                    title="Sono ammessi solamente caratteri alfanumerici, l'underscore, il trattino  e lo slash."
                                    class="fas fa-question-circle" style="color: #007bff;"></i>
                            </td></tr>
                        </table>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                            <input class="btn btn-outline-success" type='submit' 
                                   style="font-weight: bold" value='Aggiungi mappa'>
                            <input type="hidden" name="azione" value="aggiungi-mappa">
                            <input type="hidden" name="nm" value="${quota}">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>