/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    var c = document.getElementById("map-canvas");
    var ctx = c.getContext("2d");
    var img = document.getElementById("imag");
    ctx.scale(1,1);
    ctx.drawImage(img,0,0);
    
    $('i.fa-map-marker-alt').mouseenter(function(){
        $(this).css('color', '#666666');
    });$('i.fa-map-marker-alt').mouseleave(function(){
        $(this).css('color', 'red');
    });
});


