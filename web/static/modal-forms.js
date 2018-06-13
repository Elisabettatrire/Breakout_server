/* 
 * Il codice permette di assegnare al campo hidden "id" delle form di modifica ed
 * eliminazione il valore dell'id dell'elemento selezionato su cui eseguire le query
 */

$(document).ready(function() {
    $('button[id^="del"]').on('click', function(){
        var but_id = $(this).attr('id').split('-');
        var del_id = but_id[1];
        $('form[id^="form-del"] input[name^="id"]').val(del_id);
    });
    $('button[id^="mod"]').on('click', function(){
        var but_id = $(this).attr('id').split('-');
        var mod_id = but_id[1];
        $('form[id^="form-mod"] input[name^="id"]').val(mod_id);
    });
});


