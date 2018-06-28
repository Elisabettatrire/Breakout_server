<%-- 
    Document   : modifica-mappa
    Created on : 28-giu-2018, 11.12.26
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!-- Modal Form Modifica Mappa -->
<div id="modal-modifica-mappa" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Modifica mappa</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="col-md-12">
                    <form action="DBModify" method="post" id="form-mod-mappa">
                        <table class='table table-borderless'>
                            <tr><td>Nome:</td>
                            <td><input autofocus="true" type='text' name='nome-mappa'
                                       size="30" placeholder='&nbsp;(invariato)'>&nbsp;
                                <i data-toggle="tooltip" data-placement="top"
                                    title="Sono ammessi solamente caratteri alfanumerici, l'underscore, il trattino  e lo slash."
                                    class="fas fa-question-circle" style="color: #007bff;"></i>
                            </td></tr>
                            <tr><td>Immagine:</td>
                                <td><input type="file" name="url-immagine"></td></tr>
                        </table>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                            <input class="btn btn-outline-success" type='submit' 
                                   style="font-weight: bold" value='Conferma modifiche' name='modifica-mappa'>
                            <input type="hidden" name="id_mappa" value="">
                            <input type="hidden" name="azione" value="modifica-mappa">
                            <input type="hidden" name="nm" value="${quota}">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
