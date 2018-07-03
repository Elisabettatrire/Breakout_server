<%-- 
    Document   : aggiungi-scala
    Created on : 28-giu-2018, 10.18.35
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!-- Modal Form Aggiungi Scala -->
<div id="modal-scala" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
            <h5 class="modal-title">Aggiungi scala</h5>
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
                    <form action='#'>
                        <table class='table table-borderless'>
                            <tr><td>Lunghezza (m):</td>
                                <td><input type='text' name='lunghezza' size="30" required
                                    autofocus="true" maxlength="15" placeholder='&nbsp;es. 25'></td>
                            <tr><td>Cod. Nodo 1:</td><td><input type='text' required
                                    placeholder='&nbsp;es. 145UA5' name='nodo_1' size="30"></td>
                            <tr><td>Cod. Nodo 2:</td><td><input type='text' required
                                    placeholder='&nbsp;es. 145DICEA' name='nodo_1' size="30"></td>
                        </table>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                            <input class="btn btn-outline-success" type='submit'
                                   style="font-weight: bold" value='Aggiungi scala' name='aggiungi-scala'>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End Modal Form Aggiungi Scala -->