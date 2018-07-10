/* 
 * Il codice perette di assegnare al campo hidden "old-img" il nome del file
 * immagine attualmente caricato, in modo da poterlo passare al server ed eliminarlo
 * dopo che è stato sostituito con un altro
 */

$(document).ready(function() {
    $('button[id^="img"]').on('click', function(){
        var but_id = $(this).attr('id').split('-');
        var mod_id = but_id[1]; // Si prende il secondo elemento ovvero l'id
        var old_img = but_id[2]; // Si prende il terzo elemento ovvero il nome della mappa
        $('form[id^="form-img"] input[name^="id"]').val(mod_id);
        $('form[id^="form-img"] input[name^="old-img"]').val(old_img);
    });
});
