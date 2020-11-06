package com.dtecimax.ejb.backing.admin.as;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlBody;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import com.dtecimax.ejb.services.ar.PacientesLocal;
import com.dtecimax.ejb.services.as.OrdenesEstudiosLocal;
import com.dtecimax.ejb.services.as.OrdenesEstudiosVLocal;
import com.dtecimax.jpa.dto.ar.PacientesDto;
import com.dtecimax.jpa.dto.as.OrdenesEstudiosVDto;
import com.dtecimax.utils.Utilitarios;

@ManagedBean  
@ViewScoped
public class Formato1Form {
	
	private String nombreCompletoPaciente;
	private Date fechaDeNacimiento; 
	private int anios; 
	
	private Date currentDate=new Date(); 

	@Inject
	OrdenesEstudiosVLocal ordenesEstudiosVLocal; 
	
	@Inject 
	PacientesLocal pacientesLocal; 
	
	@Inject 
	OrdenesEstudiosLocal ordenesEstudiosLocal; 
	
	 @PostConstruct
	 public void init() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap  = context.getSessionMap();
        Long longNumeroOrden =  (Long)sessionMap.get("svNumeroOrden"); 
        System.out.println("longNumeroOrden:");
        System.out.println(longNumeroOrden);
        OrdenesEstudiosVDto ordenesEstudiosVDto = ordenesEstudiosVLocal.findByNumeroOrden(longNumeroOrden.longValue()); 
        nombreCompletoPaciente = ordenesEstudiosVDto.getNombrePaciente(); 
        PacientesDto pacientesDto = pacientesLocal.findByNumeroPaciente(ordenesEstudiosVDto.getNumeroPaciente()); 
        fechaDeNacimiento = pacientesDto.getFechaNacimientoPaciente(); 
        int difMonths = Utilitarios.differenceInMonths(fechaDeNacimiento, currentDate);
        int intAniosBetween = difMonths/12;
        anios = intAniosBetween; 
        
	 }
	            
	public void guardarFormato() {
		System.out.println("Entra guardarFormato");
		/************************************************************
		Prueba 1 
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		UIViewRoot uIViewRoot =facesContext.getViewRoot(); 
		List<UIComponent> listUiComponent  = uIViewRoot.getChildren(); 
		Iterator<UIComponent> iterUiComponent= listUiComponent.iterator(); 
		while(iterUiComponent.hasNext()) {
			UIComponent uIComponent = iterUiComponent.next(); 
			if(uIComponent instanceof HtmlBody) {
				HtmlBody htmlBody = (HtmlBody)uIComponent; 
				System.out.println("Inicia htmlBody");
				List<UIComponent> listUiComponentHtmlBody = htmlBody.getChildren(); 
				Iterator<UIComponent> iterUiComponentHtmlBody =  listUiComponentHtmlBody.iterator(); 
				 while(iterUiComponentHtmlBody.hasNext()) {
					 UIComponent uIComponentHtmlBody = iterUiComponentHtmlBody.next();
					 System.out.println(uIComponentHtmlBody);
					 if(uIComponentHtmlBody instanceof HtmlForm) {
						 HtmlForm htmlForm = (HtmlForm)uIComponentHtmlBody; 
						 List<UIComponent> listUiCompHtmlBForm  = htmlForm.getChildren(); 
						 Iterator<UIComponent> iterUiCompHtmlBForm  = listUiCompHtmlBForm.iterator();
						 while(iterUiCompHtmlBForm.hasNext()) {
							 UIComponent uIComponentHtmlBForm = iterUiCompHtmlBForm.next(); 
							 System.out.println(uIComponentHtmlBForm);
						 }
					 }
				 }
				System.out.println("Finaliza htmlBody");
			}
			System.out.println(uIComponent);
		}
		**********************************************************************/
		/**********************************************************************
	    Prueba 2     
	    ExternalContext externalContext = facesContext.getExternalContext();
	    HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
	    response.reset();
        response.setHeader("Content-Type", "application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=\"fileName.pdf\"");
        facesContext.responseComplete();
	    ***********************************************************************/
		System.out.println("Sale guardarFormato");
	} 
	 
	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public String getNombreCompletoPaciente() {
		return nombreCompletoPaciente;
	}

	public void setNombreCompletoPaciente(String nombreCompletoPaciente) {
		this.nombreCompletoPaciente = nombreCompletoPaciente;
	}

	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public int getAnios() {
		return anios;
	}

	public void setAnios(int anios) {
		this.anios = anios;
	}

	
	
}
