<%-- 
    Document   : failed-upload
    Created on : 30-lug-2018, 12.06.00
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!-- Modal Caricamento Fallito -->
<div id="failed-upload" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Impossibile completare l'operazione</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="col-md-12">
                    <p class="warning">
                        Non è stato possibile caricare i dati relativi ai beacon,
                    controllare che tutti i valori all'interno del file CSV rispettino
                    i requisiti specificati nella finestra di caricamento.</p>
                </div>
                <div class="col-md-12">
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">OK</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>