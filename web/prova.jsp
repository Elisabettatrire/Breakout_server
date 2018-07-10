<%-- 
    Document   : prova
    Created on : 4-giu-2018, 15.49.00
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
        <div  class="col-md-4" >
            <div class="container" style="position:relative">
             <canvas id="myCanvas" width="1000" height="1600"  style="border:1px solid #d3d3d3;" >                    
                    </canvas>
                    
        <img id="imag" src="images/150-1.jpg" hidden="true" >
        <div class="btn-group btn-group-toggle " data-toggle="buttons" style="color:red; position:absolute; left: 10px;
            top: -5px; right: 0px">
            <label class="btn " style="position:absolute; left: 0px;  top: 0px" >
              <input type="radio" name="options" id="option1" autocomplete="off" checked>
              <span  class="glyphicon glyphicon-map-marker" >
                    </span>punto d'inizio
            </label>
            <label class="btn " style="position:absolute; left: 245px; top:5px">
              <input type="radio" name="options" id="option2" autocomplete="off"> 
              <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
            <label class="btn " style="position:absolute; left: 285px; top: 5px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
            <label class="btn " style="position:absolute; left: 245px; top: 52px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
            <label class="btn " style="position:absolute; left: 285px; top: 52px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
            <label class="btn " style="position:absolute; left: 295px; top: 82px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
            <label class="btn " style="position:absolute; left: 285px; top: 135px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
            <label class="btn " style="position:absolute; left: 250px; top: 80px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
            <label class="btn " style="position:absolute; left: 262px; top: 72px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
            <label class="btn " style="position:absolute; left: 220px; top: 90px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label><!--
            <label class="btn " style="position:absolute; left: 220px; top: 100px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>-->
             <label class="btn " style="position:absolute; left: 178px; top: 109px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
            <label class="btn " style="position:absolute; left: 126px; top: 109px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
            <label class="btn " style="position:absolute; left: 178px; top: 58px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
            <label class="btn " style="position:absolute; left: 126px; top: 58px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
            <label class="btn " style="position:absolute; left: 124px; top: 138px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
             <label class="btn " style="position:absolute; left: 98px; top: 58px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
            <label class="btn " style="position:absolute; left: 78px; top: 109px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
            <label class="btn " style="position:absolute; left: 345px; top: 145px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
            <label class="btn " style="position:absolute; left: 320px; top: 150px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
            <label class="btn " style="position:absolute; left: 305px; top: 175px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
            <label class="btn " style="position:absolute; left: 305px; top: 320px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
            <label class="btn " style="position:absolute; left: 280px; top: 212px">
              <input type="radio" name="options" id="option3" autocomplete="off"> 
            <span  class="glyphicon glyphicon-map-marker" >
                    </span>
            </label>
            </div>
            </div>
        </div>
          
        <script>
            /* Carica il canvas e ci mette sopra l'immagine*/
        window.onload = function(){
            var c = document.getElementById("myCanvas");
            var ctx = c.getContext("2d");
            var img = document.getElementById("imag");
            ctx.scale(0.45,0.45);
            ctx.drawImage(img,0,0);
            var c1 = document.getElementById("myCanvas1");
            var ctx1 = c1.getContext("2d");
            var img = document.getElementById("imag");
            ctx1.scale(0.45,0.45);
            ctx1.drawImage(img,0,0);
        };
        </script>
      <!--</div>
      
      
      <canvas id="myCanvas" width="200" height="100"
                    style="border:1px solid #d3d3d3;">
                    Your browser does not support the canvas element.
                    </canvas>
      
      
      <div class="button" >
          <label>   
          <input type="radio" id="a" />
    
          <span  class="glyphicon glyphicon-map-marker" >
          </span> 
          </label>
        </div>

        <div class="button">
            <input type="radio" name="a" value="b" id="b"  />
            <label for="b">b</label>
        </div>
        <div class="button">
            <input type="radio" name="a" value="c" id="c" />
            <label for="c">c</label>
        </div>
          
      <script>
         
//get canvas/context
const canvas = document.getElementById("myCanvas");
const context = canvas.getContext("2d");

//create your shape data in a Path2D object
const path = new Path2D();
path.rect(250, 350, 200, 100);
path.rect(25,72,32,32);
path.closePath();

//draw your shape data to the context
context.fillStyle = "#FFFFFF";
context.fillStyle = "rgba(225,225,225,0.5)";
context.fill(path);
context.lineWidth = 2;
context.strokeStyle = "#000000";
context.stroke(path);

function getXY(canvas, event){ //adjust mouse click to canvas coordinates
  const rect = canvas.getBoundingClientRect();
  const y = event.clientY - rect.top;
  const x = event.clientX - rect.left;
  return {x:x, y:y};
}

document.addEventListener("click",  function (e) {
  const XY = getXY(canvas, e);
  //use the shape data to determine if there is a collision
  if(context.isPointInPath(path, XY.x, XY.y)) {
    // Do Something with the click
    alert("clicked in rectangle");
  }
}, false);
</script>
    -->
      
    </body>
</html>
