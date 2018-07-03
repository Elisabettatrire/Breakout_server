<%-- 
    Document   : modifica-piano
    Created on : 28-giu-2018, 10.16.50
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!-- Modal Form Modifica Piano -->
<div id="modal-mod-piano" method="post" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Modifica piano</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="col-md-12">
                    <p class="warning">
                        I campi non compilati manterrano il valore precedente.</p>
                </div>
                <div class="col-md-12" style="text-align: center">
                    <form action="DBModify" method="post" id="form-mod-piano">
                        <table class='table table-borderless'><tr><td>Quota:</td>
                                <td><input autofocus="true" type='text' name='quota'
                                           maxlength="15" size="30"
                                           placeholder="&nbsp;(invariato)">
                        </td></table>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                            <input class="btn btn-outline-success" type='submit' 
                                   style="font-weight: bold" value='Applica modifiche' name='modifica-piano'>
                            <input type="hidden" name="id_piano" value="">
                            <input type="hidden" name="azione" value="modifica-piano">
                            <input type="hidden" name="modalita" value="mappe">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End Modal Form Modifica Piano -->