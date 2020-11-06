/**
 * 
 */
  var counterDivs = 0; 
      $(document).ready(function() {
         console.log( "ready!" );
       });
      function creaPunto(){
    	  console.log("Entra creaPunto");
    	  var crearCirculosDiv = document.getElementById("crearCirculosDiv");
    	  console.log(crearCirculosDiv);  
    	  var divCircle = document.createElement("div");
    	  counterDivs = counterDivs+1; 
    	  divCircle.id="draggable"+counterDivs; 
    	  divCircle.classList.add("ui-widget-content");
    	  divCircle.classList.add("xxdraggable");
    	  crearCirculosDiv.appendChild(divCircle);
    	  console.log(crearCirculosDiv.height);
    	  $("#draggable"+counterDivs).draggable();
    	  console.log("Entra salePunto"); 
       }     
       function getBody(){
         console.log("Entra getBody");
         var lbody = document.body; 
         console.log(lbody);
         var lbodyInnerHtml = lbody.innerHTML; 
         var arr=[];
         for(var i=0; i<=lbodyInnerHtml.length; i++) {
        	    arr.push(lbodyInnerHtml.charCodeAt(i))
        	}
         var bytesArray = new Uint8Array(arr); 
         console.log(bytesArray);
         //console.log(lbodyInnerHtml);
         //console.log(arr);
         //var data = $('#formID').serialize();
         //console.log(data);
         //$.post('/DtecImaxWeb/Formato1CO', {
  		 //   accion : "formato1"
  		 // }, function(responseText) {
  	     //	console.log(responseText);
  		 // }
  		 // );
         
         $.ajax({
        	   url: '/DtecImaxWeb/Formato1CO',
        	   type: 'POST',
        	   contentType: 'application/octet-stream',  
        	   data: JSON.stringify(arr),
        	   processData: false
        	});
         
         
         console.log("Sale getBody");
         
        
         
       }