/* 
 * Il codice fa sì che ogni volta che si clicca sul pulsante che apre una form
 * i campi siano vuotii: infatti se prima si era cliccato sul pulsante per
 * la modifica, le select vengono compilate.
 */

$(document).ready(function(){
    $('button[id^="btn-agg"]').on('click', function(){
        $('select').find(':selected').prop('selected', false);
    });
});