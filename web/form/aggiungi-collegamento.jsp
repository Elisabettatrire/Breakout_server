<%-- 
    Document   : aggiungi-collegamento
    Created on : 28-giu-2018, 11.10.41
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!-- Modal Form Aggiungi Collegamento -->
<div id="agg-coll" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Aggiungi Collegamento</h5>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <div class="col-md-12">
                    <p class="warning">
                        Devono essere compilati tutti i campi, altrimenti l'inserimento
                        non verrà portato a termine.</p>
                </div>
                <div class="col-md-12">
                    <form>
                        <!-- Selezione beacon e tronchi che devono essere collegati-->
                        <table class='table table-borderless'>
                            <tr><td>Seleziona il beacon:</td>
                                <td><select name='nome-beacon' required>
                                    <option selected disabled hidden>Seleziona</option>
                                    <option>Beacon B1</option>
                                    <option>Beacon B2</option>
                                    <option>Beacon B3</option>
                                    <option>Beacon B4</option>
                                    <option>Beacon B5</option>
                                    <option>Beacon B4</option>
                                    <option>Beacon B5</option>
                                    </select></td></tr>
                            <tr><td>Seleziona il tronco:</td>
                                <td><select name='codice-tronco' required>
                                    <option selected disabled hidden>Seleziona</option>
                                    <option>T150N1</option>
                                    <option>T150N2</option>
                                    <option>T150N3</option>
                                    <option>T150N4</option>
                                    <option>T150N5</option>
                                    </select></td></tr>
                        </table>
                        <div class="modal-footer">
                        <!-- Bottoni per tornare alla schermata precedente o per aggiornare le modifiche-->
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                Annulla
                            </button>
                            <input class="btn btn-outline-success" type='submit' 
                                style="font-weight: bold" value='Aggiungi collegamento' name='aggiungi-collegamento'>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
