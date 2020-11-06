package com.dtecimax.ejb.backing.as;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;

import com.dtecimax.ejb.model.as.OrdenesEstudios;
import com.dtecimax.ejb.model.as.OrdenesEstudiosHistorial;
import com.dtecimax.ejb.model.as.OrdenesEstudiosSimples;
import com.dtecimax.ejb.services.admin.UbicacionesLocal;
import com.dtecimax.ejb.services.ar.PacientesLocal;
import com.dtecimax.ejb.services.as.EstudiosLocal;
import com.dtecimax.ejb.services.as.OrdenesEstudiosLocal;
import com.dtecimax.ejb.services.as.OrdenesEstudiosVLocal;
import com.dtecimax.ejb.services.hr.DoctoresLocal;
import com.dtecimax.ejb.services.hr.DoctoresReferentesLocal;
import com.dtecimax.jpa.dto.admin.UbicacionesDto;
import com.dtecimax.jpa.dto.ar.PacientesDto;
import com.dtecimax.jpa.dto.as.EstudiosDto;
import com.dtecimax.jpa.dto.as.OrdenesEstudiosDto;
import com.dtecimax.jpa.dto.as.OrdenesEstudiosVDto;
import com.dtecimax.jpa.dto.hr.DoctoresDto;
import com.dtecimax.jpa.dto.hr.DoctoresReferentesDto;

@ManagedBean  
@ViewScoped
public class HistorialOrdenesForm {

	@ManagedProperty(value="#{ordenesEstudiosHistorial}") 
	private OrdenesEstudiosHistorial ordenesEstudiosHistorial;

 
	private List<OrdenesEstudiosHistorial> listOrdenesEstudiosHistorial = new ArrayList<OrdenesEstudiosHistorial>();
	
	
	@Inject
	PacientesLocal pacientesLocal;
	

	@Inject 
	OrdenesEstudiosLocal ordenesEstudiosLocal; 
	
	@Inject 
	OrdenesEstudiosVLocal ordenesEstudiosVLocal; 
	
	@Inject 
	EstudiosLocal estudiosLocal;

	private String searchNomEstu; 
	private String searchNomPaci; 
	private String searchNumOrde; 
	private String searchNomDocRef; 
	private String decodeTipoOrden;
	private String apellidoPaternoPaciente;
	private String localFormato; 
	
	@PostConstruct
	 public void init() {
		 refreshEntity();
	 }
	
	public OrdenesEstudiosHistorial getOrdenesEstudiosHistorial() {
		return ordenesEstudiosHistorial;
	}

	public void setOrdenesEstudiosHistorial(OrdenesEstudiosHistorial ordenesEstudioHistorial) {
		this.ordenesEstudiosHistorial = ordenesEstudioHistorial;
	} 
	

	public void insertaHistorialOrden() {
	
	  boolean loggedIn = false;
		
	  OrdenesEstudiosDto ordenesEstudiosDto = new OrdenesEstudiosDto();
	  

	  ordenesEstudiosDto.setNumeroPaciente(ordenesEstudiosHistorial.getNumeroPaciente());
	  ordenesEstudiosDto.setTipoOrden((short)1/*Simple*/);
	  ordenesEstudiosDto.setNumeroEstudio(ordenesEstudiosHistorial.getNumeroEstudio()/*(long)2*/);
	
	  ordenesEstudiosLocal.insertOrdenesEstudios(ordenesEstudiosDto);
	  refreshEntity();
	  loggedIn = true;
	  PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
		
	}
	
	private void refreshEntity() {
		listOrdenesEstudiosHistorial = new ArrayList<OrdenesEstudiosHistorial>(); 
		List<OrdenesEstudiosVDto> listOrdenesVEstudiosDto = ordenesEstudiosVLocal.findAll();
		Iterator<OrdenesEstudiosVDto> iterOrdenesEstudiosVDto = listOrdenesVEstudiosDto.iterator(); 
		while(iterOrdenesEstudiosVDto.hasNext()) {
			OrdenesEstudiosVDto ordenesEstudiosVDto = iterOrdenesEstudiosVDto.next(); 
			OrdenesEstudiosHistorial ordenesEstudiosHistorial = new OrdenesEstudiosHistorial();
			ordenesEstudiosHistorial.setNumeroOrden(ordenesEstudiosVDto.getNumeroOrden());
			ordenesEstudiosHistorial.setNombrePaciente(ordenesEstudiosVDto.getNombrePaciente());
			ordenesEstudiosHistorial.setNombrePaciente(ordenesEstudiosVDto.getNombrePaciente());
			ordenesEstudiosHistorial.setNombreEstudio(ordenesEstudiosVDto.getNombreEstudio());
			ordenesEstudiosHistorial.setNombreDoctorReferente(ordenesEstudiosVDto.getNombreDoctorReferente ());
			ordenesEstudiosHistorial.setApellidoPaternoDoctorReferente(ordenesEstudiosVDto.getApellidoPaternoDoctorReferente ());
			ordenesEstudiosHistorial.setApellidoMaternoDoctorReferente(ordenesEstudiosVDto.getApellidoMaternoDoctorReferente ());
			ordenesEstudiosHistorial.setCostoEstudio(ordenesEstudiosVDto.getCostoEstudio());
			ordenesEstudiosHistorial.setNumeroPaciente(ordenesEstudiosVDto.getNumeroPaciente());
			ordenesEstudiosHistorial.setDecodeTipoOrden(ordenesEstudiosVDto.getDecodeTipoOrden());
			ordenesEstudiosHistorial.setFechaCreacion(ordenesEstudiosVDto.getFechaCreacion());
	
			listOrdenesEstudiosHistorial.add(ordenesEstudiosHistorial);
		}
	}


    
	   public void search() {
	    	if((null!=this.searchNomEstu&&!"".equals(this.searchNomEstu))
	    	  ||(null!=this.searchNomPaci&&!"".equals(this.searchNomPaci))
	    	  ||(null!=this.searchNumOrde&&!"".equals(this.searchNumOrde))
	    	  ||(null!=this.searchNomDocRef&&!"".equals(this.searchNomDocRef))
	    	  ) {
	    	
	    		listOrdenesEstudiosHistorial= new ArrayList<OrdenesEstudiosHistorial>(); 
	    		List<OrdenesEstudiosVDto> listOrdenesEstudiosVDto = ordenesEstudiosVLocal.findBySearchHisto( this.searchNumOrde
	    				                                                                                  ,this.searchNomEstu
                                                                                                          ,this.searchNomPaci
                                                                                                          ,this.searchNomDocRef
                                                                                                                );
	    				                                                                          
	    		Iterator<OrdenesEstudiosVDto> iterOrdenesEstudiosVDto = listOrdenesEstudiosVDto.iterator();
	    		while(iterOrdenesEstudiosVDto.hasNext()) {
	    			OrdenesEstudiosVDto ordenesEstudiosVDto = iterOrdenesEstudiosVDto.next();
	    			OrdenesEstudiosHistorial ordenesEstudiosHistorial = new OrdenesEstudiosHistorial(); 
	    			ordenesEstudiosHistorial.setNumeroOrden(ordenesEstudiosVDto.getNumeroOrden());
	    			ordenesEstudiosHistorial.setNombrePaciente(ordenesEstudiosVDto.getNombrePaciente());
	    			ordenesEstudiosHistorial.setNumeroEstudio(ordenesEstudiosVDto.getNumeroEstudio());
	    			ordenesEstudiosHistorial.setNombreEstudio(ordenesEstudiosVDto.getNombreEstudio());
	    			ordenesEstudiosHistorial.setNumeroDoctorReferente(ordenesEstudiosVDto.getNumeroDoctorReferente ());
	    			ordenesEstudiosHistorial.setNombreDoctorReferente(ordenesEstudiosVDto.getNombreDoctorReferente ());
	    			ordenesEstudiosHistorial.setApellidoPaternoDoctorReferente(ordenesEstudiosVDto.getApellidoPaternoDoctorReferente ());
	    			ordenesEstudiosHistorial.setApellidoMaternoDoctorReferente(ordenesEstudiosVDto.getApellidoMaternoDoctorReferente ());
	    			ordenesEstudiosHistorial.setDecodeTipoOrden(ordenesEstudiosVDto.getDecodeTipoOrden());
	    			ordenesEstudiosHistorial.setNumeroPaciente(ordenesEstudiosVDto.getNumeroPaciente());
	    			ordenesEstudiosHistorial.setEstatus(ordenesEstudiosVDto.getEstatus());
	    			ordenesEstudiosHistorial.setFechaCreacion(ordenesEstudiosVDto.getFechaCreacion());
	    			ordenesEstudiosHistorial.setFechaUltimaActualizacion(ordenesEstudiosVDto.getFechaUltimaActualizacion());
	    			ordenesEstudiosHistorial.setHoraInicialOrden(ordenesEstudiosVDto.getHoraInicialOrden());
	    			ordenesEstudiosHistorial.setHoraFinalOrden(ordenesEstudiosVDto.getHoraFinalOrden());
	    			
	    			listOrdenesEstudiosHistorial.add(ordenesEstudiosHistorial);
	    		}
	    		
	    	}
	    	
	    }
	    

	public List<OrdenesEstudiosHistorial> getListOrdenesEstudiosHistorial() {
		return listOrdenesEstudiosHistorial;
	}

	public void setListOrdenesEstudiosHistorial(List<OrdenesEstudiosHistorial> listOrdenesEstudiosHistorial) {
		this.listOrdenesEstudiosHistorial = listOrdenesEstudiosHistorial;
	}



	public String getSearchNomPaci() {
		return searchNomPaci;
	}

	public void setSearchNomPaci(String searchNomPaci) {
		this.searchNomPaci = searchNomPaci;
	}

	public String  getSearchNumOrde() {
		return searchNumOrde;
	}

	public void setSearchNumOrde(String searchNumOrde) {
		this.searchNumOrde = searchNumOrde;
	}
	public String  getSearchNomDocRef() {
		return searchNomDocRef;
	}

	public void setSearchNomDocRef(String searchNomDocRef) {
		this.searchNomDocRef= searchNomDocRef;
	}

	public String getSearchNomEstu() {
		return searchNomEstu;
	}

	public void setSearchNomEstu(String searchNomEstu) {
		this.searchNomEstu = searchNomEstu;
	}

	public String getLocalFormato() {
		return localFormato;
	}

	public void setLocalFormato(String localFormato) {
		this.localFormato = localFormato;
	}
	public String getDecodeTipoOrden() {
		return this.decodeTipoOrden;
	}

	public void setDecodeTipoOrden(String decodeTipoOrden) {
		this.decodeTipoOrden = decodeTipoOrden;
	}
	public String getApellidoPaternoPaciente() {
		return apellidoPaternoPaciente;
	}

	public void setApellidoPaternoPaciente(String apellidoPaternoPaciente) {
		this.apellidoPaternoPaciente = apellidoPaternoPaciente;
	}


	
}
