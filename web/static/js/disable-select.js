/* 
 * Il codice impedisce che, quando si va ad aggiungere un nuovo tronco, il nodo 1
 * e il nodo 2 coincidano.
 */

$(document).ready(function(){
    $('select[name=codice-1]').change(function(){
        var id_1 = $(this).val();
        // Disabilito l'opzione corrispondente dell'altra select
        $('select[name=codice-2] option[value='+id_1+']').prop('disabled',true);
        // Abilito tutte le opzioni dell'altra select che eventualmente erano
        // state disabilitate in precedenza
        $('select[name=codice-2] option[value!='+id_1+']').prop('disabled',false);
    });
    $('select[name=codice-2]').change(function(){
        var id_2 = $(this).val();
        $('select[name=codice-1] option[value='+id_2+']').prop('disabled',true);
        $('select[name=codice-1] option[value!='+id_2+']').prop('disabled',false);
    });
});
