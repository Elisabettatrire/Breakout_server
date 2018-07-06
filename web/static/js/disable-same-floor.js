/* 
 * Il codice impedisce che nell'inserimento di una nuova scala tra mappe
 * vengano selezionati due nodi appartenenti allo stesso piano
 */

$(document).ready(function(){
    $('select[name=codice-1]').change(function(){
        var id_piano_1 = $(this).find(':selected').attr('data-piano');
        // Disabilito l'opzione corrispondente dell'altra select
        $('select[name=codice-2] option[data-piano='+id_piano_1+']').prop('disabled',true);
        // Abilito tutte le opzioni dell'altra select che eventualmente erano
        // state disabilitate in precedenza
        $('select[name=codice-2] option[data-piano!='+id_piano_1+']').prop('disabled',false);
    });
    $('select[name=codice-2]').change(function(){
        var id_piano_2 = $(this).find(':selected').attr('data-piano');
        $('select[name=codice-1] option[data-piano='+id_piano_2+']').prop('disabled',true);
        $('select[name=codice-1] option[data-piano!='+id_piano_2+']').prop('disabled',false);
    });
});
