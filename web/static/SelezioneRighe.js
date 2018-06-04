/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//codice per la selezione multipla delle righe e per la gestione della tabella
$(document).ready(function() {
                $(".table.table-bordered.table-striped").DataTable({
                    "searching": false,
                    "bInfo":false,
                     dom: 'frti',
                     select: {
                     style: 'multi'
                       }
            } );
           });

           
           
         