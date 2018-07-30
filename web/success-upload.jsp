<%-- 
    Document   : success-upload
    Created on : 27-lug-2018, 11.16.20
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!-- Modal Caricamento Avvenuto con Successo -->
<div id="success-upload" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Operazione avvenuta con successo</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="col-md-12">
                    <p class="warning">
                        Il caricamento dei dati relativi ai beacon è stato portato 
                    a termine con successo, è possibile verificarlo nelle pagine di gestione
                    dei beacon delle singole mappe.</p>
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