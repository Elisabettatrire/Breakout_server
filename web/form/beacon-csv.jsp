<%-- 
    Document   : beacon-csv
    Created on : 25-lug-2018, 18.29.07
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!-- Modal Form Aggiungi Beacon da CSV -->
<div id="modal-csv" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Aggiungi Beacon da file CSV</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="col-md-12">
                    <p class="warning">
                        Specifiche file csv</p>
                </div>
                <hr>
                <div class="col-md-12" style="text-align: center">
                    <form action="UploadCSV" method="post" enctype="multipart/form-data">
                        <input type="file" accept=".csv" name="csv" required>
                        <div class="modal-footer" style="margin-top: 20px">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                            <input class="btn btn-outline-success" type='submit' 
                                   style="font-weight: bold" value='Carica dati' name='carica-dati'>
                            <input type="hidden" name="modalita" value="mappe">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End Modal Form Aggiungi Beacon da CSV -->
