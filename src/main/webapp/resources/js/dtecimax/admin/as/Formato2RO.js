/**
 * 
 */
 $(document).ready(function() {
	
   console.log( "Comienza ready FormatosRO2");
   var strNumeroOrdenEle = document.getElementById("numeroOrdenId"); 
   var strNumeroOrden = strNumeroOrdenEle.value;
   console.log("strNumeroOrden:"+strNumeroOrden);
   console.log(strNumeroOrden);
  
 
   var strBytes=""; 
   $.ajax({
	   url: '/DtecImaxWeb/Formato2CO',
	   type: 'POST',
	   data: { "accionFormato2CO" : "postFormatoText","jsNumeroOrden" : strNumeroOrden},
	   success: function (result) {
		   strBytes = result; 
       },
       async: false
	});

   
   var strBytesLength = strBytes.length; 
   var strBytes2 = strBytes.substr(1,strBytesLength-1);
   var strBytes3 = strBytes2.split(",");
   var strBytes4 = "";
   for (var i = 0; i < strBytes3.length; i++) {
	   strBytes4 += String.fromCharCode(strBytes3[i]);
	 }
   console.log(strBytes4);
   document.body.innerHTML=strBytes4;
   /* strNumeroOrdenEle.parentNode.removeChild(strNumeroOrdenEle); */
  /* document.getElementById("guardaFormatoBtn").style.display ="none";*/
   
   /**
   var primeraVez = document.getElementById("primeraVez"); 
   console.log(primeraVez);
   primeraVez.disabled=true; 
   console.log( "Finaliza ready FormatosRO2");
   **/
   
   /**********************************
	 *  REPLACES
	 */
	 var tecnico = $("#tecnico")[0]; 
     var strTecnico = ''; 
     if(null!=tecnico.value){
   	  strTecnico = '<input type="text" id="tecnico" size="100" value="'+tecnico.value+'" readonly="true"/>'
     }else{
   	  strTecnico = '<input type="text" id="tecnico" size="100"  readonly="true"/>'
     }
     $("#tecnico").replaceWith(strTecnico);  
	/****************************************/ 
     
     $("#ehdc").prop('readonly', true); 
     $("#tat").prop('readonly', true); 
     $("#tmod").prop('readonly', true); 
     $("#acp").prop('readonly', true); 
     $("#dpcsse").prop('readonly', true); 
     
 }
 );