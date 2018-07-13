<%-- 
    Document   : prova1
    Created on : 3-lug-2018, 10.54.11
    Author     : Francesco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap-grid.css" type="text/css">
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap-grid.min.css" type="text/css">
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap-reboot.css" type="text/css">
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap-reboot.min.css" type="text/css">
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap.css" type="text/css">
        <link rel="stylesheet" href="static/bootstrap-4.1.1-dist/css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="static/fontawesome/fontawesome-all.css">
        <script src="static/bootstrap-4.1.1-dist/js/bootstrap.bundle.js"></script>
        <script src="static/bootstrap-4.1.1-dist/js/bootstrap.bundle.min.js"></script>
        <script src="static/bootstrap-4.1.1-dist/js/bootstrap.js"></script>
        <script src="static/bootstrap-4.1.1-dist/js/bootstrap.min.js"></script>
        <script src="static/bootstrap-4.1.1-dist/js/bootbox.min.js"></script>
        <!--Librerie per la selezione multipla-->
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/select/1.2.5/js/dataTables.select.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/select/1.2.5/css/select.dataTables.min.css"/>
        <script src="../static/SelezioneRighe.js"></script>
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    </head>
    <body>
        <div  class="col-md-8" >
            <div class="container" style="position:relative">
              <!--
              -Mettere un container a relative e posizionarci un canvas con lunghezza e larghezza predefinita
              -Inserire il percorso dell'immagine su cui andare a mettere i bottoni segna nodi e/o beacon
              -Inserire gli oggetti bottoni in position absolute, 
              -abbiamo messo il punto iniziale in left=0 top=-13 right=0 per tutte le immagini caricate
              - per ogni nodo/beacon abbiamo inserito un bottone con le coordinate prese dal db(per ogni messi manualmente)
              
              -->
             <canvas id="myCanvas" width="1000" height="1600"  style="border:1px solid #d3d3d3;" >                    
                   </canvas>
        <img id="imag" src="/Immagini/150_color (2).jpg" hidden="true" >
         <div class="btn-group btn-group-toggle " data-toggle="buttons" style="color:red; position:absolute; left: 0px;
            top: -13px; right: 0px">
             <label class="btn " style="position:absolute; left: 0px;  top: 0px" >
              <input type="radio" name="options" id="option1" autocomplete="off" checked>
              <span  class="glyphicon glyphicon-map-marker" >
                    </span>punto d'inizio
            </label>
            <label class="btn " style="position:absolute; left: 550px; top:10px">
              <input type="radio" name="options" id="option2" autocomplete="off"> 
              <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
            <label class="btn " style="position:absolute; left: 630px; top: 10px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
            <label class="btn " style="position:absolute; left: 550px; top: 110px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
            <label class="btn " style="position:absolute; left: 630px; top: 60px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
            <label class="btn " style="position:absolute; left: 635px; top: 110px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
            <label class="btn " style="position:absolute; left: 640px; top: 150px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
             <label class="btn " style="position:absolute; left: 635px; top: 190px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
            <label class="btn " style="position:absolute; left: 640px; top: 255px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
            <label class="btn " style="position:absolute; left: 650px; top: 315px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
            <label class="btn " style="position:absolute; left: 550px; top: 185px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
            <label class="btn " style="position:absolute; left: 590px; top: 200px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
             <label class="btn " style="position:absolute; left: 580px; top: 160px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
            <label class="btn " style="position:absolute; left: 485px; top: 205px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
             <label class="btn " style="position:absolute; left: 485px; top: 165px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
            <label class="btn " style="position:absolute; left: 525px; top: 205px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
            <label class="btn " style="position:absolute; left: 480px; top: 240px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
            <label class="btn " style="position:absolute; left: 505px; top: 295px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
             <label class="btn " style="position:absolute; left: 525px; top: 255px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
             <label class="btn " style="position:absolute; left: 415px; top: 250px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
              <label class="btn " style="position:absolute; left: 415px; top: 125px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
            <label class="btn " style="position:absolute; left: 275px; top: 305px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
            <label class="btn " style="position:absolute; left: 400px; top: 295px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
             <label class="btn " style="position:absolute; left: 210px; top: 285px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
             <label class="btn " style="position:absolute; left: 280px; top: 250px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
             <label class="btn " style="position:absolute; left: 280px; top: 125px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
            <label class="btn " style="position:absolute; left: 210px; top: 200px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
            <label class="btn " style="position:absolute; left: 215px; top: 125px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
             <label class="btn " style="position:absolute; left: 565px; top: 295px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
            <label class="btn " style="position:absolute; left: 765px; top: 320px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
            <label class="btn " style="position:absolute; left: 710px; top: 335px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
             <label class="btn " style="position:absolute; left: 710px; top: 300px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
            <label class="btn " style="position:absolute; left: 680px; top: 465px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
             <label class="btn " style="position:absolute; left: 665px; top: 390px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
           <label class="btn " style="position:absolute; left: 560px; top: 370px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
             <label class="btn " style="position:absolute; left: 625px; top: 465px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
             <label class="btn " style="position:absolute; left: 680px; top: 580px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
             <label class="btn " style="position:absolute; left: 680px; top: 690px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
             <label class="btn " style="position:absolute; left: 680px; top: 790px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
             <label class="btn " style="position:absolute; left: 625px; top: 750px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
             <label class="btn " style="position:absolute; left: 755px; top: 690px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
             <label class="btn " style="position:absolute; left: 725px; top: 690px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
             <label class="btn " style="position:absolute; left: 765px; top: 755px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
             <label class="btn " style="position:absolute; left: 740px; top: 760px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
             <label class="btn " style="position:absolute; left: 765px; top: 725px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
             <label class="btn " style="position:absolute; left: 740px; top: 735px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
             <label class="btn " style="position:absolute; left: 625px; top: 1100px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
             <label class="btn " style="position:absolute; left: 850px; top: 900px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>B
            </label>
            </div>
            </div>
        </div>
       <!-- 
       Inserire questo pezzo di codice per caricare direttamente il canvas con sopra l'immagine
       scale(1,1) l'immagine non è scalata 
       drawImage(img, 0, 0) ti permette di inserire le posizioni rispetto ai margini del canvas che noi abbiamo posto a 0,0
       -->
        <script>
        window.onload = function(){
            var c = document.getElementById("myCanvas");
            var ctx = c.getContext("2d");
            var img = document.getElementById("imag");
            ctx.scale(1,1);
            ctx.drawImage(img,0,0);
        };
        </script>

    </body>
</html>
