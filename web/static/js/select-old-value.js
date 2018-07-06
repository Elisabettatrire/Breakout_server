/* 
 * Il codice assegna come valore di default delle select quello letto dal DB
 */

$(document).ready(function(){
    $('button[id^="mod"]').on('click', function(){
        var id_mod = $(this).attr('id').split('-');
        var elementi = $('p[id^='+id_mod[1]+']').attr('id').split('-');
        var nodo1 = elementi[1];
        var nodo2 = elementi[2];
        var beacon = elementi[3];
        
        $('select[name=codice-1]').val(nodo1);
        $('select[name=codice-1] option[value='+nodo2+']').prop('disabled',true);
        $('select[name=codice-1] option[value!='+nodo2+']').prop('disabled',false);

        $('select[name=codice-2]').val(nodo2);
        $('select[name=codice-2] option[value='+nodo1+']').prop('disabled',true);
        $('select[name=codice-2] option[value!='+nodo1+']').prop('disabled',false);
        
        $('select[name=codice-beacon]').val(beacon);
    });
});
