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
                    <p>L'immagine deve avere una dimensione massima di <i>2MB</i>; l'altezza massima consigliata
                    è di <i>1600px</i>, mentre la larghezza massima consigliata è di <i>1000px</i>.</p>
                </div>
                <hr>
                <div class="col-md-12">
                    <form action="UploadImage" method="post" enctype="multipart/form-data" id="form-img-mappa">
                        <table class="table-borderless">
                            <tr><td>Immagine:</td>
                                <td><input type="file" accept="image/*" name="immagine">
                                    &nbsp;&nbsp;<i data-toggle="tooltip" data-placement="top"
                                       title="Se non si seleziona alcun file verrà mantenuto quello precedente"
                                       class="fas fa-info-circle" style="color: #007bff;"></i></td></tr>
                        </table><br>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                            <input class="btn btn-outline-success" type='submit' 
                                   style="font-weight: bold" value='Aggiungi immagine'>
                            <input type="hidden" name="id_mappa" value="">
                            <input type="hidden" name="old-img" value="">
                            <input type="hidden" name="nm" value="${quota}">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
