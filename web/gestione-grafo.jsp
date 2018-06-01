<%-- 
    Document   : gestione-grafo
    Created on : 22-mag-2018, 17.00.09
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it-IT">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Breakout - Gestione Grafo</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap-grid.css" type="text/css">
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap-grid.min.css" type="text/css">
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap-reboot.css" type="text/css">
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap-reboot.min.css" type="text/css">
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap.css" type="text/css">
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="static/fontawesome/fontawesome-all.css">
        <script src="static/bootstrap-4.1.1-dist/js/bootstrap.bundle.js"></script>
        <script src="static/bootstrap-4.1.1-dist/js/bootstrap.bundle.min.js"></script>
        <script src="static/bootstrap-4.1.1-dist/js/bootstrap.js"></script>
        <script src="static/bootstrap-4.1.1-dist/js/bootstrap.min.js"></script>
        <script src="static/bootstrap-4.1.1-dist/js/bootbox.min.js"></script>
    </head>
    <body>
        <!-- Header -->
        <%@include file="header.html" %>

        <!-- Page Content -->
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <h2>Mappa Q150\1 - Gestione Grafo</h2>
                    <br><br>
                    <!-- Tabella dei Nodi della mappa -->
                    <h4>Lista Nodi</h4>
                    <table class="table table-bordered table-striped" style="text-align: center">
                        <thead>
                            <tr><th>Coord_X</th><th>Coord_Y</th><th>Larghezza</th>
                                <th>Codice</th><th>Modifica</th><th>Elimina</th>
                        </thead>
                        <tr><td>129</td><td>465</td><td>1.8</td><td>150G2</td>
                            <td><button id="mod-150g2" class="btn btn-outline-dark btn-sm"
                                    data-toggle="modal" data-target="#modal-mod-nodo">
                                    <span class="fas fa-cog"></span></button></td>
                            <td><button id="rm-150g2" class="btn btn-outline-danger btn-sm"
                                    data-toggle="modal" data-target="#modal-elimina-nodo">
                            <span class="fas fa-trash-alt"></span></button></td></tr>
                        <tr><td>110</td><td>465</td><td>1.8</td><td>150G1G2</td>
                            <td><button id="mod-150g1g2" class="btn btn-outline-dark btn-sm"
                                    data-toggle="modal" data-target="#modal-mod-nodo">
                                    <span class="fas fa-cog"></span></button></td>
                            <td><button id="rm-150g1g2" class="btn btn-outline-danger btn-sm"
                                    data-toggle="modal" data-target="#modal-elimina-nodo">
                            <span class="fas fa-trash-alt"></span></button></td></tr>
                    </table>
                    <div style="text-align: right">
                        <button type="button" class="btn btn-outline-success"
                                data-toggle="modal" data-target="#modal-agg-nodo">
                            <b>Aggiungi nodo</b>
                        </button>
                    </div>
                    <hr>
                    
                    <!-- Tabella dei Tronchi della mappa -->
                    <h4>Lista Tronchi</h4>
                    <table  class="table table-bordered table-striped" style="text-align: center">
                        <thead>
                            <tr>
                                <th>Lunghezza</th><th>Cod. Nodo 1</th><th>Cod. Nodo 2</th>
                                <th>Codice</th><th>Modifica</th><th>Elimina</th>
                            </tr>
                        </thead>
                        <tr><td>19</td><td>150G2</td><td>150G1G2</td><td>T1501</td>
                            <td><button id="mod-t1501" class="btn btn-outline-dark btn-sm"
                                    data-toggle="modal" data-target="#modal-mod-tronco">
                                    <span class="fas fa-cog"></span></button></td>
                            <td><button id="rm-t1501" class="btn btn-outline-danger btn-sm"
                                    data-toggle="modal" data-target="#modal-elimina-tronco">
                            <span class="fas fa-trash-alt"></span></button></td></tr>    
                    </table>
                    <div style="text-align: right">
                        <button type="button" class="btn btn-outline-success"
                                data-toggle="modal" data-target="#modal-agg-tronco">
                            <b>Aggiungi tronco</b>
                        </button>
                    </div>
                    <hr>
                </div>
                     
                <!-- Immagine della mappa fs:990x1572 -->
                <div class="col-md-4" >  
                    <img src="/Immagini/150_color.jpg" width="396" height="630">
                </div>
            </div>
            
            <!-- Tabella dei Punti di Interesse-->
            <br><br>
            <h4>Punti di interesse</h4>            
            <table class="table table-bordered table-striped" style="text-align: center">
                <thead>
                    <tr>
                        <th>Coord_X</th><th>Coord_Y</th><th>Larghezza</th><th>Lunghezza</th><th>Tipo</th>
                        <th>Descrizione</th><th>Codice</th><th>Modifica</th><th>Elimina</th>
                    </tr>
                </thead>
                <tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                    <td><button id="mod" class="btn btn-outline-dark btn-sm"
                            data-toggle="modal" data-target="#modal-mod-pdi">
                            <span class="fas fa-cog"></span></button></td>
                    <td><button id="rm" class="btn btn-outline-danger btn-sm"
                            data-toggle="modal" data-target="#modal-elimina-pdi">
                            <span class="fas fa-trash-alt"></span></button></td></tr>
            </table>
            <div style="text-align: right">
                <button type="button" class="btn btn-outline-success"
                    data-toggle="modal" data-target="#modal-agg-pdi">
                    <b>Aggiungi PDI</b>
                </button>
            </div>
            <br>
            <div class="row">
                <button type="button" class="btn btn-secondary">
                    < Indietro
                </button>
            </div>
        </div>
        
        <!-- Finestra popup aggiunta di un nodo -->
        <div id="modal-agg-nodo" class="modal fade">
            <div class="modal-dialog " >
                <div class="modal-content ">
                    <div class="modal-header" >
                        <h5 class="modal-title">Aggiungi nodo</h5>
                        <button type="button" class="close" data-dismiss="modal">&times;</button> 
                    </div>
                    <div class="modal-body">
                        <!-- text area per inserire i dati dei nodi da caricare -->
                        <form>
                            <table class="table table-borderless">
                                <tr><td>Coord_X</td><td><input type="text" name="coord-x"
                                                                placeholder=" es. 129" value="" size="40"></td></tr>
                                <tr><td>Coord_Y</td><td><input type="text" name="coord-y"
                                                               placeholder=" es. 465" value="" size="40"></td></tr>
                                <tr><td>Larghezza</td><td><input type="text" name="larghezza" placeholder=" es. 1.8"
                                                                value="" size="40"></td></tr>
                                <tr><td>Codice</td><td><input type="text" name="codice" placeholder=" es. 150G2"
                                                                value="" size="40"></td></tr>
                            </table>
                            <!-- Bottoni per tornare alla schermata precedente o per aggiungere il nodo -->
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                    Annulla
                                </button>
                                <input class="btn btn-outline-success" type='submit' 
                                    style="font-weight: bold" value='Aggiungi nodo' name='aggiungi-nodo'>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Finestra popup per la modifica di un nodo-->
        <div id="modal-mod-nodo" class="modal fade">
            <div class="modal-dialog " >
                <div class="modal-content ">
                    <div class="modal-header" >
                        <h5 class="modal-title">Modifica nodo</h5>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <!-- text area per modificare i dati dei nodi caricati -->
                    <div class="modal-body">
                        <form>
                            <table class="table table-borderless">
                                <tr><td>Coord_X</td><td><input type="text" name="coord-x" size="40"></td></tr>
                                <tr><td>Coord_Y</td><td><input type="text" name="coord-y" size="40"></td></tr>
                                <tr><td>Larghezza</td><td><input type="text" name="larghezza"size="40"></td></tr>
                                <tr><td>Codice</td><td><input type="text" name="codice" size="40"></td></tr>
                            </table>
                            <!-- Bottoni per tornare alla schermata precedente o per aggiornare le modifiche-->
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                    Annulla</button>
                                <input class="btn btn-outline-success" type='submit' 
                                    style="font-weight: bold" value='Conferma modifiche' name='conferma-modifiche'>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Modal Conferma Eliminazione Nodo -->
        <div id="modal-elimina-nodo" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                    <h5 class="modal-title">Conferma eliminazione nodo</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    </div>
                    <div class="modal-body">
                        <form action='#'>
                            <p>Sicuro di voler rimuovere il nodo selezionato?
                                Questa azione non può essere annullata.</p>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                                <input class="btn btn-danger" type='submit' value='Elimina' name='elimina-nodi'>
                            </div>
                        </form>                    
                    </div>
                </div>
            </div>
        </div>
           
        <!-- Finestra popup aggiunta di un tronco-->
        <div id="modal-agg-tronco" class="modal fade">
            <div class="modal-dialog " >
                <div class="modal-content ">
                    <div class="modal-header" >
                        <h5 class="modal-title">Aggiungi tronco</h5>
                        <button type="button" class="close" data-dismiss="modal">&times;</button> 
                    </div>
                    <!-- text area per inserire i dati del tronco da caricare -->
                    <div class="modal-body">
                        <form>
                            <table class="table table-borderless">
                                <tr><td>Codice Nodo 1</td><td>
                                    <select name="codice-1">
                                        <option value="" selected disabled hidden>Seleziona</option>
                                        <option>150G1G2</option>
                                        <option>150G2</option>
                                    </select></td></tr>
                                <tr><td>Codice Nodo 2</td><td>
                                    <select name="codice-2">
                                        <option value="" selected disabled hidden>Seleziona</option>
                                        <option>150G1G2</option>
                                        <option>150G2</option>
                                    </select></td></tr> 
                            </table>
                            <!-- Bottoni per tornare alla schermata precedente o per aggiungere il tronco -->
                            <div class="modal-footer">
                                <button type="button" class="btn btn-dark" data-dismiss="modal">
                                    Annulla</button>
                                <input class="btn btn-outline-success" type='submit' 
                                    style="font-weight: bold" value='Aggiungi tronco' name='aggiungi-tronco'>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Finestra popup per la modifica di un tronco -->
        <div id="modal-mod-tronco" class="modal fade">
            <div class="modal-dialog ">
                <div class="modal-content ">
                    <div class="modal-header" >
                        <h5 class="modal-title">Modifica tronco</h5>
                        <button type="button" class="close" data-dismiss="modal">&times;</button> 
                    </div>
                    <!-- text area per modificare i dati dei tronchi caricati -->
                    <div class="modal-body">
                        <form>
                            <table class="table table-borderless " style="text-align: center">
                                <tr><td>Codice Nodo 1</td><td>
                                    <select name="codice-1">
                                        <option>150G1G2</option>
                                        <option>150G2</option>
                                    </select></td></tr>
                                <tr><td>Codice Nodo 2</td><td>
                                    <select name="codice-2">
                                        <option>150G1G2</option>
                                        <option>150G2</option>
                                    </select></td></tr>                               
                            </table>
                            <!-- Bottoni per tornare alla schermata precedente o per aggiornare le modifiche -->
                            <div class="modal-footer">
                                <button type="button" class="btn btn-dark" data-dismiss="modal">
                                    Annulla</button>
                                <input class="btn btn-outline-success" type='submit' 
                                    style="font-weight: bold" value='Conferma modifiche' name='conferma-modifiche'>
                            </div>
                        </form>
                    </div>
                </div>
            </div>   
        </div>
        
        <!-- Modal Conferma Eliminazione Tronco -->
        <div id="modal-elimina-tronco" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                    <h5 class="modal-title">Conferma eliminazione tronchi</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    </div>
                    <div class="modal-body">
                        <form action='#'>
                            <p>Sicuro di voler rimuovere i tronchi selezionati?
                                Questa azione non può essere annullata.</p>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                                <input class="btn btn-danger" type='submit' value='Elimina' name='elimina-tronchi'>
                            </div>
                        </form>                    
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Finestra popup aggiunta di un PDI-->
        <div id="modal-agg-pdi" class="modal fade">
            <div class="modal-dialog ">
                <div class="modal-content ">
                    <div class="modal-header" >
                        <h5 class="modal-title">Aggiungi PDI</h5>
                        <button type="button" class="close" data-dismiss="modal">&times;</button> 
                    </div>
                    <!-- text area per inserire i dati dei nodi da caricare -->
                    <div class="modal-body">
                        <form>
                            <table class="table table-borderless " style="text-align: center">
                                <tr><td>Coord_X</td>
                                    <td><input type="text" name="coord-x" placeholder=" es."
                                               value="" size="40"></td></tr>
                                <tr><td>Coord_Y</td><td>
                                        <input type="text" name="coord-y" placeholder=" es."
                                               value="" size="40"></td></tr>
                                <tr><td>Lunghezza</td><td>
                                        <input type="text" name="lunghezza" placeholder=" es."
                                               value="" size="40"></td></tr>
                                <tr><td>Larghezza</td><td>
                                        <input type="text" name="larghezza" placeholder=" es."
                                               value="" size="40"></td></tr>
                                <tr><td>Tipo</td><td>
                                        <select name='tipo'>
                                            <option value="" selected disabled hidden>Seleziona</option>
                                            <option>Aula</option>
                                            <option>Punto di Ritrovo</option>
                                        </select></td></tr>
                                <tr><td>Descrizione</td><td>
                                        <input type="text" name="descrizione" placeholder="Descrizione"
                                               value="" size="40"></td></tr>
                                <tr><td>Codice</td><td>
                                        <input type="text" name="codice" placeholder="Inserisci Codice"
                                               value="" size="40"></td></tr>
                            </table>
                            <!-- Bottoni per tornare alla schermata precedente o per aggiungere il PDI-->
                            <div class="modal-footer">
                                <button type="button" class="btn btn-dark" data-dismiss="modal">
                                    Annulla</button>
                                <input class="btn btn-outline-success" type='submit' 
                                    style="font-weight: bold" value='Aggiungi PDI' name='aggiungi-pdi'>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Finestra popup per la modifica di un PDI-->
        <div id="modal-mod-pdi" class="modal fade">
            <div class="modal-dialog " >
                <div class="modal-content ">
                    <div class="modal-header" >
                        <h5 class="modal-title">Modifica PDI</h5>
                        <button type="button" class="close" data-dismiss="modal">&times;</button> 
                    </div>
                    <div class="modal-body">
                        <form>
                            <table class="table table-borderless " style="text-align: center">
                                <tr><td>Coord_X</td>
                                    <td><input type="text" name="coord-x" size="40"></td></tr>
                                <tr><td>Coord_Y</td><td>
                                        <input type="text" name="coord-y" size="40"></td></tr>
                                <tr><td>Lunghezza</td><td>
                                        <input type="text" name="lunghezza" size="40"></td></tr>
                                <tr><td>Larghezza</td><td>
                                        <input type="text" name="larghezza" size="40"></td></tr>
                                <tr><td>Tipo</td><td>
                                        <select name='tipo'>
                                            <option>Aula</option>
                                            <option>Punto di Ritrovo</option>
                                        </select></td></tr>
                                <tr><td>Descrizione</td><td>
                                        <input type="text" name="descrizione" size="40"></td></tr>
                                <tr><td>Codice</td><td>
                                        <input type="text" name="codice" size="40"></td></tr>
                            </table>
                            <!-- Bottoni per tornare alla schermata precedente o per aggiornare il PDI-->
                            <div class="modal-footer">
                                <button type="button" class="btn btn-dark" data-dismiss="modal">
                                    Annulla</button>
                                <input class="btn btn-outline-success" type='submit' 
                                    style="font-weight: bold" value='Conferma modifiche' name='conferma-modifiche'>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Modal Conferma Eliminazione Nodo -->
        <div id="modal-elimina-pdi" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                    <h5 class="modal-title">Conferma eliminazione PDI</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    </div>
                    <div class="modal-body">
                        <form action='#'>
                            <p>Sicuro di voler rimuovere i PDI selezionati?
                                Questa azione non può essere annullata.</p>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                                <input class="btn btn-danger" type='submit' value='Elimina' name='elimina-pdi'>
                            </div>
                        </form>                    
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Footer -->
        <%@include file="footer.html" %>
    </body>
</html>