/* 
 * Il codice impedisce che nell'inserimento di un nuovo collegamento tra mappe
 * o di una suova scala vengano selezionati due nodi appartenenti alla stessa mappa
 */

$(document).ready(function(){
    $('select[name=codice-1]').change(function(){
        var id_mappa_1 = $(this).find(':selected').attr('data-mappa');
        // Disabilito l'opzione corrispondente dell'altra select
        $('select[name=codice-2] option[data-mappa='+id_mappa_1+']').prop('disabled',true);
        // Abilito tutte le opzioni dell'altra select che eventualmente erano
        // state disabilitate in precedenza
        $('select[name=codice-2] option[data-mappa!='+id_mappa_1+']').prop('disabled',false);
    });
    $('select[name=codice-2]').change(function(){
        var id_mappa_2 = $(this).find(':selected').attr('data-mappa');
        $('select[name=codice-1] option[data-mappa='+id_mappa_2+']').prop('disabled',true);
        $('select[name=codice-1] option[data-mappa!='+id_mappa_2+']').prop('disabled',false);
    });
});
