package com.dtecimax.ejb.backing.hr;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.PrimeFaces;

import com.dtecimax.ejb.model.hr.Doctores;
import com.dtecimax.ejb.model.hr.DoctoresReferentes;
import com.dtecimax.ejb.services.email.SessionBeanLocal;
import com.dtecimax.ejb.services.hr.DoctoresReferentesLocal;
import com.dtecimax.jpa.dto.hr.DoctoresDto;
import com.dtecimax.jpa.dto.hr.DoctoresReferentesDto;

@ManagedBean  
@ViewScoped
public class DoctoresReferentesForm {

	@ManagedProperty(value="#{doctoresReferentes}")
	
	private DoctoresReferentes doctoresReferentes;
	private DoctoresReferentes doctoresReferentesSelected = new DoctoresReferentes(); 
	private List<DoctoresReferentes> listDoctoresReferentes = new ArrayList<DoctoresReferentes>();
	
	private String searchNomDoct; 
	private String searchApellPatDoct; 
	private String searchApellMatDoct; 
	private String searchLugarTrabajo; 
	private String searchAreaDocRef; 
	private String toemail;
	private String subjectemail; 
	@Inject
	DoctoresReferentesLocal doctoresReferentesLocal;
	SessionBeanLocal sessionBeanLocal; 
	
	@PostConstruct 
	public void init() { 
		  refreshEntity(); 
		  }
	  
	  private void refreshEntity() { 
	  listDoctoresReferentes = new ArrayList<DoctoresReferentes>();
	  List<DoctoresReferentesDto>listDoctoresReferentesDto = doctoresReferentesLocal.findAll();
	  Iterator<DoctoresReferentesDto>iterDoctoresReferentesDto =listDoctoresReferentesDto.iterator();
	  while(iterDoctoresReferentesDto.hasNext()) {
	  
	  DoctoresReferentesDto doctoresReferentesDto = iterDoctoresReferentesDto.next(); 
	  DoctoresReferentes doctoresReferentes = new DoctoresReferentes();
	  doctoresReferentes.setNumeroDoctorReferente(doctoresReferentesDto.getNumeroDoctorReferente());
	  doctoresReferentes.setNombreDoctorReferente(doctoresReferentesDto.getNombreDoctorReferente());
	  doctoresReferentes.setApellidoMaternoDoctorReferente(doctoresReferentesDto.getApellidoMaternoDoctorReferente());
	  doctoresReferentes.setApellidoPaternoDoctorReferente(doctoresReferentesDto.getApellidoPaternoDoctorReferente());
	  doctoresReferentes.setFechaNacimientoDoctorReferente(doctoresReferentesDto.getFechaNacimientoDoctorReferente());
	  doctoresReferentes.setCedulaDoctorReferente(doctoresReferentesDto.getCedulaDoctorReferente());
	  doctoresReferentes.setCelularDoctorReferente(doctoresReferentesDto.getCelularDoctorReferente());
	  doctoresReferentes.setCurpDoctorReferente(doctoresReferentesDto.getCurpDoctorReferente());
	  doctoresReferentes.setDomicilioDoctorReferente(doctoresReferentesDto.getDomicilioDoctorReferente());
	  doctoresReferentes.setAreaDoctorReferente(doctoresReferentesDto.getAreaDoctorReferente());
	  doctoresReferentes.setLugarTrabajo(doctoresReferentesDto.getLugarTrabajo());
	  doctoresReferentes.setCorreoDoctorReferente(doctoresReferentesDto.getCorreoDoctorReferente());
	  doctoresReferentes.setEstatus(doctoresReferentesDto.getEstatus());
	  doctoresReferentes.setFechaCreacion(doctoresReferentesDto.getFechaCreacion()); 
	  doctoresReferentes.setFechaUltimaActualizacion(doctoresReferentesDto.getFechaUltimaActualizacion());
	  doctoresReferentes.setComentarios(doctoresReferentesDto.getComentarios());
	  Calendar fecha = Calendar.getInstance();
	  System.out.println ("fecha completa "+fecha);
	  int diaAct = fecha.get(Calendar.DAY_OF_MONTH);
	  int mesAct = fecha.get(Calendar.MONTH);
	  mesAct = mesAct+1;
	  System.out.println ("dia act "+diaAct);
	  System.out.println ("mes act "+mesAct);
	
	  
	  Timestamp fechaNacimiento =  doctoresReferentesDto.getFechaNacimientoDoctorReferente();
      System.out.println("fecha completa Nacimiento "+fechaNacimiento);
	  Calendar fechaNac = Calendar.getInstance();
	  fechaNac.setTime(fechaNacimiento);
	  int mesDoctRef =fechaNac.get(Calendar.MONTH);
	  int diaDoctRef = fechaNac.get(Calendar.DAY_OF_MONTH);
	  mesDoctRef = mesDoctRef+1;
      System.out.println("diaaa "+diaDoctRef);
      System.out.println("messs "+mesDoctRef);
    
      if(diaDoctRef==diaAct && mesDoctRef==mesAct) {
        doctoresReferentes.setColorRegistro("dtecimaxgreen"); 
	  } 
      else if(diaDoctRef>diaAct&&mesDoctRef==mesAct){
		  doctoresReferentes.setColorRegistro("dtecimaxolive"); 
	  }
	  listDoctoresReferentes.add(doctoresReferentes); }
	  }
	   
	 

	  public void insertDoctoresReferentes() {
	  
	  boolean loggedIn = false;
	  
	  DoctoresReferentesDto doctoresReferentesDto = new DoctoresReferentesDto();

	  doctoresReferentesDto.setNombreDoctorReferente(doctoresReferentes.getNombreDoctorReferente());
	  doctoresReferentesDto.setApellidoMaternoDoctorReferente(doctoresReferentes.getApellidoMaternoDoctorReferente());
	  doctoresReferentesDto.setApellidoPaternoDoctorReferente(doctoresReferentes.getApellidoPaternoDoctorReferente());
	  doctoresReferentesDto.setFechaNacimientoDoctorReferente(doctoresReferentes.getFechaNacimientoDoctorReferente());
	  doctoresReferentesDto.setCedulaDoctorReferente(doctoresReferentes.getCedulaDoctorReferente());
	  doctoresReferentesDto.setCelularDoctorReferente(doctoresReferentes.getCelularDoctorReferente());
	  doctoresReferentesDto.setCurpDoctorReferente(doctoresReferentes.getCurpDoctorReferente());
	  doctoresReferentesDto.setDomicilioDoctorReferente(doctoresReferentes.getDomicilioDoctorReferente());
	  doctoresReferentesDto.setAreaDoctorReferente(doctoresReferentes.getAreaDoctorReferente());
	  doctoresReferentesDto.setLugarTrabajo(doctoresReferentes.getLugarTrabajo());
	  doctoresReferentesDto.setCorreoDoctorReferente(doctoresReferentes.getCorreoDoctorReferente());
	  doctoresReferentesDto.setEstatus(doctoresReferentes.getEstatus());
	  doctoresReferentesDto.setFechaCreacion(doctoresReferentes.getFechaCreacion());
	  doctoresReferentesDto.setFechaUltimaActualizacion(doctoresReferentes.getFechaUltimaActualizacion()); 
	  doctoresReferentesDto.setComentarios(doctoresReferentes.getComentarios());
	  
	  doctoresReferentesLocal.insertDoctoresReferentes(doctoresReferentesDto); 
	  refreshEntity(); loggedIn = true;
	  PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
	  
	  }
	 
	
	public void selectForUpdate(DoctoresReferentes pDoctoresReferentes) {
		doctoresReferentesSelected.setNumeroDoctorReferente(pDoctoresReferentes.getNumeroDoctorReferente());
		doctoresReferentesSelected.setNombreDoctorReferente(pDoctoresReferentes.getNombreDoctorReferente());
		doctoresReferentesSelected.setApellidoMaternoDoctorReferente(pDoctoresReferentes.getApellidoMaternoDoctorReferente());
		doctoresReferentesSelected.setApellidoPaternoDoctorReferente(pDoctoresReferentes.getApellidoPaternoDoctorReferente());
		doctoresReferentesSelected.setFechaNacimientoDoctorReferente(pDoctoresReferentes.getFechaNacimientoDoctorReferente());
		doctoresReferentesSelected.setCedulaDoctorReferente(pDoctoresReferentes.getCedulaDoctorReferente());
		doctoresReferentesSelected.setCelularDoctorReferente(pDoctoresReferentes.getCelularDoctorReferente());
		doctoresReferentesSelected.setCurpDoctorReferente(pDoctoresReferentes.getCurpDoctorReferente());
		doctoresReferentesSelected.setDomicilioDoctorReferente(pDoctoresReferentes.getDomicilioDoctorReferente());
		doctoresReferentesSelected.setAreaDoctorReferente(pDoctoresReferentes.getAreaDoctorReferente());
		doctoresReferentesSelected.setLugarTrabajo(pDoctoresReferentes.getLugarTrabajo());
		doctoresReferentesSelected.setCorreoDoctorReferente(pDoctoresReferentes.getCorreoDoctorReferente());
		doctoresReferentesSelected.setEstatus(pDoctoresReferentes.getEstatus());
		doctoresReferentesSelected.setFechaCreacion(pDoctoresReferentes.getFechaCreacion());
		doctoresReferentesSelected.setFechaUltimaActualizacion(pDoctoresReferentes.getFechaUltimaActualizacion());
		doctoresReferentesSelected.setComentarios(pDoctoresReferentes.getComentarios());
	}
	
	  	public void selectForDelete(DoctoresReferentes pDoctoresReferentes) {
	  		doctoresReferentesSelected.setNumeroDoctorReferente(pDoctoresReferentes.getNumeroDoctorReferente());
	  		doctoresReferentesSelected.setNombreDoctorReferente(pDoctoresReferentes.getNombreDoctorReferente());
	  		doctoresReferentesSelected.setApellidoMaternoDoctorReferente(pDoctoresReferentes.getApellidoMaternoDoctorReferente());
	  		doctoresReferentesSelected.setApellidoPaternoDoctorReferente(pDoctoresReferentes.getApellidoPaternoDoctorReferente());
	  		doctoresReferentesSelected.setFechaNacimientoDoctorReferente(pDoctoresReferentes.getFechaNacimientoDoctorReferente());
	  		doctoresReferentesSelected.setCedulaDoctorReferente(pDoctoresReferentes.getCedulaDoctorReferente());
	  		doctoresReferentesSelected.setCelularDoctorReferente(pDoctoresReferentes.getCelularDoctorReferente());
	  		doctoresReferentesSelected.setCurpDoctorReferente(pDoctoresReferentes.getCurpDoctorReferente());
	  		doctoresReferentesSelected.setDomicilioDoctorReferente(pDoctoresReferentes.getDomicilioDoctorReferente());
	  		doctoresReferentesSelected.setAreaDoctorReferente(pDoctoresReferentes.getAreaDoctorReferente());
	  		doctoresReferentesSelected.setLugarTrabajo(pDoctoresReferentes.getLugarTrabajo());
	  		doctoresReferentesSelected.setCorreoDoctorReferente(pDoctoresReferentes.getCorreoDoctorReferente());
	  		doctoresReferentesSelected.setEstatus(pDoctoresReferentes.getEstatus());
	  		doctoresReferentesSelected.setFechaCreacion(pDoctoresReferentes.getFechaCreacion());
	  		doctoresReferentesSelected.setFechaUltimaActualizacion(pDoctoresReferentes.getFechaUltimaActualizacion());
	  		doctoresReferentesSelected.setComentarios(pDoctoresReferentes.getComentarios());
	  		}
	
	
	
	  public void delete() { 
	  try {
	  doctoresReferentesLocal.deleteDoctoresReferentes(doctoresReferentesSelected.getNumeroDoctorReferente()); 
	  refreshEntity(); }
	  catch(Exception e) {
	  Throwable throwable = e.getCause(); 
	  while(null!=throwable) { 
	  throwable = throwable.getCause(); 
	  if(null!=throwable) {
	  if(throwable.toString().contains("CITAS_FK_V2")) {
	  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!","No se puede eliminar un doctor con una cita.")); break; } } } }
	  
	  }
	 
	  	public void updateDoctoresReferentes() { boolean updatedIn = false; 
	  	DoctoresReferentesDto doctoresReferentesDto = new DoctoresReferentesDto();
	  	
	  	doctoresReferentesDto.setNombreDoctorReferente(doctoresReferentesSelected.getNombreDoctorReferente());
	  	doctoresReferentesDto.setApellidoMaternoDoctorReferente(doctoresReferentesSelected.getApellidoMaternoDoctorReferente());
	  	doctoresReferentesDto.setApellidoPaternoDoctorReferente(doctoresReferentesSelected.getApellidoPaternoDoctorReferente());
	  	doctoresReferentesDto.setFechaNacimientoDoctorReferente(doctoresReferentesSelected.getFechaNacimientoDoctorReferente());
	  	doctoresReferentesDto.setCedulaDoctorReferente(doctoresReferentesSelected.getCedulaDoctorReferente());
	  	doctoresReferentesDto.setCelularDoctorReferente(doctoresReferentesSelected.getCelularDoctorReferente());
	  	doctoresReferentesDto.setCurpDoctorReferente(doctoresReferentesSelected.getCurpDoctorReferente());
	  	doctoresReferentesDto.setDomicilioDoctorReferente(doctoresReferentesSelected.getDomicilioDoctorReferente());
	  	doctoresReferentesDto.setAreaDoctorReferente(doctoresReferentesSelected.getAreaDoctorReferente());
	  	doctoresReferentesDto.setLugarTrabajo(doctoresReferentesSelected.getLugarTrabajo());
	  	doctoresReferentesDto.setCorreoDoctorReferente(doctoresReferentesSelected.getCorreoDoctorReferente());
	  	doctoresReferentesDto.setEstatus(doctoresReferentesSelected.getEstatus());
	  	doctoresReferentesDto.setFechaCreacion(doctoresReferentesSelected.getFechaCreacion());
	  	doctoresReferentesDto.setFechaUltimaActualizacion(doctoresReferentesSelected.getFechaUltimaActualizacion());
	  	doctoresReferentesDto.setComentarios(doctoresReferentesSelected.getComentarios());
	  	doctoresReferentesLocal.updateDoctoresReferentes(doctoresReferentesSelected.getNumeroDoctorReferente(),doctoresReferentesDto); 
	  	
	  	refreshEntity(); updatedIn = true;
	  	PrimeFaces.current().ajax().addCallbackParam("updatedIn", updatedIn); 
	  	}
	  	
	  	public void search() {
	        System.out.println("Entra search");
	        System.out.println(this.searchNomDoct);
	        if(null!=this.searchNomDoct&&!"".equals(this.searchNomDoct)
	          ||(null!=this.searchApellPatDoct&&!"".equals(this.searchApellPatDoct))
	          ||(null!=this.searchApellMatDoct&&!"".equals(this.searchApellMatDoct))
	          ||(null!=this.searchLugarTrabajo&&!"".equals(this.searchLugarTrabajo))
	          ||(null!=this.searchAreaDocRef&&!"".equals(this.searchAreaDocRef))
	          ) {
	      	  
	      	listDoctoresReferentes = new ArrayList<DoctoresReferentes>();
	    		List<DoctoresReferentesDto> listDoctoresReferentesDto = doctoresReferentesLocal.findBySearch(this.searchNomDoct
	    				                                                      ,this.searchApellPatDoct
	    				                                                      ,this.searchApellMatDoct
	    				                                                      ,this.searchLugarTrabajo
	    				                                                      ,this.searchAreaDocRef
	    				                                                      ); 
	    		Iterator<DoctoresReferentesDto> iterDoctoresReferentesDto =  listDoctoresReferentesDto.iterator();
	    		while(iterDoctoresReferentesDto.hasNext()) {
	    			DoctoresReferentesDto doctoresReferentesDto = iterDoctoresReferentesDto.next();
	    			DoctoresReferentes doctoresReferentes = new DoctoresReferentes();
	    			doctoresReferentes.setNumeroDoctorReferente(doctoresReferentesDto.getNumeroDoctorReferente());
	    			  doctoresReferentes.setNombreDoctorReferente(doctoresReferentesDto.getNombreDoctorReferente());
	    			  doctoresReferentes.setApellidoMaternoDoctorReferente(doctoresReferentesDto.getApellidoMaternoDoctorReferente());
	    			  doctoresReferentes.setApellidoPaternoDoctorReferente(doctoresReferentesDto.getApellidoPaternoDoctorReferente());
	    			  doctoresReferentes.setFechaNacimientoDoctorReferente(doctoresReferentesDto.getFechaNacimientoDoctorReferente());
	    			  doctoresReferentes.setCedulaDoctorReferente(doctoresReferentesDto.getCedulaDoctorReferente());
	    			  doctoresReferentes.setCelularDoctorReferente(doctoresReferentesDto.getCelularDoctorReferente());
	    			  doctoresReferentes.setCurpDoctorReferente(doctoresReferentesDto.getCurpDoctorReferente());
	    			  doctoresReferentes.setDomicilioDoctorReferente(doctoresReferentesDto.getDomicilioDoctorReferente());
	    			  doctoresReferentes.setAreaDoctorReferente(doctoresReferentesDto.getAreaDoctorReferente());
	    			  doctoresReferentes.setLugarTrabajo(doctoresReferentesDto.getLugarTrabajo());
	    			  doctoresReferentes.setCorreoDoctorReferente(doctoresReferentesDto.getCorreoDoctorReferente());
	    			  doctoresReferentes.setEstatus(doctoresReferentesDto.getEstatus());
	    			  doctoresReferentes.setFechaCreacion(doctoresReferentesDto.getFechaCreacion()); 
	    			  doctoresReferentes.setFechaUltimaActualizacion(doctoresReferentesDto.getFechaUltimaActualizacion());
	    			  doctoresReferentes.setComentarios(doctoresReferentesDto.getComentarios());
	    			listDoctoresReferentes.add(doctoresReferentes);
	    		}
	        }
	        
	        System.out.println("Sale search");
	  	}
	  	
	    public void selectForEmail(DoctoresReferentes pDoctoresReferentes) {
			 	   doctoresReferentes = new DoctoresReferentes();
			 	   doctoresReferentes.setNumeroDoctorReferente(pDoctoresReferentes.getNumeroDoctorReferente());
			 	  doctoresReferentes.setComentarios(pDoctoresReferentes.getComentarios());
			 	//doctoresReferentesSelected.setInfoAdicional(pOrdenesEstudiosEspeciales.getInfoAdicional());
			 		
			    }

		    
			    public void enviarIndicaciones() {
			    	System.out.println("Comienza enviarIndicaciones");
		    	sessionBeanLocal.sendEmail(this.toemail, this.subjectemail, this.doctoresReferentesSelected.getIndicacionesDoctorReferente());
			    	System.out.println("Finaliza enviarIndicaciones");
			    }
			  	
	  	
	  	
	public DoctoresReferentes getDoctoresReferentes() {
		return doctoresReferentes;
	}

	public void setDoctoresReferentes(DoctoresReferentes doctoresReferentes) {
		this.doctoresReferentes = doctoresReferentes;
	}

	public DoctoresReferentes getDoctoresReferentesSelected() {
		return doctoresReferentesSelected;
	}

	public void setDoctoresReferentesSelected(DoctoresReferentes doctoresReferentesSelected) {
		this.doctoresReferentesSelected = doctoresReferentesSelected;
	}

	public List<DoctoresReferentes> getListDoctoresReferentes() {
		return listDoctoresReferentes;
	}

	public void setListDoctores(List<DoctoresReferentes> listDoctoresReferentes) {
		this.listDoctoresReferentes = listDoctoresReferentes;
	}
	
	public String getSearchNomDoct() {
		return searchNomDoct;
	}

	public void setSearchNomDoct(String searchNomDoct) {
		this.searchNomDoct = searchNomDoct;
	}
	public String getSearchAreaDocRef() {
		return searchAreaDocRef;
	}

	public void setSearchAreaDocRef(String searchAreaDocRef) {
		this.searchAreaDocRef = searchAreaDocRef;
	}
	public String getToemail() {
		return toemail;
	}

	public void setToemail(String toemail) {
		this.toemail = toemail;
	}
	public String getSearchLugarTrabajo() {
		return searchLugarTrabajo;
	}

	public void setSearchLugarTrabajo(String searchLugarTrabajo) {
		this.searchLugarTrabajo = searchLugarTrabajo;
	}
	public String getSubjectemail() {
		return subjectemail;
	}

	public void setSubjectemail(String subjectemail) {
		this.subjectemail = subjectemail;
	}
	public String getSearchApellPatDoct() {
		return searchApellPatDoct;
	}

	public void setSearchApellPatDoct(String searchApellPatDoct) {
		this.searchApellPatDoct = searchApellPatDoct;
	}

	public String getSearchApellMatDoct() {
		return searchApellMatDoct;
	}

	public void setSearchApellMatDoct(String searchApellMatDoct) {
		this.searchApellMatDoct = searchApellMatDoct;
	}
	

}
