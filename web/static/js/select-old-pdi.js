/* 
 * Il codice assegna come valore di default della select quello letto dal DB
 */

$(document).ready(function(){
    $('button[id^="mod"]').on('click', function(){
        var id_mod = $(this).attr('id').split('-');
        var ids = $('p[id^='+id_mod[1]+']').attr('id').split('-');
        
        if(ids[1]==='0') {
            $('select[name=codice-pdi]').val('nessuno');
        } else {
            $('select[name=codice-pdi]').val(ids[1]);
        }
    });
});
