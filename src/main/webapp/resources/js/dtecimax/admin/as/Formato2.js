/**
 * 
 */



 $(document).ready(function() {
	 $("#edad")[0].readOnly=true; 
	 $("#fechaDeNacimiento")[0].readOnly=true; 
	 $("#telefono")[0].readOnly=true; 
	 $("#formaDePago")[0].readOnly=true; 
	 $("#requiereFactura")[0].readOnly=true;
	 $("#estudio")[0].readOnly=true;
	 
	 $("#imax")[0].checked=true;
 });


function guardarFormatoFn(){
	 console.log("Entra guardarFormatoFn");
     var lbody = document.body; 
     
     var primeraVezTmp = document.createElement("input"); 
     primeraVezTmp.type="checkbox"; 
     primeraVezTmp.id = "primeraVez"; 
     primeraVezTmp.checked="true"; 
     
     
     
     /****************************************************
      * REPLACES
      */
      var llegaFecha = $("#llegaFecha")[0]; 
     
      var strLlegaFecha = '<input id="llegaFecha" type="date" value="'+llegaFecha.value+'" readonly="true"/>';
      $("#llegaFecha").replaceWith(strLlegaFecha); 
      
      var imax = $("#imax")[0]; 
      var cm = $("#cm")[0]; 
      
      var strImax = '';
      var strCm = ''; 
      if(imax.checked){
    	  strImax = '<input id="imax" type="radio" name="ubicacion" checked="true" disabled="true"/>'; 
    	  strCm = '<input id="cm" type="radio" name="ubicacion" disabled="true"/>';
      }else if(cm.checked){
    	  strImax = '<input id="imax" type="radio" name="ubicacion" disabled="true"/>'; 
    	  strCm = '<input id="cm" type="radio" name="ubicacion" checked="true" disabled="true"/>';
      }
      
      $("#imax").replaceWith(strImax); 
      $("#cm").replaceWith(strCm); 
      
      var tecnico = $("#tecnico")[0]; 
      var strTecnico = ''; 
      if(null!=tecnico.value){
    	  strTecnico = '<input id="tecnico" size="100" value="'+tecnico.value+'" />'
      }else{
    	  strTecnico = '<input id="tecnico" size="100" value=""/>'
      }
      $("#tecnico").replaceWith(strTecnico);  
      
      var ehdc = $("#ehdc")[0]; 
      var strEhdc = '';
      if(null!=ehdc.value){
    	  strEhdc='<textarea id="ehdc" rows="4" cols="125">'+ehdc.value+'</textarea>'; 
      }else{
    	  strEhdc='<textarea id="ehdc" rows="4" cols="125"></textarea>'; 
      } 
      $("#ehdc").replaceWith(strEhdc);  
      
      var tat = $("#tat")[0]; 
      var strTat = '';
      if(null!=tat.value){
    	  strTat='<textarea id="tat" rows="4" cols="125">'+tat.value+'</textarea>'; 
      }else{
    	  strTat='<textarea id="tat" rows="4" cols="125"></textarea>'; 
      } 
      $("#tat").replaceWith(strTat);  
      
      var tmod = $("#tmod")[0]; 
      var strTmod = '';
      if(null!=tmod.value){
    	  strTmod='<textarea id="tmod" rows="4" cols="125">'+tmod.value+'</textarea>'; 
      }else{
    	  strTmod='<textarea id="tmod" rows="4" cols="125"></textarea>'; 
      } 
      $("#tmod").replaceWith(strTmod);  
      
      
      var acp = $("#acp")[0]; 
      var strAcp = '';
      if(null!=acp.value){
    	  strAcp='<textarea id="acp" rows="4" cols="125">'+acp.value+'</textarea>'; 
      }else{
    	  strAcp='<textarea id="acp" rows="4" cols="125"></textarea>'; 
      } 
      $("#acp").replaceWith(strAcp);   
      
      var dpcsse = $("#dpcsse")[0]; 
      var strDpcsse = '';
      if(null!=dpcsse.value){
    	  strDpcsse='<textarea id="dpcsse" rows="4" cols="125">'+dpcsse.value+'</textarea>'; 
      }else{
    	  strDpcsse='<textarea id="dpcsse" rows="4" cols="125"></textarea>'; 
      } 
      $("#dpcsse").replaceWith(strDpcsse);  
      
     /****************************************************/
     
     
     /**var lbodyInnerHtml = lbody.innerHTML;**/
     
     var lbodyInnerHtml =''; 
     lbodyInnerHtml = lbodyInnerHtml+'<form id="formID">';
     lbodyInnerHtml = lbodyInnerHtml+'<div id="contenedor">';
     lbodyInnerHtml = lbodyInnerHtml+'<div id="row1">';
     lbodyInnerHtml = lbodyInnerHtml+'<div id="row1Col1">';
     var centroDeImagenologia = document.getElementById("formID:centroDeImagenologia"); 
     lbodyInnerHtml = lbodyInnerHtml+centroDeImagenologia.outerHTML;
     lbodyInnerHtml = lbodyInnerHtml+'</div>';
     lbodyInnerHtml = lbodyInnerHtml+'<div id="row1Col2">';
     lbodyInnerHtml = lbodyInnerHtml+'<br/>';
     var primeraVez = document.getElementById("primeraVez"); 
     if(primeraVez.checked){
     lbodyInnerHtml = lbodyInnerHtml+'<input id="primeraVez" type="checkbox" checked="true" disabled="true"/><label for="primeraVez">PRIMERA VEZ</label><br/>'; 
     }else{
     lbodyInnerHtml = lbodyInnerHtml+'<input id="primeraVez" type="checkbox" disabled="true"/><label for="primeraVez">PRIMERA VEZ</label><br/>'; 
     }
     var subsecuente = document.getElementById("subsecuente"); 
     if(subsecuente.checked){
      lbodyInnerHtml = lbodyInnerHtml+'<input id="subsecuente" type="checkbox" checked="true" disabled="true"/><label for="subsecuente">SUBSECUENTE</label><br/>'; 	 
     }else{
    	 lbodyInnerHtml = lbodyInnerHtml+'<input id="subsecuente" type="checkbox" disabled="true"/><label for="subsecuente">SUBSECUENTE</label><br/>'; 	 
     }
     var reporte = document.getElementById("reporte"); 
     if(subsecuente.checked){
      lbodyInnerHtml = lbodyInnerHtml+'<input id="reporte" type="checkbox" checked="true" disabled="true"/><label for="reporte">REPORTE</label><br/>'; 	 
     }else{
    	 lbodyInnerHtml = lbodyInnerHtml+'<input id="reporte" type="checkbox" disabled="true"/><label for="reporte">REPORTE</label><br/>'; 	 
     }
     lbodyInnerHtml = lbodyInnerHtml+'</div>';
     lbodyInnerHtml = lbodyInnerHtml+'<div id="row1Col3">';
     lbodyInnerHtml = lbodyInnerHtml+'<label for="fecha">FECHA:</label>'; 
     var fecha = document.getElementById("fecha"); 
   
     if(null!=fecha.value){
     lbodyInnerHtml = lbodyInnerHtml+'<input id="fecha" type="date" value="'+fecha.value+'" readonly="true"/>';
     }else{
      lbodyInnerHtml = lbodyInnerHtml+'<input id="fecha" type="date" readonly="true"/>';	  
     }
     lbodyInnerHtml = lbodyInnerHtml+'</div>';
     lbodyInnerHtml = lbodyInnerHtml+'</div>';
     
     
     var row2 = document.getElementById("row2"); 
     lbodyInnerHtml = lbodyInnerHtml+row2.outerHTML;
   
     var row3 = document.getElementById("row3"); 
     lbodyInnerHtml = lbodyInnerHtml+row3.outerHTML;
     
     var row4 = document.getElementById("row4"); 
     lbodyInnerHtml = lbodyInnerHtml+row4.outerHTML;
     
     var row5 = document.getElementById("row5"); 
     lbodyInnerHtml = lbodyInnerHtml+row5.outerHTML;
     
     var row6 = document.getElementById("row6"); 
     lbodyInnerHtml = lbodyInnerHtml+row6.outerHTML;
     
     var row7 = document.getElementById("row7"); 
     lbodyInnerHtml = lbodyInnerHtml+row7.outerHTML;
     
     var row8 = document.getElementById("row8"); 
     lbodyInnerHtml = lbodyInnerHtml+row8.outerHTML;
     
     var row9 = document.getElementById("row9"); 
     lbodyInnerHtml = lbodyInnerHtml+row9.outerHTML;
     
     var row10 = document.getElementById("row10"); 
     lbodyInnerHtml = lbodyInnerHtml+row10.outerHTML;
     
     var row11 = document.getElementById("row11"); 
     lbodyInnerHtml = lbodyInnerHtml+row11.outerHTML;
     
     var row12 = document.getElementById("row12"); 
     lbodyInnerHtml = lbodyInnerHtml+row12.outerHTML;
     
     lbodyInnerHtml = lbodyInnerHtml+'</div>';
     lbodyInnerHtml = lbodyInnerHtml+'</form>';
     
     var arr=[];
     for(var i=0; i<=lbodyInnerHtml.length; i++) {
    	    arr.push(lbodyInnerHtml.charCodeAt(i))
    	}
     var bytesArray = new Uint8Array(arr); 
  
     $.ajax({
    	   url: '/DtecImaxWeb/Formato2CO',
    	   type: 'POST',
    	   contentType: 'application/octet-stream',  
    	   data: JSON.stringify(arr),
    	   processData: false
    	});
     console.log("Sale guardarFormatoFn");
}