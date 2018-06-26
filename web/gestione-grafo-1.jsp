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
        <!--Librerie per la selezione multipla-->
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/select/1.2.5/js/dataTables.select.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/select/1.2.5/css/select.dataTables.min.css"/>
        <script src="../static/SelezioneRighe.js"></script>
    </head>
    <body>
        <!-- Header -->
        <%@include file="header.jsp" %>

        <!-- Page Content -->
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <h2>Mappa Q150\1 - Gestione Grafo</h2>
                    <br><br>
                    <!-- Tabella dei Nodi della mappa -->
                    <div class="row">
                        <div class="col-md-6">
                            <h4>Lista Nodi</h4>
                        </div>
                        <div class="col-md-6" style="text-align: right">
                            <button type="button" class="btn btn-sm btn-outline-success"
                                    data-toggle="modal" data-target="#modal-agg-nodo">
                                <b>Aggiungi nodo</b>
                            </button>
                        </div>
                    </div>
                    <form>
                        <table class="table table-bordered table-striped" style="text-align: center">
                            <thead>
                                <tr><th>Coord_X</th><th>Coord_Y</th><th>Larghezza</th><th>Codice</th>
                            </thead>
                            <tr><td>129</td><td>465</td><td>1.8</td><td>150G2</td></tr>
                            <tr><td>110</td><td>465</td><td>1.8</td><td>150G1G2</td></tr>
                        </table>
                        <!-- Bottoni per la gestione dei nodi -->
                        <div class="row" style="margin-top: 10px">
                            <div class="col-md-6">
                                <button type="button" class="btn btn-sm btn-outline-danger"
                                        data-toggle="modal" data-target="#modal-elimina-nodi">
                                    <b>Elimina i nodi selezionati</b>
                                </button>
                            </div>
                            <div class="col-md-6" style="text-align: right">
                                <button type="button" class="btn btn-sm btn-outline-dark"
                                        data-toggle="modal" data-target="#modal-mod-nodo">
                                    <b>Modifica il nodo selezionato</b>
                                </button>
                            </div>
                        </div>
                    </form>
                    <hr>
                    <br>
                    
                    <!-- Tabella dei Tronchi della mappa -->
                    <div class="row"> 
                        <div class="col-md-6">
                            <h4>Lista Tronchi</h4>
                        </div>
                        <div class="col-md-6" style="text-align: right">
                            <button type="button" class="btn btn-sm btn-outline-success"
                                    data-toggle="modal" data-target="#modal-agg-tronco">
                                <b>Aggiungi tronco</b>
                            </button>
                        </div>
                    </div>
                    <form>
                        <table  class="table table-bordered table-striped" style="text-align: center">
                            <thead>
                                <tr>
                                    <th>Lunghezza</th><th>Cod. Nodo 1</th><th>Cod. Nodo 2</th><th>Codice</th>
                                </tr>
                            </thead>
                            <tr><td>19</td><td>150G2</td><td>150G1G2</td><td>T1501</td></tr>    
                        </table>
                        <!--Bottoni per la gestione dei tronchi-->
                        <div class="row" style="margin-top: 10px"> 
                            <div class="col-md-6">
                                <button type="button" class="btn btn-sm btn-outline-danger"
                                        data-toggle="modal" data-target="#modal-elimina-tronchi">
                                    <b>Elimina i tronchi selezionati</b>
                                </button>
                            </div>
                            <div class="col-md-6" style="text-align: right">
                                <button type="button" class="btn btn-sm btn-outline-dark"
                                        data-toggle="modal" data-target="#modal-mod-tronco">
                                    <b>Modifica il tronco selezionato</b>
                                </button>
                            </div>
                        </div>
                    </form>
                    <hr>
                    <br>
                </div>
                     
                <!-- Immagine della mappa fs:990x1572 -->
                <div class="col-md-6" >  
                    <img src="/Immagini/150_color.jpg" width="594" height="943">
                </div>
            </div>
            
            <!-- Tabella dei Punti di Interesse-->
            <br><br>
            <div class="row"> 
                <div class="col-md-3">
                    <h4>Punti di interesse</h4>
                </div>
                <div class="col-md-3" style="text-align: right">
                    <button type="button" class="btn btn-sm btn-outline-success"
                                data-toggle="modal" data-target="#modal-agg-pdi">
                            <b>Aggiungi PDI</b>
                        </button>
                </div>
            </div>
            <form> 
                <table class="table table-bordered table-striped" style="text-align: center">
                    <thead>
                        <tr>
                            <th>Coord_X</th><th>Coord_Y</th><th>Larghezza</th><th>Lunghezza</th><th>Tipo</th>
                            <th>Descrizione </th><th>Codice</th>
                        </tr>
                    </thead>
                    <tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
                </table>
                <!-- Bottoni per la gestione dei PDI -->
                <div class="row" style="margin-top: 10px">
                    <div class="col-md-3">
                        <button type="button" class="btn btn-sm btn-outline-danger"
                                data-toggle="modal" data-target="#modal-elimina-pdi">
                            <b>Elimina i PDI selezionati</b></button>
                    </div>
                    <div class="col-md-3" style="text-align: right">
                        <button type="button" class="btn btn-sm btn-outline-dark" 
                            data-toggle="modal" data-target="#modal-mod-pdi">
                            <b>Modifica il PDI selezionato</b>
                        </button>
                    </div>
                </div>
            </form>
            <br>
            <div class="row">
                <button type="button" class="btn btn-dark">
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
                                <button type="button" class="btn btn-dark" data-dismiss="modal">
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
        <div id="modal-elimina-nodi" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                    <h5 class="modal-title">Conferma eliminazione nodi</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    </div>
                    <div class="modal-body">
                        <form action='#'>
                            <p>Sicuro di voler rimuovere i nodi selezionati?
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
        <div id="modal-elimina-tronchi" class="modal fade">
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