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
        <link href="static/fontawesome/fontawesome-all.css" rel="stylesheet">
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
        <script src="static/SelezioneRighe.js"></script>
    </head>
    <body>
        <!-- Header -->
        <%@include file="header.html" %>

        <!-- Page Content -->
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <h2>Mappa Q150\1 - Gestione Grafo</h2>
                    <br><br>
                    <h4>Lista Nodi</h4>
                    <form> 
                        <!--Tabella dei Nodi della mappa-->
                        <table class="table table-bordered table-striped" style="text-align: center">
                            <thead>
                                <tr><th>Coord_X</th><th>Coord_Y</th><th>Larghezza</th><th>Codice</th>
                            </thead>
                            <tr><td>129</td><td>465</td><td>1.8</td><td>150G2</td></tr>
                            <tr><td>110</td><td>465</td><td>1.8</td><td>150G1G2</td></tr>
                        </table>
                        <!-- Bottoni per la gestione dei nodi -->
                        <div class="row">
                            <button type="button" class="btn btn-outline-danger">
                                <b>Elimina i nodi selezionati</b>
                            </button>
                            <button type="button" class="btn btn-outline-dark"
                                    data-toggle="modal" data-target="#ModalModNodo">
                                <b>Modifica il nodo selezionato</b>
                            </button>                       
                            <br>
                            <button type="button" class="btn btn-outline-success"
                                    data-toggle="modal" data-target="#ModalAggNodo">
                                <b>Aggiungi nodo</b>
                            </button>
                        </div>
                    </form>
                        <!--Gestione dei tronchi-->
                        <br><br>
                        
                         <div class="container" style="background-color: ghostwhite  ">
                        <div class="row"> 
                         <div class="col-lg-6">
                        <h2 >
                                Tronchi
                        </h2>
                         </div>
                        <div class="col-lg-6">
                            <!--Bottone per modificare il tronco selezionato-->
                        <button type="button" class="btn btn-outline-success" data-toggle="modal" data-target="#ModalModTronco" style="font-weight: bold">
                                        Modifica il tronco selezionato</button>
                        </div>
                       </div>
                        <form> 
                            <!--Tabella dei Tronchi della mappa-->
                        <table  class="table table-bordered table-striped" style="text-align: center">
                            <thead>
                                    <tr>
                                        <th>Lunghezza </th><th>Cod. Nodo1 </th><th>Cod. Nodo1 </th><th>Codice </th>
                                    </tr>
                                </thead>
                                <tr> <td>19</td><td>150G2</td><td>150G1G2</td><td>T1501</td> </tr>    
                            
                        </table>
                        </form><br>
                         <!--Bottoni per la gestione dei tronchi-->
                        <div class="row"> 
                        <div class="col-lg-6">
                            <button type="button" class="btn btn-outline-success" data-toggle="modal" data-target="#ModalAggTronco" style="font-weight: bold">
                                        Aggiungi tronco</button>
                            </div>
                        <div class="col-lg-6">
                            <button type="button" class="btn btn-outline-success" style="font-weight: bold">
                                        Elimina i tronchi selezionati</button>
                        </div>
                        </div>
                      </div>
                        
                      </div>
                     
                    <div class="col-lg-6" >
                        
                        <!--Immagine della mappa -->
                        <img src="/Immagini/150_color.jpg" width="500" height="600" >
                    </div>
                </div>
                <!--Gestione dei punti di interesse-->
                        
                <br><br>
                <div class="container" style="background-color: ghostwhite">
                        <div class="row"> 
                         <div class="col-lg-6">
                        <h2 >
                                Punti di interesse
                        </h2>
                         </div>
                        <div class="col-lg-6">
                            <!--Bottone per modificare il PDI selezionato-->
                        <button type="button" class="btn btn-outline-success" 
                                data-toggle="modal" data-target="#ModalModPDI" style="font-weight: bold">
                                        Modifica il PDI selezionato</button>
                        </div>
                       </div>
                        <form> 
                            <!--Tabella dei PDI della mappa-->
                        <table class="table table-bordered table-striped" style="text-align: center">
                                <thead>
                                    <tr>
                                        <th>Coord_X </th><th>Coord_Y </th><th>Larghezza </th><th>Lunghezza </th><th>Tipo </th><th>Descrizione </th><th>Codice </th>
                                    </tr>
                                </thead>
                                <tr> <td></td><td></td><td></td><td></td><td></td><td></td><td></td> </tr>
                        </table>
                        </form><br>
                         <!--Bottoni per la gestione dei PDI-->
                        <div class="row"> 
                            <div class="col-lg-6">
                                <button type="button" class="btn btn-outline-success" data-toggle="modal" data-target="#ModalAggPDI" style="font-weight: bold">
                                            Aggiungi PDI</button>
                            </div>
                            <div class="col-lg-6">
                                <button type="button" class="btn btn-outline-success" style="font-weight: bold">
                                            Elimina i PDI selezionati</button>
                            </div>
                        </div>
                </div>
                        <br><br>
                <button type="button" class="btn btn-outline-success" style="font-weight: bold">
                                        Indietro</button>
            </div>
        </div>
<!-- Definizione della finestra popup aggiunta di un nodo-->
            <div id="ModalAggNodo" class="modal fade" style="position: fixed; top:0; left:0; background-color: rgba(0,0,0,.8);
                 width: 100%; height:100%">
            <div class="modal-dialog " >
             <div class="modal-content ">
                 <div class="modal-header" >
                    <div class="col-lg-11" style="text-align: center">
                        <h4 class="modal-title"> Aggiungi Nodo</h4>
                    </div>
                        <button type="button" class="close" data-dismiss="modal">&times;</button> 
              </div>
              <div class="modal-body">
                  <!-- text area per inserire i dati dei nodi da caricare -->
                  <table class="table table-borderless " style="text-align: center">
                                <tr> <td>Coord_X</td><td><input type="text" name="InserisciCoordinateX" placeholder="Es. 129" value="" size="40" /></td> </tr>
                                <tr> <td>Coord_Y</td><td><input type="text" name="InserisciCoordinateY" placeholder="Es. 465" value="" size="40" /></td> </tr>
                                <tr> <td>Larghezza</td><td><input type="text" name="Larghezza" placeholder="Es. 1.8" value="" size="40" /></td> </tr>
                                <tr> <td>Codice</td><td><input type="text" name="Codice" placeholder="Es. 150G2" value="" size="40"  /></td> </tr>
                  </table>
              </div>
              <div class="modal-footer">
                  <!-- Bottoni per tornare alla schermata precedente o per aggiungere il nodo-->
                    <div class="col-md-6">
                        <div style="text-align: left">
                        <button type="button" class="btn btn-outline-success" data-dismiss="modal" style="font-weight: bold">
                            Annulla</button>
                        </div>
                           </div>
                    <div class="col-md-6">
                        <div style="text-align: right">
                        <button type="button" class="btn btn-primary btn-outline-success" style="font-weight: bold">
                            Aggiungi</button>
                        </div>
                    </div>
              </div>
             </div>
            </div>
           </div>
<!-- Definizione della finestra popup per la modifica di un nodo-->
            <div id="ModalModNodo" class="modal fade" style="position: fixed; top:0; left:0; background-color: rgba(0,0,0,.8);
                 width: 100%; height:100%">
            <div class="modal-dialog " >
             <div class="modal-content ">
                 <div class="modal-header" >
                    <div class="col-lg-11" style="text-align: center">
                        <h4 class="modal-title"> Modifica Nodo</h4>
                    </div>
                        <button type="button" class="close" data-dismiss="modal">&times;</button> 
              </div>
              <div class="modal-body">
                  <!-- text area per modificare i dati dei nodi caricati -->
                  <table class="table table-borderless " style="text-align: center">
                                <tr> <td>Coord_X</td><td><input type="text" name="InserisciCoordinateX" placeholder="Es. 129" value="" size="40" /></td> </tr>
                                <tr> <td>Coord_Y</td><td><input type="text" name="InserisciCoordinateY" placeholder="Es. 465" value="" size="40" /></td> </tr>
                                <tr> <td>Larghezza</td><td><input type="text" name="Larghezza" placeholder="Es. 1.8" value="" size="40" /></td> </tr>
                                <tr> <td>Codice</td><td><input type="text" name="Codice" placeholder="Es. 150G2" value="" size="40"  /></td> </tr>
                  </table>
              </div>
              <div class="modal-footer">
                  <!-- Bottoni per tornare alla schermata precedente o per aggiornare le modifiche-->
                    <div class="col-md-6">
                        <div style="text-align: left">
                        <button type="button" class="btn btn-outline-success" data-dismiss="modal" style="font-weight: bold">
                            Annulla</button>
                        </div>
                           </div>
                    <div class="col-md-6">
                        <div style="text-align: right">
                        <button type="button" class="btn btn-primary btn-outline-success" style="font-weight: bold">
                            Modifica</button>
                        </div>
                    </div>
              </div>
             </div>
            </div>
           </div>
<!-- Definizione della finestra popup aggiunta di un tronco-->
            <div id="ModalAggTronco" class="modal fade" style="position: fixed; top:0; left:0; background-color: rgba(0,0,0,.8);
                 width: 100%; height:100%">
            <div class="modal-dialog " >
             <div class="modal-content ">
                 <div class="modal-header" >
                    <div class="col-lg-11" style="text-align: center">
                        <h4 class="modal-title"> Aggiungi Tronco</h4>
                    </div>
                        <button type="button" class="close" data-dismiss="modal">&times;</button> 
              </div>
              <div class="modal-body">
                  <!-- text area per inserire i dati dei Tronco da caricare -->
                  <table class="table table-borderless " style="text-align: center">
                                <tr> <td>Cod. Nodo1</td><td><select name='Nome Beacon' ><option>150G1G2</option><option>150G2</option>
                            </select></td> </tr>
                               <tr> <td>Cod. Nodo2</td><td><select name='Nome Beacon' ><option>150G1G2</option><option>150G2</option>
                            </select></td> </tr> 
                  </table>
              </div>
              <div class="modal-footer">
                  <!-- Bottoni per tornare alla schermata precedente o per aggiungere il nodo-->
                    <div class="col-md-6">
                        <div style="text-align: left">
                        <button type="button" class="btn btn-outline-success" data-dismiss="modal" style="font-weight: bold">
                            Annulla</button>
                        </div>
                           </div>
                    <div class="col-md-6">
                        <div style="text-align: right">
                        <button type="button" class="btn btn-primary btn-outline-success" style="font-weight: bold">
                            Aggiungi</button>
                        </div>
                    </div>
              </div>
             </div>
            </div>
           </div>
<!-- Definizione della finestra popup per la modifica di un tronco-->
            <div id="ModalModTronco" class="modal fade" style="position: fixed; top:0; left:0; background-color: rgba(0,0,0,.8);
                 width: 100%; height:100%">
            <div class="modal-dialog " >
             <div class="modal-content ">
                 <div class="modal-header" >
                    <div class="col-lg-11" style="text-align: center">
                        <h4 class="modal-title"> Modifica Tronco</h4>
                    </div>
                        <button type="button" class="close" data-dismiss="modal">&times;</button> 
              </div>
              <div class="modal-body">
                  <!-- text area per modificare i dati dei tronchi caricati -->
                  <table class="table table-borderless " style="text-align: center">
                               <tr> <td>Cod. Nodo1</td><td><select name='Nome Beacon' ><option>150G1G2</option><option>150G2</option>
                            </select></td> </tr>
                               <tr> <td>Cod. Nodo2</td><td><select name='Nome Beacon' ><option>150G1G2</option><option>150G2</option>
                            </select></td> </tr>                               
                  </table>
              </div>
              <div class="modal-footer">
                  <!-- Bottoni per tornare alla schermata precedente o per aggiornare le modifiche-->
                    <div class="col-md-6">
                        <div style="text-align: left">
                        <button type="button" class="btn btn-outline-success" data-dismiss="modal" style="font-weight: bold">
                            Annulla</button>
                        </div>
                           </div>
                    <div class="col-md-6">
                        <div style="text-align: right">
                        <button type="button" class="btn btn-primary btn-outline-success" style="font-weight: bold">
                            Modifica</button>
                        </div>
                    </div>
              </div>
             </div>
            </div>
           </div>
<!-- Definizione della finestra popup aggiunta di un PDI-->
            <div id="ModalAggPDI" class="modal fade" style="position: fixed; top:0; left:0; background-color: rgba(0,0,0,.8);
                 width: 100%; height:100%">
            <div class="modal-dialog " >
             <div class="modal-content ">
                 <div class="modal-header" >
                    <div class="col-lg-11" style="text-align: center">
                        <h4 class="modal-title"> Aggiungi PDI</h4>
                    </div>
                        <button type="button" class="close" data-dismiss="modal">&times;</button> 
              </div>
              <div class="modal-body">
                  <!-- text area per inserire i dati dei nodi da caricare -->
                  <table class="table table-borderless " style="text-align: center">
                                <tr> <td>Coord_X</td><td><input type="text" name="InserisciCoordinateX" placeholder="Inserisci Coordinate X" value="" size="40" /></td> </tr>
                                <tr> <td>Coord_Y</td><td><input type="text" name="InserisciCoordinateY" placeholder="Inserisci Coordinate Y" value="" size="40" /></td> </tr>
                                <tr> <td>Lunghezza</td><td><input type="text" name="Lunghezza" placeholder="Inserisci Lunghezza" value="" size="40" /></td> </tr>
                                <tr> <td>Larghezza</td><td><input type="text" name="Larghezza" placeholder="Inserisci Larghezza" value="" size="40" /></td> </tr>
                                <tr> <td>Tipo</td><td><select name='Tipo' ><option>Aula</option><option>Punto di Ritrovo</option>
                            </select></td> </tr>
                                <tr> <td>Descrizione</td><td><input type="text" name="Descrizione" placeholder="Descrizione" value="" size="40" /></td> </tr>
                                <tr> <td>Codice</td><td><input type="text" name="Codice" placeholder="Inserisci Codice" value="" size="40"  /></td> </tr>
                  </table>
              </div>
              <div class="modal-footer">
                  <!-- Bottoni per tornare alla schermata precedente o per aggiungere il nodo-->
                    <div class="col-md-6">
                        <div style="text-align: left">
                        <button type="button" class="btn btn-outline-success" data-dismiss="modal" style="font-weight: bold">
                            Annulla</button>
                        </div>
                           </div>
                    <div class="col-md-6">
                        <div style="text-align: right">
                        <button type="button" class="btn btn-primary btn-outline-success" style="font-weight: bold">
                            Aggiungi</button>
                        </div>
                    </div>
              </div>
             </div>
            </div>
           </div>
<!-- Definizione della finestra popup per la modifica di un PDI-->
            <div id="ModalModPDI" class="modal fade" style="position: fixed; top:0; left:0; background-color: rgba(0,0,0,.8);
                 width: 100%; height:100%">
            <div class="modal-dialog " >
             <div class="modal-content ">
                 <div class="modal-header" >
                    <div class="col-lg-11" style="text-align: center">
                        <h4 class="modal-title"> Modifica PDI</h4>
                    </div>
                        <button type="button" class="close" data-dismiss="modal">&times;</button> 
              </div>
              <div class="modal-body">
                  <!-- text area per modificare i dati dei PDI caricati -->
                  <table class="table table-borderless " style="text-align: center">
                                <tr> <td>Coord_X</td><td><input type="text" name="InserisciCoordinateX" placeholder="Inserisci Coordinate X" value="" size="40" /></td> </tr>
                                <tr> <td>Coord_Y</td><td><input type="text" name="InserisciCoordinateY" placeholder="Inserisci Coordinate Y" value="" size="40" /></td> </tr>
                                <tr> <td>Lunghezza</td><td><input type="text" name="Lunghezza" placeholder="Inserisci Lunghezza" value="" size="40" /></td> </tr>
                                <tr> <td>Larghezza</td><td><input type="text" name="Larghezza" placeholder="Inserisci Larghezza" value="" size="40" /></td> </tr>
                                <tr> <td>Tipo</td><td><select name='Tipo' ><option>Aula</option><option>Punto di Ritrovo</option>
                            </select></td> </tr>
                                <tr> <td>Descrizione</td><td><input type="text" name="Descrizione" placeholder="Descrizione" value="" size="40" /></td> </tr>
                                <tr> <td>Codice</td><td><input type="text" name="Codice" placeholder="Inserisci Codice" value="" size="40"  /></td> </tr>
                  </table>
              </div>
              <div class="modal-footer">
                  <!-- Bottoni per tornare alla schermata precedente o per aggiornare le modifiche-->
                    <div class="col-md-6">
                        <div style="text-align: left">
                        <button type="button" class="btn btn-outline-success" data-dismiss="modal" style="font-weight: bold">
                            Annulla</button>
                        </div>
                           </div>
                    <div class="col-md-6">
                        <div style="text-align: right">
                        <button type="button" class="btn btn-primary btn-outline-success" style="font-weight: bold">
                            Modifica</button>
                        </div>
                    </div>
              </div>
             </div>
            </div>
           </div>
        
        <!-- Footer -->
        <%@include file="footer.html" %>
    </body>
</html>