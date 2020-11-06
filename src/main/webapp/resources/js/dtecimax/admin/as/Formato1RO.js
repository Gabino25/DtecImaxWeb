/**
 * 
 */
//$.ajaxSetup({async: false});

 $(document).ready(function() {
    	   console.log( "Comienza ready FormatosRO");
    	   var strNumeroOrdenEle = document.getElementById("numeroOrdenId"); 
    	   var strNumeroOrden = strNumeroOrdenEle.value;
    	   console.log("strNumeroOrden:"+strNumeroOrden);
    	   console.log(strNumeroOrden);
    	  
    	 
    	   var strBytes=""; 
    	   $.ajax({
        	   url: '/DtecImaxWeb/Formato1CO',
        	   type: 'POST',
        	   data: { "accionFormato1CO" : "postFormatoText","jsNumeroOrden" : strNumeroOrden},
        	   success: function (result) {
        		   strBytes = result; 
               },
               async: false
        	});
        	
    	   /**
    	   $.post('/DtecImaxWeb/Formato1CO', {
   		   accionFormato1CO : "postFormatoText",
   		    jsNumeroOrden:strNumeroOrden
   		   }, function(responseText) {
   	      		  strBytes=responseText; 
   		    } ); 
   			**/
    	   strNumeroOrdenEle.parentNode.removeChild(strNumeroOrdenEle);
    	 
    	   var strBytesLength = strBytes.length; 
    	   var strBytes2 = strBytes.substr(1,strBytesLength-1);
    	   /* console.log(strBytes2); */
    	   var strBytes3 = strBytes2.split(",");
    	   /* console.log(strBytes3); */
    	   var strBytes4 = "";
    	   for (var i = 0; i < strBytes3.length; i++) {
    		  /* console.log(strBytes3[i]); */
    		  /* console.log(String.fromCharCode(strBytes3[i])); */
    		   strBytes4 += String.fromCharCode(strBytes3[i]);
    		 }
           /* console.log(strBytes4); */
           document.body.innerHTML=strBytes4;
           document.getElementById("creaPuntoBtn").style.display ="none";
           document.getElementById("guardaFormatoBtn").style.display ="none";
           console.log("Finaliza ready FormatosRO");
    	   

 });