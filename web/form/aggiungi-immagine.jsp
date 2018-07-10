<%-- 
    Document   : aggiungi-immagine
    Created on : 28-giu-2018, 17.55.04
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<div id="modal-aggiungi-immagine" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Aggiungi immagine mappa</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="col-md-12">
                    <p>La dimensione massima consentita è di 2MB</p>
                </div>
                <div class="col-md-12">
                    <form action="UploadImage" method="post" enctype="multipart/form-data" id="form-mod-img-mappa">
                        <table class="table-borderless">
                            <tr><td>Immagine:</td>
                                <td><input type="file" accept="image/*" name="immagine"></td></tr>
                        </table><br>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                            <input class="btn btn-outline-success" type='submit' 
                                   style="font-weight: bold" value='Aggiungi immagine'>
                            <input type="hidden" name="id_mappa" value="">
                            <input type="hidden" name="nm" value="${quota}">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
